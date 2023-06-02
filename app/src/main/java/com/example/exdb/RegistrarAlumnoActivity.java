package com.example.exdb;

import androidx.appcompat.widget.Toolbar;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exdb.data.AlumnoDAO;
import com.example.exdb.model.Alumno;

public class RegistrarAlumnoActivity extends OptionsMenuHandler implements View.OnClickListener {

    private EditText nombre;
    private EditText apellido;
    private Button registrar;
    private static final String TAG = "RegistrarAlumnoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alumno);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Registrar Alumno");

        // Formulário
        nombre = findViewById(R.id.inputNombre);
        apellido = findViewById(R.id.inputApellido);
        registrar = findViewById(R.id.btnRegistrar);
        registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnRegistrar) {
            String nombreAlumno = nombre.getText().toString();
            String apellidoAlumno = apellido.getText().toString();

            Alumno alumno = new Alumno(nombreAlumno, apellidoAlumno);

            DatabaseHelper dbHelper = new DatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            AlumnoDAO alumnoDAO = new AlumnoDAO(db);
            long resultado = alumnoDAO.insertarAlumno(alumno);

            if(resultado != -1) {
                findViewById(R.id.inputNombre);
                Toast.makeText(getApplicationContext(),"Alumno Registrado",Toast.LENGTH_SHORT).show();
                Log.d(TAG,"Alumno Registrado");
                nombre.setText("");
                apellido.setText("");
            } else {
                Toast.makeText(getApplicationContext(),"Error al registrar",Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Error al registrar");
            }

        }
    }

}