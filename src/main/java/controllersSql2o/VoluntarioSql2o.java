package controllersSql2o;

import models.Dimension_vol;
import models.Voluntario;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class VoluntarioSql2o {
    private Sql2o sql2o;
    //private Sql2o sql2o[];
    public VoluntarioSql2o(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<Voluntario> getAllVoluntarios(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select id_voluntario, nombre, apellido, correo, sexo, latitude, longitude from voluntario")
                    .executeAndFetch(Voluntario.class);
        }

    }

    public List<Voluntario> getAllVoluntariosPaginated(int limit, int offset){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select id_voluntario, nombre, apellido, correo, sexo, latitude, longitude from voluntario limit :limit offset :offset")
                    .addParameter("limit", limit)
                    .addParameter("offset", offset)
                    .executeAndFetch(Voluntario.class);
        }
        /*try {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            List<Voluntario>[] voluntarios = new ArrayList[2];
            for (int i = 0; i < 2; i++) {
                final int db = i;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try (Connection conn = sql2o[db].open()) {
                            voluntarios[db] = conn.createQuery("select * from voluntario limit :limit offset :offset")
                                    .addParameter("limit", limit)
                                    .addParameter("offset", offset)
                                    .executeAndFetch(Voluntario.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24 * 3600, TimeUnit.SECONDS);
            List<Voluntario> merged = new ArrayList<Voluntario>();
            for( int i = 0; i < 2; i++){
                merged.addAll(voluntarios[i]);
            }
            return merged;
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from voluntario limit :limit offset :offset")
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select id_voluntario, nombre, apellido, correo, sexo, latitude, longitude from voluntario limit :limit offset :offset")
                    .addParameter("limit", limit)
                    .addParameter("offset", offset)
                    .executeAndFetch(Voluntario.class);
        }*/
    }

    public int totalVoluntarios(){
        try(Connection conn = sql2o.open()){
            int cantidad = conn.createQuery("select count(v) from voluntario v")
                    .executeScalar(Integer.class);
            return cantidad;
        }

        /*try {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            int total_voluntarios = 0;
            List<String>[] subtotal_voluntarios = new ArrayList[2];
            for (int i = 0; i < 2; i++) {
                final int db = i;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try (Connection conn = sql2o[db].open()) {
                            subtotal_voluntarios[db] = conn.createQuery("select count(d) from voluntario v")
                                    .executeAndFetch(String.class);
                        }
                    }
                });
            }
            executor.shutdown();
            executor.awaitTermination(24 * 3600, TimeUnit.SECONDS);
            for (int j = 0; j < 2; j++) {
                total_voluntarios = total_voluntarios + Integer.parseInt(subtotal_voluntarios[j].get(0));
            }
            return total_voluntarios;
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
        try(Connection conn = sql2o.open()){
            int cantidad = conn.createQuery("select count(v) from voluntario v")
                    .executeScalar(Integer.class);
            return cantidad;
        }*/
    }


    public long crearVoluntario(Voluntario voluntario){
        try(Connection conn = sql2o.open()){
            long newId = conn.createQuery("insert into voluntario(nombre, apellido, correo, sexo, latitude, longitude, location) values (:nombre, :apellido, :correo, :sexo, :latitude, :longitude, ST_SetSRID(CAST(:location AS geometry), 4326))")
                    .addParameter("nombre", voluntario.getNombre())
                    .addParameter("apellido", voluntario.getApellido())
                    .addParameter("correo", voluntario.getCorreo())
                    .addParameter("sexo", voluntario.getSexo())
                    .addParameter("latitude", voluntario.getLatitude())
                    .addParameter("longitude", voluntario.getLongitude())
                    .addParameter("location", "POINT(" + voluntario.getLatitude() + " " + voluntario.getLongitude()+ ")")
                    .executeUpdate().getKey(Long.class);
            System.out.println("Se agrego");
            return newId;
        }
        /*int db = (int)voluntario.getId_voluntario() % 2;
        try(Connection conn = sql2o[db].open()){
            int newId = conn.createQuery("insert into voluntario(nombre, apellido, correo, sexo,latitude, longitude , location) values (:nombre, :apellido, :correo, :sexo, :latitude, :longitude,:location)")
        try(Connection conn = sql2o.open()){
            long newId = conn.createQuery("insert into voluntario(nombre, apellido, correo, sexo, latitude, longitude, location) values (:nombre, :apellido, :correo, :sexo, :latitude, :longitude, ST_SetSRID(CAST(:location AS geometry), 4326))")
                    .addParameter("nombre", voluntario.getNombre())
                    .addParameter("apellido", voluntario.getApellido())
                    .addParameter("correo", voluntario.getCorreo())
                    .addParameter("sexo", voluntario.getSexo())



                    .addParameter("latitude", voluntario.getLatitude())
                    .addParameter("longitude", voluntario.getLongitude())
                    .addParameter("location", "POINT(" + voluntario.getLatitude() + " " + voluntario.getLongitude()+ ")")
                    .executeUpdate().getKey(Long.class);
                    System.out.println("Se agrego");
            return newId;
        }*/
        /*try(Connection conn = sql2o.open()){
            long newId = conn.createQuery("insert into voluntario(nombre, apellido, correo, sexo,latitude, longitude , location) values (:nombre, :apellido, :correo, :sexo, :latitude, :longitude,:location)") /*Agrege location longitude latitude*/
                   /* .addParameter("nombre", voluntario.getNombre())
                    .addParameter("apellido", voluntario.getApellido())
                    .addParameter("correo", voluntario.getCorreo())
                    .addParameter("sexo", voluntario.getSexo())*/


                    /*Verificar esto, porque es necesario colocar en la tabla voluntario latitude y longitude*/
        /*            .addParameter("latitude", voluntario.getLatitude())
                    .addParameter("longitude", voluntario.getLongitude())
                    .addParameter("location", "ST_GeomFromText('POINT("+voluntario.getLatitude() +" "+voluntario.getLongitude()+")', 4326)") /*Esto agrege*/
        /*            .executeUpdate().getKey(Long.class);
            return newId;
        }*/
    }

    public List<Voluntario> obtenerVoluntario(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from voluntario where id_voluntario = " + id)
                    .executeAndFetch(Voluntario.class);
        }
        /*int db = Integer.parseInt(id) % 2;
        try(Connection conn = sql2o[db].open()){
            return conn.createQuery("select * from voluntario where id_voluntario = " + id)
                    .executeAndFetch(Voluntario.class);
        }*/
       /* try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from voluntario where id_voluntario = " + id)
                    .executeAndFetch(Voluntario.class);
        }*/
    }

    public Object modificarVoluntario(String nombreNuevo, String paramObj, String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("update voluntario set " + paramObj + "= '" + nombreNuevo + "' where id_voluntario = " + id)
                    .executeUpdate().getKey();
        }
        /*int db = Integer.parseInt(id) % 2;
        try(Connection conn = sql2o[db].open()){
            return conn.createQuery("update voluntario set " + paramObj + "= '" + nombreNuevo + "' where id_voluntario = " + id)
                    .executeUpdate().getKey();
        }
        try(Connection conn = sql2o.open()){
            return conn.createQuery("update voluntario set " + paramObj + "= '" + nombreNuevo + "' where id_voluntario = " + id)
                    .executeUpdate().getKey();
        }*/
    }

    public Object eliminarVoluntario(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("delete from dimension_voluntario where id_voluntario=" + id +
                    ";delete from voluntario where id_voluntario=" + id)
                    .executeUpdate().getKey();
        }
        /*int db = Integer.parseInt(id) % 2;
        try(Connection conn = sql2o[db].open()){
            return conn.createQuery("delete from dimension_voluntario where id_voluntario=" + id +
                    ";delete from voluntario where id_voluntario=" + id)
                    .executeUpdate().getKey();
        }
        try(Connection conn = sql2o.open()){
            return conn.createQuery("delete from dimension_voluntario where id_voluntario=" + id +
                    ";delete from voluntario where id_voluntario=" + id)
                    .executeUpdate().getKey();
        }*/
    }

    public List<Dimension_vol> obtenerDimensionVoluntario(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select d.id_dimension as id_dimension, d.nombre as nombre, dv.valor as valor" +
                    " from  dimension d , dimension_voluntario dv" +
                    " where dv.id_voluntario = " + id + " and dv.id_dimension = d.id_dimension ")
                    .executeAndFetch(Dimension_vol.class);
        }
        /*int db = Integer.parseInt(id) % 2;
        try(Connection conn = sql2o[db].open()){
            return conn.createQuery("select d.id_dimension as id_dimension, d.nombre as nombre, dv.valor as valor" +
                    " from  dimension d , dimension_voluntario dv" +
                    " where dv.id_voluntario = " + id + " and dv.id_dimension = d.id_dimension ")
                    .executeAndFetch(Dimension_vol.class);
        }
        /*try(Connection conn = sql2o.open()){
            return conn.createQuery("select d.id_dimension as id_dimension, d.nombre as nombre, dv.valor as valor" +
                    " from  dimension d , dimension_voluntario dv" +
                    " where dv.id_voluntario = " + id + " and dv.id_dimension = d.id_dimension ")
                    .executeAndFetch(Dimension_vol.class);
        }*/
    }
}
