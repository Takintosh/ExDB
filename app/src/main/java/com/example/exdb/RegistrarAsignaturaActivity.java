package com.example.exdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exdb.data.AsignaturaDAO;
import com.example.exdb.model.Asignatura;

public class RegistrarAsignaturaActivity extends OptionsMenuHandler implements View.OnClickListener {

    private EditText nombre, docente;
    private Button registrar;
    private static final String TAG = "RegistrarAsignaturaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_asignatura);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Registrar Asignatura");

        // Formulário
        nombre = findViewById(R.id.inputNombre);
        docente = findViewById(R.id.inputDocente);
        registrar = findViewById(R.id.btnRegistrar);
        registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnRegistrar) {
            String nombreAsignatura = nombre.getText().toString();
            String docenteAsignatura = docente.getText().toString();

            Asignatura asignatura = new Asignatura(nombreAsignatura, docenteAsignatura);

            DatabaseHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            AsignaturaDAO asignaturaDAO = new AsignaturaDAO(db);
            long resultado = asignaturaDAO.insertarAsignatura(asignatura);

            if (resultado != -1) {
                Toast.makeText(getApplicationContext(), "Asignatura registrada", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Asignatura Registrada");
                nombre.setText("");
                docente.setText("");
            } else {
                Toast.makeText(getApplicationContext(), "Error al registrar", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Error al registrar");
            }
        }
    }
}