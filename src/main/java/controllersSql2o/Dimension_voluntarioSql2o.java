package controllersSql2o;

import models.Dimension_vol;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.*;
import java.util.Arrays;

public class Dimension_voluntarioSql2o {

    private Sql2o sql2o;
    public Dimension_voluntarioSql2o(Sql2o sql2o) { this.sql2o = sql2o; }
    VoluntarioSql2o voluntarioSql2o = new VoluntarioSql2o(sql2o);

    public long crearDim_vol(Dimension_vol dim_vol){
        try(Connection conn = sql2o.open()){
            conn.createQuery("insert into voluntario (id_voluntario, id_dimension, nombre, valor) values (:id_voluntario, :id_dimension, :nombre, :valor)")
                    .addParameter("id_voluntario", dim_vol.getId_voluntario())
                    .addParameter("id_dimension", dim_vol.getId_dimension())
                    .addParameter("nombre", dim_vol.getNombre())
                    .addParameter("valor", dim_vol.getValor())
                    .executeUpdate().getKey(Integer.class);
            return dim_vol.getId_voluntario();
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
                String[] dimensiones = datos[1].split(";");
                datos[0] = datos[0].replace(",\"", "");
                String[] datosVoluntario = datos[0].split(",");

                System.out.println(dimensiones[1]);
                //System.out.println(Arrays.toString(datosVoluntario));
                //System.out.println(Arrays.toString(dimensiones));

            }
        }
        catch (Exception  e) {
            e.printStackTrace();
        }

    }

}
