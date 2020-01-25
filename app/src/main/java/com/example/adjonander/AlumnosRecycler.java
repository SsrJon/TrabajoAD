package com.example.adjonander;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class AlumnosRecycler extends AppCompatActivity {

    /* RecyclerView PASO 5*/

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutmanager;
    private adaptador_recycler_alumno adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos_recycler);

        Alumno.leerAlumnos();



        recyclerView = findViewById(R.id.recyclerAlumnos);
        ArrayList<Alumno> alumnoss = Alumno.getAlumnos();
        Log.d("tag", "alumnos? " + alumnoss.size());
        System.out.println("hola "+alumnoss.size());

        adaptador = new adaptador_recycler_alumno(Objects.requireNonNull(this), alumnoss);
        adaptador.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                TextView tv = x.findViewById(R.id.textViewNombreR);
                String piso = tv.getText().toString();
                //Intent myIntent = new Intent(AlumnosRecycler.this, AlumnosRecycler.class);
                //myIntent.putExtra("dni", dni);
                //startActivity(myIntent);
            }
        });



        recyclerView.setAdapter(adaptador);

        layoutmanager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutmanager);



    }




}
