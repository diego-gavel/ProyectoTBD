package controllersSql2o;

import models.Usuario;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class UsuarioSql2o {
    private Sql2o sql2o;
    public UsuarioSql2o(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public List<Usuario> getAllUsuarios(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from usuario")
                    .executeAndFetch(Usuario.class);
        }
    }

    public int crearUsuario(Usuario usuario){
        try(Connection conn = sql2o.open()){
            int newId = conn.createQuery("insert into usuario(nombre, edad, correo, celular, rut) values (:nombre, :edad, :correo, :celular, :rut)")
                    .addParameter("nombre", usuario.getNombre())
                    .addParameter("edad", usuario.getEdad())
                    .addParameter("correo", usuario.getCorreo())
                    .addParameter("celular", usuario.getCelular())
                    .addParameter("rut", usuario.getRut())
                    .executeUpdate().getKey(Integer.class);
            return newId;
        }
    }
}
