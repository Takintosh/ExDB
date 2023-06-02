package com.example.exdb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exdb.data.AlumnoDAO;
import com.example.exdb.data.MatriculaDAO;
import com.example.exdb.model.Alumno;
import com.example.exdb.model.Asignatura;


import java.util.List;

public class DetalleAlumnoActivity extends OptionsMenuHandler {

    private String textNombre;
    private String textApellido;
    private String nombreCompleto = "Alumno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_alumno);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(toolbar!=null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            TextView toolbarTitle = findViewById(R.id.toolbar_title);
            toolbarTitle.setText(nombreCompleto);
        }

        // Obtener el objeto Alumno desde el intent
        Alumno alumno = (Alumno) getIntent().getSerializableExtra("alumno");

        if (alumno == null) {
            Toast.makeText(this, "Objeto Alumno nulo", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Crear una instancia de DatabaseHelper y AlumnoDAO
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        AlumnoDAO alumnoDAO = new AlumnoDAO(dbHelper.getReadableDatabase());

        // Obtener los detalles del alumno
        alumno = alumnoDAO.obtenerAlumno(alumno.getId());

        // Mostrar los detalles del alumno en la interfaz de usuario
        if (alumno != null) {
            textNombre = alumno.getNombre();
            textApellido = alumno.getApellido();
            nombreCompleto = textNombre + " " + textApellido;
        }

        MatriculaDAO matriculaDAO = new MatriculaDAO(dbHelper.getReadableDatabase());
        List<Asignatura> asignaturasMatriculadas = matriculaDAO.listarMatriculasPorAlumno(alumno);

        LinearLayout linearAsignaturas = findViewById(R.id.linearAsignaturas);

        for (Asignatura asignatura : asignaturasMatriculadas) {
            TextView textViewAsignatura = new TextView(this);
            textViewAsignatura.setText(asignatura.getNombre());

            linearAsignaturas.addView(textViewAsignatura);
        }

    }


}
