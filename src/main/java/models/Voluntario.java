package models;

public class Voluntario {

    private long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String sexo;


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


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public Voluntario(String nombre, String apellido, String correo, String sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.sexo = sexo;
    }
}
