package models;

import org.postgis.Point;

public class Emergencia {
    private Long id_emergencia;
    private String nombre;
    private String tipo;
    private String descripcion;

    private Point location;
    private float latitude;
    private float longitude;
    private float radius;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

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

    public Emergencia(String nombre, String tipo, String descripcion, float latitude, float longitude) {
        this.nombre = nombre;

        this.tipo = tipo;
        this.descripcion = descripcion;
        this.latitude = latitude;
        this.longitude = longitude;
    }







}
