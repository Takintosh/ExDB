package com.example.exdb.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.exdb.model.Alumno;
import com.example.exdb.model.Asignatura;

import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    private SQLiteDatabase db;

    public MatriculaDAO(SQLiteDatabase db) {
        this.db = db;
        alumnoDAO = new AlumnoDAO(db);
        asignaturaDAO = new AsignaturaDAO(db);
    }

    private AlumnoDAO alumnoDAO;
    private AsignaturaDAO asignaturaDAO;

    public long matricularAlumno(Alumno alumno, Asignatura asignatura) {
        ContentValues values = new ContentValues();
        values.put("id_alumno", alumno.getId());
        values.put("id_asignatura", asignatura.getId());

        return db.insert("matriculas", null, values);
    }

    public List<Asignatura> listarMatriculasPorAlumno(Alumno alumno) {
        List<Asignatura> asignaturasMatriculadas = new ArrayList<>();

        String query = "SELECT asignaturas.nombre, asignaturas.docente FROM asignaturas " +
                "INNER JOIN matriculas ON asignaturas.id = matriculas.id_asignatura " +
                "WHERE matriculas.id_alumno = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(alumno.getId())});

        // Itera sobre el cursor y crea objetos Asignatura con los datos obtenidos
        while (cursor.moveToNext()) {
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String docente = cursor.getString(cursor.getColumnIndexOrThrow("docente"));

            Asignatura asignatura = new Asignatura(nombre, docente);

            asignaturasMatriculadas.add(asignatura);
        }

        cursor.close();

        return asignaturasMatriculadas;
    }


}
