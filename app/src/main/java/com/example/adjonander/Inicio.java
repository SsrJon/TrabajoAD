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

    private Button btn;

    DBHelper dbHelper;
    SQLiteDatabase dbsqlite;
    //public ArrayList<Alumno> AlumnoLocal = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Alumno.leerAlumnos();
        cargarAlumnosLocal();



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




    public void cargarAlumnosLocal(){
        System.out.println("hola local");
        dbHelper = new DBHelper(getApplicationContext());
        dbsqlite = dbHelper.getWritableDatabase();
        Cursor cursorcantidad = dbsqlite.query(DBHelper.entidadAlumnos.TABLE_NAME,null,null,null,null,null,null);
        while (cursorcantidad.moveToNext()){
            String nombreL = cursorcantidad.getString(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadAlumnos.COLUMN_NAME_NOMBRE));
            String apellido1L = cursorcantidad.getString(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadAlumnos.COLUMN_NAME_APELLIDO1));
            String apellido2L = cursorcantidad.getString(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadAlumnos.COLUMN_NAME_APELLIDO2));
            String dniL = cursorcantidad.getString(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadAlumnos.COLUMN_NAME_DNI));
            String cursoL = cursorcantidad.getString(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadAlumnos.COLUMN_NAME_CURSO));
            Alumno AL = new Alumno(nombreL,apellido1L,apellido2L,dniL,cursoL);
            System.out.println("Datos locales "+nombreL+" "+apellido1L+" "+apellido2L+" "+dniL+" "+cursoL);
            Alumno.AlumnoLocal.add(AL);



        }
    }
}
