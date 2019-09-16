package controllersSql2o;

import java.io.*;

public class Dimension_voluntarioSql2o {

    public void llenarTablaVoluntario(){
        String fila;
        BufferedReader csvReader = null;


        try {
            csvReader = new BufferedReader(new FileReader("TBD VOLUNTARIOS.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        while ((fila = csvReader.readLine() != null)){
            String[] datos = fila.split(",");

        }

    }

}
