package com.example.exdb;

import androidx.appcompat.widget.Toolbar;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exdb.data.AlumnoDAO;
import com.example.exdb.data.AsignaturaDAO;
import com.example.exdb.data.MatriculaDAO;
import com.example.exdb.model.Alumno;
import com.example.exdb.model.Asignatura;

import java.util.ArrayList;
import java.util.List;

public class MatricularActivity extends OptionsMenuHandler implements View.OnClickListener {

    private AsignaturaDAO asignaturaDAO;
    private AlumnoDAO alumnoDAO;
    private MatriculaDAO matriculaDAO;
    private Button registrar;
    private List<Asignatura> listaAsignaturas;
    private List<Alumno> alumnosSeleccionados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matricular);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Matricular Alumnos");

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        asignaturaDAO = new AsignaturaDAO(db);
        alumnoDAO = new AlumnoDAO(db);
        matriculaDAO = new MatriculaDAO(db);

        listaAsignaturas = asignaturaDAO.listarAsignaturas();
        alumnosSeleccionados = new ArrayList<>();

        Spinner spinnerAsignaturas = findViewById(R.id.spinnerAsignaturas);

        List<String> listaNombresAsignaturas = new ArrayList<>();
        for(Asignatura asignatura : listaAsignaturas) {
            listaNombresAsignaturas.add(asignatura.getNombre());
        }

        ArrayAdapter<String> asignaturaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaNombresAsignaturas);
        asignaturaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAsignaturas.setAdapter(asignaturaAdapter);

        List<Alumno> listaAlumnos = alumnoDAO.listarAlumnos();
        LinearLayout contenedorAlumnos = findViewById(R.id.linearAlumnos);

        for(Alumno alumno : listaAlumnos) {
            CheckBox checkBoxAlumno = new CheckBox(this);
            checkBoxAlumno.setText(alumno.getNombre()+" "+alumno.getApellido());
            checkBoxAlumno.setId(alumno.getId());

            checkBoxAlumno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        alumnosSeleccionados.add(alumno);
                    } else {
                        alumnosSeleccionados.remove(alumno);
                    }
                }
            });

            contenedorAlumnos.addView(checkBoxAlumno);
        }

        registrar = findViewById(R.id.btnRegistrar);
        registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnRegistrar) {
            Spinner spinnerAsignaturas = findViewById(R.id.spinnerAsignaturas);
            int selectedIndex = spinnerAsignaturas.getSelectedItemPosition();
            Asignatura asignaturaSeleccionada = listaAsignaturas.get(selectedIndex);

            StringBuilder alumnosRegistradosText = new StringBuilder();
            boolean errorMsg = false;
            for (Alumno alumno : alumnosSeleccionados) {
                //Insertar en Matricula de BD

                long resultado = matriculaDAO.matricularAlumno(alumno, asignaturaSeleccionada);
                if(resultado == -1) {
                    errorMsg = true;
                } else {
                    alumnosRegistradosText.append(alumno.getNombre()).append(" ").append(alumno.getApellido()).append("\n");
                }
            }
            if (errorMsg) {
                Toast.makeText(getApplicationContext(), "Ocurrió un error", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Alumnos matriculados", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(getApplicationContext(), "Alumnos matriculados:\n" + alumnosRegistradosText.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
