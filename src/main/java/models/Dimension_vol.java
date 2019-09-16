package models;

public class Dimension_vol {

    private long id_dimension;
    private long id_voluntario;
    private String nombre;
    private int valor;

    public long getId() {
        return id_dimension;
    }

    public void setId(long id_dimension) {
        this.id_dimension = id_dimension;
    }

    public long getId_voluntario() {
        return id_voluntario;
    }

    public void setId_voluntario(long id_voluntario) {
        this.id_voluntario = id_voluntario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }


    public Dimension_vol(String nombre, int valor) {
        this.nombre = nombre;
        this.valor = valor;
    }
}
