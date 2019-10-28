package models;

import java.awt.*;
import org.postgis.*;
import org.postgis.Point;

public class Voluntario {

    private long id_voluntario;
    private String nombre;
    private String apellido;
    private String correo;
    private String sexo;

    private Point location;
    private float latitude;
    private float longitude;


    public Point getLocation() {
        return location;
    }



    public void setLocation(Point location) {
        this.location = location;
    }



    public long getId_voluntario() {
        return id_voluntario;
    }

    public void setId_voluntario(long id_voluntario) {
        this.id_voluntario = id_voluntario;
    }



    public long getId() {
        return id_voluntario;
    }

    public void setId(long id_voluntario) {
        this.id_voluntario = id_voluntario;
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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Voluntario(String nombre, String apellido, String correo, String sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.sexo = sexo;
    }

    public Voluntario(String nombre, String apellido, String correo, String sexo, float latitude, float longitude) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.sexo = sexo;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
