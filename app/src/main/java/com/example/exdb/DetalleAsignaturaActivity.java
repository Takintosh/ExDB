package com.example.exdb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exdb.data.AsignaturaDAO;
import com.example.exdb.data.MatriculaDAO;
import com.example.exdb.model.Alumno;
import com.example.exdb.model.Asignatura;

import java.util.List;

public class DetalleAsignaturaActivity extends OptionsMenuHandler {

    private String textNombre;
    private String textDocente;
    private RecyclerView recyclerView;
    private AlumnoAdapter alumnoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_asignatura);

        // Obtener objeto Asignatura desde el Intent
        Asignatura asignatura = (Asignatura) getIntent().getSerializableExtra("asignatura");

        // Verificar si no viene nulo
        if(asignatura == null) {
            Toast.makeText(this, "Objeto Asignatura nulo", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Crear instancia de DatabaseHelper y AsignaturaDAO
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        AsignaturaDAO asignaturaDAO = new AsignaturaDAO(dbHelper.getWritableDatabase());

        // Obtener detalles de la Asignatura
        asignatura = asignaturaDAO.obtenerAsignatura(asignatura.getId());

        // Mostrar detalles de la asignatura
        if (asignatura != null) {
            textNombre = asignatura.getNombre();
            textDocente = asignatura.getDocente();
        }

        MatriculaDAO matriculaDAO = new MatriculaDAO(dbHelper.getReadableDatabase());
        List<Alumno> alumnosMatriculados = matriculaDAO.listarAlumnosMatriculados(asignatura);

        recyclerView = findViewById(R.id.recyclerViewAlumnos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        alumnoAdapter = new AlumnoAdapter(null);
        recyclerView.setAdapter(alumnoAdapter);
        alumnoAdapter.setAlumnos(alumnosMatriculados);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(textNombre);
        }

        /*
        if(toolbar!=null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            TextView toolbarTitle = findViewById(R.id.toolbar_title);
            toolbarTitle.setText(textNombre);
        }

         */

    }
}