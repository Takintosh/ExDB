package com.example.exdb;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.exdb.data.AsignaturaDAO;
import com.example.exdb.model.Alumno;
import com.example.exdb.model.Asignatura;

import java.util.List;

public class ListarAsignaturasActivity extends OptionsMenuHandler implements View.OnClickListener {

    private static final String TAG = "ListarAsignaturasActivity";
    private RecyclerView recyclerView;
    private AsignaturaAdapter asignaturaAdapter;
    private AsignaturaDAO asignaturaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_asignaturas);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Listar Asignaturas");

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        asignaturaAdapter = new AsignaturaAdapter(this);
        recyclerView.setAdapter(asignaturaAdapter);

        // Inicializar DAO
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        asignaturaDAO = new AsignaturaDAO(db);

        // Obtener lista de alumnos y mostrar en RecyclerView
        List<Asignatura> listaAsignaturas = asignaturaDAO.listarAsignaturas();
        asignaturaAdapter.setAsignaturas(listaAsignaturas);
    }

    @Override
    public void onClick(View view) {
        Asignatura asignatura = (Asignatura) view.getTag();
        if (asignatura != null) {
            Intent intent = new Intent(this, DetalleAsignaturaActivity.class);
            intent.putExtra("asignatura", asignatura);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Objeto nulo", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Objeto nulo");
        }
    }
}