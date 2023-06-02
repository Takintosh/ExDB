package com.example.exdb;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exdb.data.AlumnoDAO;
import com.example.exdb.model.Alumno;

import java.util.List;
public class ListarAlumnosActivity extends OptionsMenuHandler implements View.OnClickListener {

    private static final String TAG = "ListarAlumnosActivity";
    private RecyclerView recyclerView;
    private AlumnoAdapter alumnoAdapter;
    private AlumnoDAO alumnoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_alumnos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Listar Alumnos");

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        alumnoAdapter = new AlumnoAdapter(this);
        recyclerView.setAdapter(alumnoAdapter);

        // Inicializar DAO
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        alumnoDAO = new AlumnoDAO(db);

        // Obtener lista de alumnos y mostrar en RecyclerView
        List<Alumno> listaAlumnos = alumnoDAO.listarAlumnos();
        alumnoAdapter.setAlumnos(listaAlumnos);
    }

    @Override
    public void onClick(View view) {
        Alumno alumno = (Alumno) view.getTag();

        if(alumno != null) {

            // Crear el intent para iniciar la actividad DetalleAlumnoActivity
            Intent intent = new Intent(this, DetalleAlumnoActivity.class);
            intent.putExtra("alumno", alumno); // Pasar el objeto Alumno al intent

            // Iniciar la actividad DetalleAlumnoActivity
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"Objeto nulo",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Objeto nulo");
        }

    }
}
