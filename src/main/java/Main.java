import com.google.gson.Gson;
import controllersSql2o.DogSql2o;
import controllersSql2o.EmergenciaSql2o;
import controllersSql2o.Emergencia_tareaSql2o;
import controllersSql2o.TareaSql2o;
import controllersSql2o.UsuarioSql2o;
import controllersSql2o.VoluntarioSql2o;
import controllersSql2o.DimensionSql2o;
import models.*;
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
        Emergencia_tareaSql2o emergencia_tareaSql2o = new Emergencia_tareaSql2o(sql2o);
        UsuarioSql2o usuarioSql2o = new UsuarioSql2o(sql2o);
        VoluntarioSql2o voluntarioSql2o = new VoluntarioSql2o(sql2o);
        DimensionSql2o dimensionSql2o = new DimensionSql2o(sql2o);
        TareaSql2o tareaSql2o = new TareaSql2o(sql2o);

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

        get("/emergencias/:id", (req, res)->{
            return new Gson().toJson(emergenciaSql2o.obtenerEmergencia(req.params(":id")));
        });
        
        post("/emergencias", (req, res)->{
            Emergencia emergencia = new Gson().fromJson(req.body(), Emergencia.class);
            int result = emergenciaSql2o.crearEmergencia(emergencia);
            res.status(201);
            return result;
        });

        put("/emergencias/:id/:paramObj/:nuevoParam", (req, res)->{
            return new Gson().toJson(emergenciaSql2o.modificarEmergencia(req.params(":nuevoParam"), req.params(":paramObj"),req.params(":id")));
        });

        delete("/emergencias/:id", (req, res)->{
            return emergenciaSql2o.eliminarEmergencia(req.params(":id"));
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

        put("/usuarios/:id/:paramObj/:nuevoParam", (req, res)->{
            return new Gson().toJson(usuarioSql2o.modificarUsuario(req.params(":nuevoParam"), req.params(":paramObj"),req.params(":id")));
        });

        delete("/usuarios/:id", (req, res)->{
            return usuarioSql2o.eliminarUsuario(req.params(":id"));
        });

//CRUD voluntarios

        get("/voluntarios", (req, res)->{
            return new Gson().toJson(voluntarioSql2o.getAllVoluntarios());
        });

        get("/voluntarios/:id", (req, res)->{
            return new Gson().toJson(voluntarioSql2o.obtenerVoluntario(req.params(":id")));
        });

        post("/voluntarios", (req, res)->{
            Voluntario voluntario = new Gson().fromJson(req.body(), Voluntario.class);
            int result = voluntarioSql2o.crearVoluntario(voluntario);
            res.status(201);
            return result;
        });

        put("/voluntarios/:id/:paramObj/:nuevoParam", (req, res)->{
            return new Gson().toJson(voluntarioSql2o.modificarVoluntario(req.params(":nuevoParam"), req.params(":paramObj"),req.params(":id")));
        });

        delete("/voluntarios/:id", (req, res)->{
            return voluntarioSql2o.eliminarVoluntario(req.params(":id"));
        });

        after((req, res) -> {
            res.type("application/json");
        });

        get("/voluntarios/dimensiones/:id", (req, res)->{
            return new Gson().toJson(voluntarioSql2o.obtenerDimensionVoluntario(req.params(":id")));
        });

        get("/dimensiones/voluntarios/:id", (req, res)->{
            return new Gson().toJson(dimensionSql2o.obtenerVoluntarioDimension(req.params(":id")));
        });

        get("/tareas/emergencias/:id", (req, res)->{
            return new Gson().toJson(emergencia_tareaSql2o.obtenerEmergenciaDeTarea(req.params(":id")));
        });

        get("/emergencias/tareas/:id", (req, res)->{
            return new Gson().toJson(emergencia_tareaSql2o.obtenerTareaDeEmergencia(req.params(":id")));
        });

    }
}
