package com.example.adjonander;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AlumnoClick extends AppCompatActivity {

    //Pantalla que se muestra al hacer click en un elemento del recycler

    private TextView tvNombre, tvApellido1, tvApellido2, tvDni, tvCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno_click);

        tvNombre=findViewById(R.id.textViewNombreBD);
        tvApellido1=findViewById(R.id.textViewApellido1BD);
        tvApellido2=findViewById(R.id.textViewApellido2BD);
        tvDni=findViewById(R.id.textViewDniBD);
        tvCurso=findViewById(R.id.textViewCursoBD);

        tvNombre.setText(getIntent().getStringExtra("nombre"));
        tvApellido1.setText(getIntent().getStringExtra("apellido1"));
        tvApellido2.setText(getIntent().getStringExtra("apellido2"));
        tvDni.setText(getIntent().getStringExtra("dni"));
        tvCurso.setText(getIntent().getStringExtra("curso"));


    }
}
