package controllersSql2o;

import models.Voluntario;
import models.Dimension_vol;
import models.Dimension;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class VoluntarioSql2o {
    private Sql2o sql2o;
    public VoluntarioSql2o(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<Voluntario> getAllVoluntarios(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from voluntario")
                    .executeAndFetch(Voluntario.class);
        }
    }

    public List<Voluntario> getAllVoluntariosPaginated(int limit, int offset){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from voluntario limit :limit offset :offset")
                    .addParameter("limit", limit)
                    .addParameter("offset", offset)
                    .executeAndFetch(Voluntario.class);
        }
    }

    public int totalVoluntarios(){
        try(Connection conn = sql2o.open()){
            int cantidad = conn.createQuery("select count(v) from voluntario v")
                    .executeScalar(Integer.class);
            return cantidad;
        }
    }


    public long crearVoluntario(Voluntario voluntario){
        try(Connection conn = sql2o.open()){
            long newId = conn.createQuery("insert into voluntario(nombre, apellido, correo, sexo) values (:nombre, :apellido, :correo, :sexo)")
                    .addParameter("nombre", voluntario.getNombre())
                    .addParameter("apellido", voluntario.getApellido())
                    .addParameter("correo", voluntario.getCorreo())
                    .addParameter("sexo", voluntario.getSexo())
                    .executeUpdate().getKey(Long.class);
            return newId;
        }
    }

    public List<Voluntario> obtenerVoluntario(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from voluntario where id_voluntario = " + id)
                    .executeAndFetch(Voluntario.class);
        }
    }

    public Object modificarVoluntario(String nombreNuevo, String paramObj, String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("update voluntario set " + paramObj + "= '" + nombreNuevo + "' where id_voluntario = " + id)
                    .executeUpdate().getKey();
        }
    }

    public Object eliminarVoluntario(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("delete from dimension_voluntario where id_voluntario=" + id +
                    ";delete from voluntario where id_voluntario=" + id)
                    .executeUpdate().getKey();
        }
    }

    public List<Dimension_vol> obtenerDimensionVoluntario(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select d.id_dimension as id_dimension, d.nombre as nombre, dv.valor as valor" +
                    " from  dimension d , dimension_voluntario dv" +
                    " where dv.id_voluntario = " + id + " and dv.id_dimension = d.id_dimension ")
                    .executeAndFetch(Dimension_vol.class);
        }
    }
}
