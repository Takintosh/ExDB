package com.example.exdb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exdb.model.Alumno;

import java.util.List;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder> {

    private List<Alumno> alumnos;
    private View.OnClickListener clickListener;

    public AlumnoAdapter(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno, parent, false);
        return new AlumnoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        Alumno alumno = alumnos.get(position);
        holder.bind(alumno);
    }

    @Override
    public int getItemCount() {
        return alumnos != null ? alumnos.size() : 0;
    }

    class AlumnoViewHolder extends RecyclerView.ViewHolder {

        private TextView textNombre;

        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombre = itemView.findViewById(R.id.textNombre);
            itemView.setOnClickListener(clickListener);
        }

        public void bind(final Alumno alumno) {
            String nombreCompleto = alumno.getNombre() + " " + alumno.getApellido();
            textNombre.setText(nombreCompleto);
            itemView.setTag(alumno);
        }
    }
}
