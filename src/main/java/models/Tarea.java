package models;

public class Tarea {

    private long id_tarea;
    private String titulo;
    private String estado;

    public long getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(long id_tarea) {
        this.id_tarea = id_tarea;
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
