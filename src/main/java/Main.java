import com.google.gson.Gson;
import controllersSql2o.DogSql2o;
import controllersSql2o.EmergenciaSql2o;
import controllersSql2o.UsuarioSql2o;
import controllersSql2o.VoluntarioSql2o;
import models.Dog;
import models.Emergencia;
import models.Usuario;
import models.Voluntario;
import org.sql2o.Sql2o;

import static spark.Spark.*;


public class Main {

    public static void main(String[] args) {

        Sql2o sql2o = new Sql2o("jdbc:postgresql://127.0.0.1:5432/TBD","tbduser","tbdpass");
        DogSql2o dogSql2o = new DogSql2o(sql2o);
        EmergenciaSql2o emergenciaSql2o = new EmergenciaSql2o(sql2o);
        UsuarioSql2o usuarioSql2o = new UsuarioSql2o(sql2o);
        VoluntarioSql2o voluntarioSql2o = new VoluntarioSql2o(sql2o);

//CRUD dog
        get("/", (req, res) -> "{\"mensaje\":\"Corriendo\"}");

        get("/dogs", (req, res)->{
            return new Gson().toJson(dogSql2o.getAllDogs());
        });

        /*get("/dogs", (req, res)->{
            int limit = 10;
            if ( req.queryParams("limit")!= null){
                limit = Integer.valueOf(req.queryParams("limit"));
            }

            int offset = 0;
            if(req.queryParams("offset")!=null){
                offset = Integer.valueOf(req.queryParams("offset"));
            }

            return new Gson().toJson(dogSql2o.getAllDogsPaginated(limit, offset));
        });*/
        post("/dogs", (req, res)->{
            Dog dog = new Gson().fromJson(req.body(), Dog.class);
            int result = dogSql2o.createDog(dog);
            res.status(201);
            return result;
        });
//Fin CRUD dog
//CRUD de Usuario-------------------------------------------------------------------------------------------------------

        get("/emergencias", (req, res)->{
            return new Gson().toJson(emergenciaSql2o.getAllEmergencias());
        });

        post("/emergencias", (req, res)->{
            Emergencia emergencia = new Gson().fromJson(req.body(), Emergencia.class);
            int result = emergenciaSql2o.crearEmergencia(emergencia);
            res.status(201);
            return result;
        });

        get("/usuarios", (req, res)->{
            return new Gson().toJson(usuarioSql2o.obtenerTodosUsuarios());
        });

        get("/usuarios/:id", (req, res)->{
            return new Gson().toJson(usuarioSql2o.obtenerUsuario(req.params(":id")));
        });

        post("/usuarios", (req, res)->{
            Usuario usuario = new Gson().fromJson(req.body(), Usuario.class);
            int result = usuarioSql2o.crearUsuario(usuario);
            res.status(201);
            return result;
        });

        get("/voluntarios", (req, res)->{
            return new Gson().toJson(voluntarioSql2o.getAllVoluntarios());
        });

        post("/voluntarios", (req, res)->{
            Voluntario voluntario = new Gson().fromJson(req.body(), Voluntario.class);
            int result = voluntarioSql2o.crearVoluntario(voluntario);
            res.status(201);
            return result;
        });

        after((req, res) -> {
            res.type("application/json");
        });
    }
}
