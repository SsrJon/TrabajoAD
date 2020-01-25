package com.example.adjonander;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class AlumnosRecycler extends AppCompatActivity {

    /* RecyclerView PASO 5*/

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutmanager;
    private adaptador_recycler_alumno adaptador;

    private Button btnOnline, btnLocal, btnCombinar;
    ArrayList<Alumno> tipoBD=Alumno.getAlumnos();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos_recycler);

        btnOnline=findViewById(R.id.buttonOnline);
        btnLocal=findViewById(R.id.buttonLocal);
        btnCombinar=findViewById(R.id.buttonCombinar);

        recyclerView = findViewById(R.id.recyclerAlumnos);

        btnOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipoBD=Alumno.getAlumnos();
                Alumno.leerAlumnos();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        recycler();
                    }
                }, 1000);



            }
        });


        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipoBD=Alumno.getAlumnoLocal();


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        recycler();
                    }
                }, 1000);

            }
        });

        btnCombinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        recycler();
                    }
                }, 1000);

            }
        });

    }


    public void recycler(){
        ArrayList<Alumno> alumnoss =tipoBD;
        adaptador = new adaptador_recycler_alumno(Objects.requireNonNull(getApplicationContext()), alumnoss);

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

        layoutmanager = new GridLayoutManager(getApplicationContext(), 4);
        recyclerView.setLayoutManager(layoutmanager);

    }



}
