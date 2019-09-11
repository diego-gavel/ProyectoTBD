package controllersSql2o;

import models.Tarea;
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

    public int crearTarea(Tarea tarea){
        try(Connection conn = sql2o.open()){
            int newId = conn.createQuery("insert into tarea(titulo, estado) values (:titulo, :estado)")
                    .addParameter("titulo", tarea.getTitulo())
                    .addParameter("estado", tarea.getEstado())
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
}
