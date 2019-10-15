package controllersSql2o;

import models.Emergencia;
import models.Tarea;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.*;
import java.util.List;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EmergenciaSql2o {
    private Sql2o sql2o[];
    public EmergenciaSql2o(Sql2o[] sql2o) {
        this.sql2o = sql2o;
    }

    public List<Emergencia> getAllEmergencias(){
        try {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            List<Emergencia>[] dimensions = new ArrayList[2];
            for (int i = 0; i < 2; i++) {
                final int db = i;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try (Connection conn = sql2o[db].open()) {
                            dimensions[db] = conn.createQuery("select * from emergencia")
                                    .executeAndFetch(Emergencia.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24 * 3600, TimeUnit.SECONDS);
            List<Emergencia> merged = new ArrayList<Emergencia>();
            for( int i = 0; i < 2; i++){
                merged.addAll(dimensions[i]);
            }
            return merged;
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
        /*try(Connection conn = sql2o[].open()){
            return conn.createQuery("select * from emergencia")
                    .executeAndFetch(Emergencia.class);
        }*/
    }

    public List<Emergencia> getAllEmergenciasPaginated(int limit, int offset){
        try {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            List<Emergencia>[] dimensions = new ArrayList[2];
            for (int i = 0; i < 2; i++) {
                final int db = i;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try (Connection conn = sql2o[db].open()) {
                            dimensions[db] = conn.createQuery("select * from emergencia limit :limit offset :offset")
                                    .addParameter("limit", limit)
                                    .addParameter("offset", offset)
                                    .executeAndFetch(Emergencia.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24 * 3600, TimeUnit.SECONDS);
            List<Emergencia> merged = new ArrayList<Emergencia>();
            for( int i = 0; i < 2; i++){
                merged.addAll(dimensions[i]);
            }
            return merged;
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
        /*try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from emergencia limit :limit offset :offset")
                    .addParameter("limit", limit)
                    .addParameter("offset", offset)
                    .executeAndFetch(Emergencia.class);
        }*/
    }

    public int totalEmergencias(){
        try {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            int total_emergencias = 0;
            List<String>[] subtotal_emergencias = new ArrayList[2];
            for (int i = 0; i < 2; i++) {
                final int db = i;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try (Connection conn = sql2o[db].open()) {
                            subtotal_emergencias[db] = conn.createQuery("select count(d) from emergencia e")
                                    .executeAndFetch(String.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24 * 3600, TimeUnit.SECONDS);
            for (int j = 0; j < 2; j++) {
                total_emergencias = total_emergencias + Integer.parseInt(subtotal_emergencias[j].get(0));
            }
            return total_emergencias;
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
        /*try(Connection conn = sql2o.open()){
            int cantidad = conn.createQuery("select count(e) from emergencia e")
                    .executeScalar(Integer.class);
            return cantidad;
        }*/
    }

    public int crearEmergencia(Emergencia emergencia){
        int db = (int)(emergencia.getId_emergencia()).longValue() % 2;
        try(Connection conn = sql2o[db].open()){
            int newId = conn.createQuery("insert into emergencia (nombre, ubicacion, tipo, descripcion) values (:nombre, :ubicacion, :tipo, :descripcion)")
                    .addParameter("nombre", emergencia.getNombre())
                    .addParameter("ubicacion", emergencia.getUbicacion())
                    .addParameter("tipo", emergencia.getTipo())
                    .addParameter("descripcion", emergencia.getDescripcion())
                    .executeUpdate().getKey(Integer.class);
            return newId;
        }
        /*try(Connection conn = sql2o.open()){
            int newId = conn.createQuery("insert into emergencia (nombre, ubicacion, tipo, descripcion) values (:nombre, :ubicacion, :tipo, :descripcion)")
                    .addParameter("nombre", emergencia.getNombre())
                    .addParameter("ubicacion", emergencia.getUbicacion())
                    .addParameter("tipo", emergencia.getTipo())
                    .addParameter("descripcion", emergencia.getDescripcion())
                    .executeUpdate().getKey(Integer.class);
            return newId;
        }*/
    }

    public List<Emergencia> obtenerEmergencia(String id){
        int db = Integer.parseInt(id) % 2;
        try(Connection conn = sql2o[db].open()){
            return conn.createQuery("select * from emergencia where id_emergencia = " + id)
                    .executeAndFetch(Emergencia.class);
        }
        /*try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from emergencia where id_emergencia = " + id)
                    .executeAndFetch(Emergencia.class);
        }*/
    }

    public Object modificarEmergencia(String nombreNuevo, String paramObj, String id){
        int db = Integer.parseInt(id) % 2;
        try(Connection conn = sql2o[db].open()){
            return conn.createQuery("update emergencia set " + paramObj + "= '" + nombreNuevo + "' where id_emergencia = " + id)
                    .executeUpdate().getKey();
        }
       /* try(Connection conn = sql2o.open()){
            return conn.createQuery("update emergencia set " + paramObj + "= '" + nombreNuevo + "' where id_emergencia = " + id)
                    .executeUpdate().getKey();
        }*/
    }

    public Object eliminarEmergencia(String id){
        int db = Integer.parseInt(id) % 2;
        try(Connection conn = sql2o[db].open()){
            return conn.createQuery("delete from emergencia where id_emergencia=" + id)
                    .executeUpdate().getKey();
        }
        /*try(Connection conn = sql2o.open()){
            return conn.createQuery("delete from emergencia where id_emergencia=" + id)
                    .executeUpdate().getKey();
        }*/
    }

    public List<Tarea> obtenerTarea(String id){
        int db = Integer.parseInt(id) % 2;
        try(Connection conn = sql2o[db].open()){
            return conn.createQuery("select t from emergencia e, tarea t, emergencia_tarea et where e.id_emergencia = " + id + " and " +
                    "e.id_emergencia = et.id_emergencia and et.id_tarea = t.id_tarea")
                    .executeAndFetch(Tarea.class);
        }
        /*try(Connection conn = sql2o.open()){
            return conn.createQuery("select t from emergencia e, tarea t, emergencia_tarea et where e.id_emergencia = " + id + " and " +
                    "e.id_emergencia = et.id_emergencia and et.id_tarea = t.id_tarea")
                    .executeAndFetch(Tarea.class);
        }*/
    }

}
