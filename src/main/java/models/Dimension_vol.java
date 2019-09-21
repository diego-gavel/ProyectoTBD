package models;

public class Dimension_vol {

    private long id;
    private long id_dimension;
    private long id_voluntario;
    private String nombre;
    private int valor;

    public long getId_dimension() {
        return id_dimension;
    }

    public void setId_dimension(long id_dimension) {
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


    public Dimension_vol(long id_voluntario, long id_dimension ,String nombre, int valor) {
        this.id_dimension = id_dimension;
        this.id_voluntario = id_voluntario;
        this.nombre = nombre;
        this.valor = valor;
    }
}
