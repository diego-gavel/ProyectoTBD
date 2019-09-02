package models;

public class Emergencia {
    private Long id_emergencia;
    private String nombre;
    private String ubicacion;
    private String tipo;
    private String descripcion;

    public Long getId_emergencia() {
        return id_emergencia;
    }

    public void setId_emergencia(Long id_emergencia) {
        this.id_emergencia = id_emergencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Emergencia(String nombre, String ubicacion, String tipo, String descripcion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }







}
