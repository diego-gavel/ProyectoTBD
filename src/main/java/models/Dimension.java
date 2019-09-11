package models;

public class Dimension {

    private long id_dimension;
    private String nombre;


    public long getId() {
        return id_dimension;
    }

    public void setId(long id_dimension) {
        this.id_dimension = id_dimension;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Dimension(String nombre) {
        this.nombre = nombre;
    }
}
