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

    // Enables CORS on requests. This method is an initialization method and should be called once.
    /*private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            response.header("Access-Control-Expose-Headers", "Pagination-Count, Pagination-Limit, Pagination-Count, Pagination-Page");
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }*/

    public static void main(String[] args) {

        /*enableCORS("*","GET,PUT,POST,DELETE,OPTIONS", "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");*/
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

//CRUD emergencias

        get("/emergencias", (req, res)->{
            return new Gson().toJson(emergenciaSql2o.getAllEmergencias());
        });

        post("/emergencias", (req, res)->{
            Emergencia emergencia = new Gson().fromJson(req.body(), Emergencia.class);
            int result = emergenciaSql2o.crearEmergencia(emergencia);
            res.status(201);
            return result;
        });

//CRUD de Usuario-------------------------------------------------------------------------------------------------------

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

        put("/usuarios/:id/:nuevoNombre", (req, res)->{
            return new Gson().toJson(usuarioSql2o.modificarNombreUsuario((req.params(":nuevoNombre")).toString(),req.params(":id")));
        });

        /*delete("/usuarios/:id", (req, res)->{
            return usuarioSql2o.eliminarUsuario(req.params(":id"));
        });*/

//CRUD voluntarios

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
