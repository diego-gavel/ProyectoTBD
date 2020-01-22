package controllersSql2o;

import models.Emergencia;
import models.Tarea;
import models.Voluntario;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class TareaSql2o {
    private Sql2o sql2o;
    public TareaSql2o(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<Tarea> getAllTareas(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from tarea")
                    .executeAndFetch(Tarea.class);
        }
    }

    public List<Tarea> getAllTareasPaginated(int limit, int offset){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from tarea limit :limit offset :offset")
                    .addParameter("limit", limit)
                    .addParameter("offset", offset)
                    .executeAndFetch(Tarea.class);
        }
    }

    public int totalTareas(){
        try(Connection conn = sql2o.open()){
            int cantidad = conn.createQuery("select count(t) from tarea t")
                    .executeScalar(Integer.class);
            return cantidad;
        }
    }


    public int crearTarea(Tarea tarea){
        try(Connection conn = sql2o.open()){
            int newId = conn.createQuery("insert into tarea(titulo, estado, id_voluntario, id_emergencia) values (:titulo, :estado, :id_voluntario, :id_emergencia)")
                    .addParameter("titulo", tarea.getTitulo())
                    .addParameter("estado", tarea.getEstado())
                    .addParameter("id_voluntario", tarea.getId_voluntario())
                    .addParameter("id_emergencia", tarea.getId_emergencia())
                    .executeUpdate().getKey(Integer.class);
            return newId;
        }
    }

    public List<Tarea> obtenerTarea(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from tarea where id_tarea = " + id)
                    .executeAndFetch(Tarea.class);
        }
    }

    public Object modificarTarea(String nombreNuevo, String paramObj, String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("update tarea set " + paramObj + "= '" + nombreNuevo + "' where id_tarea = " + id)
                    .executeUpdate().getKey();
        }
    }

    public Object eliminarTarea(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("delete from tarea where id_tarea=" + id)
                    .executeUpdate().getKey();
        }
    }

    public List<Emergencia> obtenerEmergencia(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select e from emergencia e, tarea t, emergencia_tarea et where t.id_tarea = " + id + " and " +
                    "t.id_tarea = et.id_tarea and et.id_emergencia = t.id_emergencia")
                    .executeAndFetch(Emergencia.class);
        }
    }

    public List<Voluntario> obtenerVoluntario(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select v.id_voluntario as id_voluntario, v.nombre as nombre, " +
                    "v.apellido as apellido, v.correo as correo, v.sexo as sexo from tarea t, voluntario v " +
                    "where t.id_voluntario = " + id + " and " +
                    "t.id_voluntario = v.id_voluntario")
                    .executeAndFetch(Voluntario.class);
        }
    }

}
