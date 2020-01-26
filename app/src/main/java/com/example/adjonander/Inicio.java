package com.example.adjonander;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import static com.example.adjonander.Alumno.alumnos;

public class Inicio extends AppCompatActivity {

    //Pantalla inicial con las opciones de mostrar o añadir alumnos

    private Button btnMostrar, btnAñadir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnMostrar=findViewById(R.id.buttonMostrar);
        btnAñadir=findViewById(R.id.buttonAñadir);

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Inicio.this, AlumnosRecycler.class);
                startActivity(myIntent);

            }
        });

        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Inicio.this, CrearAlumno.class);
                startActivity(myIntent);
            }
        });


    }


}
