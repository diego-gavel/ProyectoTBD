package controllersSql2o;

import models.Dimension;
import models.Voluntario;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.*;
import java.util.List;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DimensionSql2o {
    private Sql2o sql2o[];
    public DimensionSql2o(Sql2o[] sql2o) {
        this.sql2o = sql2o;
    }

    public List<Dimension> getAllDimensiones(){
        try {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            List<Dimension>[] dimensions = new ArrayList[2];
            for (int i = 0; i < 2; i++) {
                final int db = i;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try (Connection conn = sql2o[db].open()) {
                            dimensions[db] = conn.createQuery("select * from dimension")
                                    .executeAndFetch(Dimension.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24 * 3600, TimeUnit.SECONDS);
            List<Dimension> merged = new ArrayList<Dimension>();
            for( int i = 0; i < 2; i++){
                merged.addAll(dimensions[i]);
            }
            return merged;
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
       /* try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from dimension")
                    .executeAndFetch(Dimension.class);
        }*/
    }

    public List<Dimension> getAllDimensionesPaginated(int limit, int offset){
        try {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            List<Dimension>[] dimensions = new ArrayList[2];
            for (int i = 0; i < 2; i++) {
                final int db = i;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try (Connection conn = sql2o[db].open()) {
                            dimensions[db] = conn.createQuery("select * from dimension limit :limit offset :offset")
                                    .addParameter("limit", limit)
                                    .addParameter("offset", offset)
                                    .executeAndFetch(Dimension.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24 * 3600, TimeUnit.SECONDS);
            List<Dimension> merged = new ArrayList<Dimension>();
            for( int i = 0; i < 2; i++){
                merged.addAll(dimensions[i]);
            }
            return merged;
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
       /*try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from dimension limit :limit offset :offset")
                    .addParameter("limit", limit)
                    .addParameter("offset", offset)
                    .executeAndFetch(Dimension.class);
        }*/
    }

    public int totalDimensiones(){
        try {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            int total_dimensions = 0;
            List<String>[] subtotal_dimensions = new ArrayList[2];
            for (int i = 0; i < 2; i++) {
                final int db = i;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try (Connection conn = sql2o[db].open()) {
                            subtotal_dimensions[db] = conn.createQuery("select count(d) from dimension d")
                                    .executeAndFetch(String.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24 * 3600, TimeUnit.SECONDS);
            for (int j = 0; j < 2; j++) {
                total_dimensions = total_dimensions + Integer.parseInt(subtotal_dimensions[j].get(0));
            }
            return total_dimensions;
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
        /*try(Connection conn = sql2o.open()){
            int cantidad = conn.createQuery("select count(d) from dimension d")
                    .executeScalar(Integer.class);
            return cantidad;
        }*/
    }

    public int crearDimension(Dimension dimension){
        int db = (int)dimension.getId() % 2;
        try(Connection conn = sql2o[db].open()){
            int newId = conn.createQuery("insert into dimension(nombre) values (:nombre)")
                    .addParameter("nombre", dimension.getNombre())
                    .executeUpdate().getKey(Integer.class);
            return newId;
        }
        /*try(Connection conn = sql2o.open()){
            int newId = conn.createQuery("insert into dimension(nombre) values (:nombre)")
                    .addParameter("nombre", dimension.getNombre())
                    .executeUpdate().getKey(Integer.class);
            return newId;
        }*/
    }

    public List<Dimension> obtenerDimension(String id){
        int db = Integer.parseInt(id) % 2;
        try(Connection conn = sql2o[db].open()){
            return conn.createQuery("select * from dimension where id_dimension = " + id)
                    .executeAndFetch(Dimension.class);
        }
    }


    public List<Voluntario> obtenerVoluntarioDimension(String id){
        int db = Integer.parseInt(id) % 2;
        try(Connection conn = sql2o[db].open()){
            return conn.createQuery("select v from voluntario v , dimension_voluntario dv" +
                    " where dv.id_dimension = " + id + " and dv.id_voluntario = v.id_voluntario ")
                    .executeAndFetch(Voluntario.class);
        }
    }

}
