package models;

public class Emergencia_tarea {

    private long id_emergencia;
    private long id_tarea;

    public long getId_emergencia() {
        return id_emergencia;
    }

    public void setId_emergencia(long id_emergencia) {
        this.id_emergencia = id_emergencia;
    }

    public long getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(long id_tarea) {
        this.id_tarea = id_tarea;
    }



    public Emergencia_tarea(long id_emergencia, long id_tarea) {
        this.id_emergencia = id_emergencia;
        this.id_tarea = id_tarea;
    }
}
