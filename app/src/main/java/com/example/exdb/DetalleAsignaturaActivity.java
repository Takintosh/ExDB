package com.example.exdb;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exdb.data.AsignaturaDAO;
import com.example.exdb.model.Asignatura;

public class DetalleAsignaturaActivity extends OptionsMenuHandler {

    private String textNombre;
    private String textDocente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_asignatura);

        Asignatura asignatura = (Asignatura) getIntent().getSerializableExtra("asignatura");

        if(asignatura == null) {
            Toast.makeText(this, "Objeto Asignatura nulo", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        AsignaturaDAO asignaturaDAO = new AsignaturaDAO(dbHelper.getWritableDatabase());

        asignatura = asignaturaDAO.obtenerAsignatura(asignatura.getId());

        if(asignatura != null) {
            textNombre = asignatura.getNombre();
            textDocente = asignatura.getDocente();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(textNombre);
    }
}