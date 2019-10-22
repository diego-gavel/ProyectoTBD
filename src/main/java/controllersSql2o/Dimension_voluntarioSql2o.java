package controllersSql2o;

import models.Dimension_vol;
import models.Voluntario;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.*;
import java.util.List;

public class Dimension_voluntarioSql2o {

    private Sql2o sql2o;
    private Sql2o sql2o_v[];
    private VoluntarioSql2o voluntarioSql2o = new VoluntarioSql2o(sql2o_v);
    public Dimension_voluntarioSql2o(Sql2o sql2o, VoluntarioSql2o voluntarioSql2o )
    {
        this.sql2o = sql2o;
        this.voluntarioSql2o = voluntarioSql2o;
    }


    public void crearDim_vol(Dimension_vol dim_vol){
        try(Connection conn = sql2o.open()){
            int newId = conn.createQuery("insert into dimension_voluntario (id_voluntario, id_dimension, valor) values (:id_voluntario, :id_dimension, :valor)")
                    .addParameter("id_voluntario", dim_vol.getId_voluntario())
                    .addParameter("id_dimension", dim_vol.getId_dimension())
                    .addParameter("valor", dim_vol.getValor())
                    .executeUpdate().getKey(Integer.class);
        }
    }

    public List<Dimension_vol> getAllDim_vol(){
        //return dimensions_vol;
       try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from dimension_voluntario")
                    .executeAndFetch(Dimension_vol.class);
        }
    }

    public List<Dimension_vol> getAllDim_volPaginated(int limit, int offset){
       try (Connection conn = sql2o.open()) {
            return conn.createQuery("select * from dimension_voluntario limit :limit offset :offset")
                    .addParameter("limit", limit)
                    .addParameter("offset", offset)
                    .executeAndFetch(Dimension_vol.class);
        }
    }

    public void llenarTablaVoluntario(){
        String fila;
        BufferedReader csvReader = null;

        try {
            csvReader = new BufferedReader(new FileReader("TBD VOLUNTARIOS.csv"));
            fila = csvReader.readLine();
            while ( (fila = csvReader.readLine()) != null){
                String [] datos = fila.split("\\[");
                datos[1] = datos[1].replace("\"","");
                datos[1] = datos[1].replace("name:", "");
                datos[1] = datos[1].replace("score:", "");
                datos[1] = datos[1].replace("},{", ";");
                datos[1] = datos[1].replace("}],", "");
                datos[1] = datos[1].replace("{", "");
                datos[1] = datos[1].replace("Fuerza,", "");
                datos[1] = datos[1].replace("Destreza,", "");
                datos[1] = datos[1].replace("Liderazgo,", "");
                datos[1] = datos[1].replace("Motivacion,", "");
                datos[1] = datos[1].replace("Conocimiento,", "");
                datos[1] = datos[1].replace(",", "");
                String[] dimensiones = datos[1].split(";");
                datos[0] = datos[0].replace(",\"", "");
                String[] datosVoluntario = datos[0].split(",");
                //Se crea e inserta voluntario
                Voluntario voluntario = new Voluntario(datosVoluntario[1], datosVoluntario[2], datosVoluntario[3], datosVoluntario[4]);
                System.out.println(datosVoluntario[0]);
                voluntarioSql2o.crearVoluntario(voluntario);
                //Se crea e inserta cada tabla intermedia del voluntario
                //Fuerza
                Dimension_vol DVFuerza = new Dimension_vol(Long.parseLong(datosVoluntario[0]),1, "fuerza", Integer.parseInt(dimensiones[0]));
                crearDim_vol(DVFuerza);
                //Destreza
                Dimension_vol DVDestreza = new Dimension_vol(Long.parseLong(datosVoluntario[0]),2, "destreza", Integer.parseInt(dimensiones[1]));
                crearDim_vol(DVDestreza);
                //Liderazgo
                Dimension_vol DVLiderazgo = new Dimension_vol(Long.parseLong(datosVoluntario[0]),3, "liderazgo", Integer.parseInt(dimensiones[2]));
                crearDim_vol(DVLiderazgo);
                //Motivacion
                Dimension_vol DVMotivacion = new Dimension_vol(Long.parseLong(datosVoluntario[0]),4, "motivacion", Integer.parseInt(dimensiones[3]));
                crearDim_vol(DVMotivacion);
                //Conocimiento
                Dimension_vol DVConocimiento = new Dimension_vol(Long.parseLong(datosVoluntario[0]),5, "conocimiento", Integer.parseInt(dimensiones[4]));
                crearDim_vol(DVConocimiento);
            }
        }
        catch (Exception  e) {
            e.printStackTrace();
        }

    }

}
