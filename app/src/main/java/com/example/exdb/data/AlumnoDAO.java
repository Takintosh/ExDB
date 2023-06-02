package com.example.exdb.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.exdb.model.Alumno;

import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {

    private SQLiteDatabase db;
    public AlumnoDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertarAlumno(Alumno alumno) {
        ContentValues values = new ContentValues();
        values.put("nombre", alumno.getNombre());
        values.put("apellido", alumno.getApellido());

        return db.insert("alumnos", null, values);
    }

    public Alumno obtenerAlumno(int alumnoId) {
        String[] projection = {"id", "nombre", "apellido"};
        String selection = "id = ?";
        String[] selectionArgs = {String.valueOf(alumnoId)};

        Cursor cursor = db.query("alumnos", projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            Alumno alumno = new Alumno();
            alumno.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            alumno.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
            alumno.setApellido(cursor.getString(cursor.getColumnIndexOrThrow("apellido")));
            cursor.close();
            return alumno;
        } else {
            cursor.close();
            return null;
        }
    }


    public List<Alumno> listarAlumnos() {
        List<Alumno> listaAlumnos = new ArrayList<>();
        String[] projection = {"id", "nombre", "apellido"};

        Cursor cursor = db.query("alumnos", projection, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Alumno alumno = new Alumno();
            alumno.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            alumno.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
            alumno.setApellido(cursor.getString(cursor.getColumnIndexOrThrow("apellido")));

            listaAlumnos.add(alumno);
        }
        cursor.close();
        return listaAlumnos;
    }

}
