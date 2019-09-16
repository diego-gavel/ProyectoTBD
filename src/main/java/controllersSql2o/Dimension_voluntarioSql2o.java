package controllersSql2o;

import java.io.*;

public class Dimension_voluntarioSql2o {
    public static final String SEPARATOR=";";
    public void llenarTablaVoluntario(){
        String fila;
        BufferedReader csvReader = null;


        try {
            csvReader = new BufferedReader(new FileReader("TBD VOLUNTARIOS.csv"));

            while ( (fila = csvReader.readLine()) != null){
                String [] fields = fila.split(SEPARATOR);

            }
        }
        catch (Exception  e) {
            e.printStackTrace();
        }

    }

}
