package models;

public class Usuario {

    private long id_usuario;
    private String nombre;
    private int edad;
    private String correo;
    private String celular;
    private String rut;

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Usuario(String nombre, int edad, String correo, String celular, String rut) {
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
        this.celular = celular;
        this.rut = rut;
    }
}
