package com.example.adjonander;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adaptador_recycler_alumno extends RecyclerView.Adapter<adaptador_recycler_alumno.ViewHolder>  {

    /* RecyclerView PASO 4*/


    private LayoutInflater inflador;
    protected List<Alumno> alumnosList;
    private Context contexto;
    private View.OnClickListener onClickListener;


    public adaptador_recycler_alumno (Context contexto,  List<Alumno> alumnosList){

        inflador= (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        this.alumnosList=alumnosList;
        this.contexto=contexto;
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView apellido1;
        public TextView apellido2;
        public TextView dni;
        public TextView curso;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.textViewNombreR);
            apellido1 = itemView.findViewById(R.id.textViewApellido1R);
            apellido2 = itemView.findViewById(R.id.textViewApellido2R);
            dni = itemView.findViewById(R.id.textViewDniR);
            curso = itemView.findViewById(R.id.textViewCursoR);


        }


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.contenido_recycler,null);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int posicion) {
        Alumno alumno = alumnosList.get(posicion);
        holder.nombre.setText(alumno.getNombre());
        holder.apellido1.setText(alumno.getApellido1());
        holder.apellido2.setText(alumno.getApellido2());
        holder.dni.setText(alumno.getDNI());
        holder.curso.setText(alumno.getCurso());
    }


    @Override
    public int getItemCount () { return alumnosList.size(); }

    public void setOnItemClickListener (View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }



}
