package models;

public class Tarea {

    private long id;
    private String titulo;
    private String estado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }



    public Tarea(String titulo, String estado) {
        this.titulo = titulo;
        this.estado = estado;
    }
}
