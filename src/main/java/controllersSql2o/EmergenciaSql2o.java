package controllersSql2o;

import models.Emergencia;
import models.Tarea;
import models.Voluntario;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;


public class EmergenciaSql2o {
    private Sql2o sql2o;
    public EmergenciaSql2o(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<Emergencia> getAllEmergencias(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select id_emergencia, nombre, tipo, descripcion, latitude, longitude from emergencia")
                    .executeAndFetch(Emergencia.class);
        }
    }

    public List<Emergencia> getAllEmergenciasPaginated(int limit, int offset){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select id_emergencia, nombre, tipo, descripcion, latitude, longitude from emergencia limit :limit offset :offset")
                    .addParameter("limit", limit)
                    .addParameter("offset", offset)
                    .executeAndFetch(Emergencia.class);
        }
    }

    public int totalEmergencias(){
        try(Connection conn = sql2o.open()){
            int cantidad = conn.createQuery("select count(e) from emergencia e")
                    .executeScalar(Integer.class);
            return cantidad;
        }
    }

    public int crearEmergencia(Emergencia emergencia){
        try(Connection conn = sql2o.open()){
            int newId = conn.createQuery("insert into emergencia (nombre, tipo, descripcion, latitude, longitude, location) values (:nombre, :tipo, :descripcion, :latitude, :longitude, ST_SetSRID(CAST(:location AS geometry), 4326))")
                    .addParameter("nombre", emergencia.getNombre())
                    .addParameter("tipo", emergencia.getTipo())
                    .addParameter("descripcion", emergencia.getDescripcion())
                    .addParameter("latitude", emergencia.getLatitude())
                    .addParameter("longitude", emergencia.getLongitude())
                    .addParameter("location", "POINT(" + emergencia.getLatitude() + " " + emergencia.getLongitude()+ ")")
                    .executeUpdate().getKey(Integer.class);
            System.out.println("Se agrego emergencia.");
            return newId;
        }
    }

    public List<Emergencia> obtenerEmergencia(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select id_emergencia, nombre, latitude, longitude, tipo from emergencia where id_emergencia = "+id)
                    .executeAndFetch(Emergencia.class);
        }
    }

    public List<Emergencia> obtenerEmergenciaNombre(String nombre){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select id_emergencia, nombre, latitude, longitude from emergencia where nombre = ads")
                    .executeAndFetch(Emergencia.class);
        }
    }

    public Object modificarEmergencia(String nombreNuevo, String paramObj, String id){
       try(Connection conn = sql2o.open()){
            return conn.createQuery("update emergencia set " + paramObj + "= '" + nombreNuevo + "' where id_emergencia = " + id)
                    .executeUpdate().getKey();
        }
    }

    public Object eliminarEmergencia(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("delete from emergencia where id_emergencia=" + id)
                    .executeUpdate().getKey();
        }
    }

    public List<Tarea> obtenerTarea(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select t from emergencia e, tarea t, emergencia_tarea et where e.id_emergencia = " + id + " and " +
                    "e.id_emergencia = et.id_emergencia and et.id_tarea = t.id_tarea")
                    .executeAndFetch(Tarea.class);
        }
    }
    public List<Voluntario> buscarVoluntariosCercanos(String id){
        try(Connection conn = sql2o.open()) {
            return conn.createQuery("select id_voluntario from emergencia e, voluntario v  " +
                    "where |/((e.latitude - v.latitude)^2 + (e.longitude - v.longitude)^2) <= e.radius and " +
                    "e.id_emergencia =" + id)
                    .executeAndFetch(Voluntario.class);
        }
    }
    public List<Voluntario> buscarNVoluntariosCercanos(String id, String total){
        try(Connection conn = sql2o.open()) {
            return conn.createQuery("select TOP " + total + " v from emergencia e, voluntario v  " +
                    "where SQRT(SQUARE(e.latitude - v.latitude) + SQUARE(e.longitude - v.longitude)) <= e.radius and " +
                    "e.id_emergencia =" + id)
                    .executeAndFetch(Voluntario.class);
        }
    }
}
