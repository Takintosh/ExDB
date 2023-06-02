package com.example.exdb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exdb.model.Asignatura;

import java.util.List;

public class AsignaturaAdapter extends RecyclerView.Adapter<AsignaturaAdapter.AsignaturaViewHolder> {

    private List<Asignatura> asignaturas;
    private View.OnClickListener clickListener;

    public AsignaturaAdapter(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AsignaturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asignatura, parent, false);
        return new AsignaturaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsignaturaViewHolder holder, int position) {
        Asignatura asignatura = asignaturas.get(position);
        holder.bind(asignatura);
    }

    @Override
    public int getItemCount() {
        return asignaturas != null ? asignaturas.size() : 0;
    }

    class AsignaturaViewHolder extends RecyclerView.ViewHolder {
        private TextView textNombre, textDocente;

        public AsignaturaViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombre = itemView.findViewById(R.id.textNombre);
            textDocente = itemView.findViewById(R.id.textDocente);
            itemView.setOnClickListener(clickListener);
        }
        public void bind(final Asignatura asignatura) {
            textNombre.setText(asignatura.getNombre());
            textDocente.setText("Prof. " + asignatura.getDocente());
            itemView.setTag(asignatura);
        }
    }

}
