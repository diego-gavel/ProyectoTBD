package controllersSql2o;

import models.Voluntario;
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

    public int crearVoluntario(Voluntario voluntario){
        try(Connection conn = sql2o.open()){
            int newId = conn.createQuery("insert into voluntario(nombre, edad, correo, celular, rut, estatura,peso) values (:nombre, :edad, :correo, :celular, :rut, :estatura, :peso)")
                    .addParameter("nombre", voluntario.getNombre())
                    .addParameter("edad", voluntario.getEdad())
                    .addParameter("correo", voluntario.getCorreo())
                    .addParameter("celular", voluntario.getCelular())
                    .addParameter("rut", voluntario.getRut())
                    .addParameter("estatura", voluntario.getEstatura())
                    .addParameter("peso", voluntario.getPeso())
                    .executeUpdate().getKey(Integer.class);
            return newId;
        }
    }
}
