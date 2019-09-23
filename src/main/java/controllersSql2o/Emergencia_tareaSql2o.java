package controllersSql2o;

import models.Emergencia;
import models.Emergencia_tarea;
import models.Tarea;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Emergencia_tareaSql2o {
    private Sql2o sql2o;
    public Emergencia_tareaSql2o(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<Emergencia_tarea> getAllEmergencias(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from emergencia_tarea")
                    .executeAndFetch(Emergencia_tarea.class);
        }
    }

    public int crearEmergencia_tarea(Emergencia_tarea emergencia_tarea){
        try(Connection conn = sql2o.open()){
            int newId = conn.createQuery("insert into emergencia_tarea (id_emergencia, id_tarea) values (:id_emergencia, :id_tarea)")
                    .addParameter("id_emergencia", emergencia_tarea.getId_emergencia())
                    .addParameter("id_tarea", emergencia_tarea.getId_tarea())
                    .executeUpdate().getKey(Integer.class);
            return newId;
        }
    }

    public List<Emergencia_tarea> obtenerEmergencia_tarea(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from emergencia_tarea where id = " + id)
                    .executeAndFetch(Emergencia_tarea.class);
        }
    }

    public Object modificarEmergencia(String nombreNuevo, String paramObj, String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("update emergencia_tarea set " + paramObj + "= '" + nombreNuevo + "' where id = " + id)
                    .executeUpdate().getKey();
        }
    }

    public Object eliminarEmergencia_tarea(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("delete from emergencia_tarea where id =" + id)
                    .executeUpdate().getKey();
        }
    }

    public List<Tarea> obtenerTareaDeEmergencia(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select t.id_tarea as id_tarea, t.titulo as titulo, t.estado as estado from emergencia e, tarea t, emergencia_tarea et where e.id_emergencia = " + id + " and " +
                    "e.id_emergencia = et.id_emergencia and et.id_tarea = t.id_tarea")
                    .executeAndFetch(Tarea.class);
        }
    }

    public List<Tarea> obtenerTareaDeEmergenciaPaginated(int limit, int offset, int idd){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select t.id_tarea as id_tarea, t.titulo as titulo, t.estado as estado" +
                    " from emergencia e, tarea t where e.id_emergencia = :idd and " +
                    "e.id_emergencia = t.id_emergencia  limit :limit offset :offset")
                    .addParameter("limit", limit)
                    .addParameter("offset", offset)
                    .addParameter("idd", idd)
                    .executeAndFetch(Tarea.class);
        }
    }

    public int totalTareas(int idd){
        try(Connection conn = sql2o.open()){
            int cantidad = conn.createQuery("select count(t)" +
                    " from emergencia e, tarea t where e.id_emergencia = :idd and " +
                    "e.id_emergencia = t.id_emergencia ")
                    .addParameter("idd", idd)
                    .executeScalar(Integer.class);
            return cantidad;
        }
    }

    public List<Emergencia> obtenerEmergenciaDeTarea(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select e.id_emergencia as id_emergencia, e.nombre as nombre, e.ubicacion as ubicacion, " +
                    " e.tipo as tipo, e.descripcion as descripcion  from emergencia e, tarea t, emergencia_tarea et where t.id_tarea = " + id + " and " +
                    "t.id_tarea = et.id_tarea and et.id_emergencia = e.id_emergencia")
                    .executeAndFetch(Emergencia.class);
        }
    }

}
