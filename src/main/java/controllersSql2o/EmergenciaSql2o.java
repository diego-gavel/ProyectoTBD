package controllersSql2o;

        import models.Emergencia;
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
            int newId = conn.createQuery("insert into emergencia (nombre, ubicacion, tipo, descripcion) values (:nombre :ubicacion :tipo :descripcion)")
                    .addParameter("nombre", emergencia.getNombre())
                    .addParameter("ubicacion", emergencia.getUbicacion())
                    .addParameter("tipo", emergencia.getTipo())
                    .addParameter("descripcion", emergencia.getDescripcion())
                    .executeUpdate().getKey(Integer.class);
            return newId;
        }
    }
}
