package com.example.exdb.model;

import android.os.Parcelable;

import java.io.Serializable;

public class Alumno implements Serializable {

    private int id;
    private String nombre;
    private String apellido;

    public Alumno() {
        this.id = 0;
        this.nombre = "";
        this.apellido = "";
    }

    public Alumno (String nombre, String apellido) {
        this.id = 0;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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
}
