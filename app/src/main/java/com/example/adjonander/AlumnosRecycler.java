package com.example.adjonander;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlumnosRecycler extends AppCompatActivity {

    /* RecyclerView PASO 5*/

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutmanager;
    private adaptador_recycler_alumno adaptador;

    private TextView tvNombre, tvApellido1, tvApellido2, tvDni, tvCurso;
    private String nombre,apellido1, apellido2, dni, curso;

    private Button btnOnline, btnLocal, btnCombinar;

    DBHelper dbHelper;
    SQLiteDatabase dbsqlite;
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

                Toast.makeText(getApplicationContext(), (R.string.cargando), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(), (R.string.cargando), Toast.LENGTH_SHORT).show();
                tipoBD=Alumno.getAlumnoLocal();
                cargarAlumnosLocal();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        recycler();
                    }
                }, 500);

            }
        });

        btnCombinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), (R.string.cargando), Toast.LENGTH_SHORT).show();
                Alumno.AlumnoCombinada.clear();
                tipoBD=Alumno.getAlumnoCombinada();
                Alumno.leerAlumnos();
                cargarAlumnosLocal();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        Alumno.AlumnoCombinada.addAll(Alumno.alumnos);

                        for(int i = 0; i < Alumno.AlumnoCombinada.size(); i++){

                            for(int j = 0; j < Alumno.AlumnoLocal.size(); j++){
                                if (Alumno.AlumnoCombinada.get(i).getDNI().equals(Alumno.AlumnoLocal.get(j).getDNI())){
                                    Alumno.AlumnoLocal.remove(j);
                                }
                            }
                        }

                        Alumno.AlumnoCombinada.addAll(Alumno.AlumnoLocal);
                        /*
                        for(int i = 0; i < Alumno.AlumnoCombinada.size(); i++){
                            System.out.println("Combinada "+Alumno.AlumnoCombinada.get(i).getDNI());

                        }

                         */

                        recycler();

                    }
                }, 2000);

            }
        });

    }


    public void recycler(){
        ArrayList<Alumno> alumnoss =tipoBD;
        adaptador = new adaptador_recycler_alumno(Objects.requireNonNull(getApplicationContext()), alumnoss);

        adaptador.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {

                tvNombre = x.findViewById(R.id.textViewNombreR);
                tvApellido1 = x.findViewById(R.id.textViewApellido1R);
                tvApellido2 = x.findViewById(R.id.textViewApellido2R);
                tvDni = x.findViewById(R.id.textViewDniR);
                tvCurso = x.findViewById(R.id.textViewCursoR);

                nombre = tvNombre.getText().toString();
                apellido1 = tvApellido1.getText().toString();
                apellido2 = tvApellido2.getText().toString();
                dni = tvDni.getText().toString();
                curso = tvCurso.getText().toString();

                Intent intent = new Intent(AlumnosRecycler.this, AlumnoClick.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("apellido1", apellido1);
                intent.putExtra("apellido2", apellido2);
                intent.putExtra("dni", dni);
                intent.putExtra("curso", curso);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adaptador);
        layoutmanager = new GridLayoutManager(getApplicationContext(), 4);
        recyclerView.setLayoutManager(layoutmanager);

    }


    public void cargarAlumnosLocal(){

        Alumno.AlumnoLocal.clear();
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
            Alumno.AlumnoLocal.add(AL);

            System.out.println("Datos locales "+nombreL+" "+apellido1L+" "+apellido2L+" "+dniL+" "+cursoL);


        }
    }


}
