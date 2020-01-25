package com.example.adjonander;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Alumno.leerAlumnos();

        btn=findViewById(R.id.buttonMostrar);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Inicio.this, AlumnosRecycler.class);
                //myIntent.putExtra("dni", dni);
                startActivity(myIntent);

            }
        });


    }
}
