package controllersSql2o;

import models.Dimension;
import models.Voluntario;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class DimensionSql2o {
    private Sql2o sql2o;
    public DimensionSql2o(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<Dimension> getAllDimensiones(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from dimension")
                    .executeAndFetch(Dimension.class);
        }
    }

    public int crearDimension(Dimension dimension){
        try(Connection conn = sql2o.open()){
            int newId = conn.createQuery("insert into dimension(nombre) values (:nombre)")
                    .addParameter("nombre", dimension.getNombre())
                    .executeUpdate().getKey(Integer.class);
            return newId;
        }
    }

    public List<Dimension> obtenerDimension(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from dimension where id_dimension = " + id)
                    .executeAndFetch(Dimension.class);
        }
    }


    public List<Voluntario> obtenerVoluntarioDimension(String id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select v from voluntario v , dimension_voluntario dv" +
                    " where dv.id_dimension = " + id + " and dv.id_voluntario = v.id_voluntario ")
                    .executeAndFetch(Voluntario.class);
        }
    }

}
