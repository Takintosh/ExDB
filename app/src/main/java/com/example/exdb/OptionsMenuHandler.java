package com.example.exdb;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsMenuHandler extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_listar_alumnos) {
            Intent listarAlumnosIntent = new Intent(this, ListarAlumnosActivity.class);
            startActivity(listarAlumnosIntent);
            return true;
        } else if (id == R.id.action_listar_asignaturas) {
            Intent listarAsignaturasIntent = new Intent(this, ListarAsignaturasActivity.class);
            startActivity((listarAsignaturasIntent));
            return true;
        } else if (id == R.id.action_registrar_alumno) {
            Intent RegistrarAlumnoIntent = new Intent(this, RegistrarAlumnoActivity.class);
            startActivity(RegistrarAlumnoIntent);
            return true;
        } else if (id == R.id.action_registrar_asignatura) {
            Intent RegistrarAsignaturaIntent = new Intent(this, RegistrarAsignaturaActivity.class);
            startActivity(RegistrarAsignaturaIntent);
            return true;
        } else if (id == R.id.action_matricular_alumnos) {
            Intent MatricularAsignaturaIntent = new Intent(this, MatricularActivity.class);
            startActivity(MatricularAsignaturaIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
