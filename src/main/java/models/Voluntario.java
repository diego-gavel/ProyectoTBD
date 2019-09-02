package models;

public class Voluntario {

    private long id;
    private String nombre;
    private int edad;
    private String correo;
    private String celular;
    private String rut;
    private int estatura;
    private int peso;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Voluntario(String nombre, int edad, String correo, String celular, String rut, int estatura,int peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
        this.celular = celular;
        this.rut = rut;
        this.estatura = estatura;
        this.peso = peso;
    }
}
