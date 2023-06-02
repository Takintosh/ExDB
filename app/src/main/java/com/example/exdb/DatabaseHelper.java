package com.example.exdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    private static final String DATABASE_NAME = "matriculas.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Estructura de la Base de Datos
        Log.d(TAG, "Creando tablas");
        db.execSQL("CREATE TABLE IF NOT EXISTS alumnos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS asignaturas (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, docente TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS matriculas (id_alumno integer, id_asignatura integer," +
                "primary key(id_alumno, id_asignatura)," +
                "foreign key (id_alumno) references alumnos(id)," +
                "foreign key (id_asignatura) references asignaturas(id));");
        Log.d(TAG, "Tablas creadas");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Acciones de actualización de la base de datos
    }
}
