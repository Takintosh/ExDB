package com.example.exdb.model;

import java.io.Serializable;

public class Asignatura implements Serializable {

    private int id;
    private String nombre;
    private String docente;

    public Asignatura() {
        this.id = 0;
        this.nombre = "";
        this.docente = "";
    }

    public Asignatura(String nombre, String docente) {
        this.id = 0;
        this.nombre = nombre;
        this.docente = docente;
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
    public String getDocente() {
        return docente;
    }
    public void setDocente(String docente) {
        this.docente = docente;
    }
}
