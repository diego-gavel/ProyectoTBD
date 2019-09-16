package controllersSql2o;

        import models.Emergencia;
        import models.Tarea;
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
            return conn.createQuery("select * from emergencia")
                    .executeAndFetch(Emergencia.class);
        }
    }

    public int crearEmergencia(Emergencia emergencia){
        try(Connection conn = sql2o.open()){
            int newId = conn.createQuery("insert into emergencia (nombre, ubicacion, tipo, descripcion) values (:nombre, :ubicacion, :tipo, :descripcion)")
                    .addParameter("nombre", emergencia.getNombre())
                    .addParameter("ubicacion", emergencia.getUbicacion())
                    .addParameter("tipo", emergencia.getTipo())
                    .addParameter("descripcion", emergencia.getDescripcion())
                    .executeUpdate().getKey(Integer.class);
            return newId;
        }
    }

    public List<Emergencia> obtenerEmergencia(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from emergencia where id_emergencia = " + id)
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

}
