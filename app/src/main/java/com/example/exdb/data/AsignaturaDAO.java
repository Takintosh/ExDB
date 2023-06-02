package com.example.exdb.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.exdb.model.Alumno;
import com.example.exdb.model.Asignatura;

import java.util.ArrayList;
import java.util.List;

public class AsignaturaDAO {
    private SQLiteDatabase db;
    public AsignaturaDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertarAsignatura(Asignatura asignatura) {
        ContentValues values = new ContentValues();
        values.put("nombre", asignatura.getNombre());
        values.put("docente", asignatura.getDocente());

        return db.insert("asignaturas", null, values);
    }

    public Asignatura obtenerAsignatura(int id) {
        String[] projection = {"id", "nombre", "docente"};
        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query("asignaturas", projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            Asignatura asignatura = new Asignatura();
            asignatura.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            asignatura.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
            asignatura.setDocente(cursor.getString(cursor.getColumnIndexOrThrow("docente")));
            cursor.close();
            return asignatura;
        } else {
            cursor.close();
            return null;
        }
    }

    public List<Asignatura> listarAsignaturas() {
        List<Asignatura> listaAsignaturas = new ArrayList<>();
        String[] projection = {"id", "nombre", "docente"};

        Cursor cursor = db.query("asignaturas", projection, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Asignatura asignatura = new Asignatura();
            asignatura.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            asignatura.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
            asignatura.setDocente(cursor.getString(cursor.getColumnIndexOrThrow("docente")));

            listaAsignaturas.add(asignatura);
        }
        cursor.close();
        return listaAsignaturas;
    }

}
