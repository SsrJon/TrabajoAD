package com.example.adjonander;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.ContentValues.TAG;

public class Alumno {

    /* RecyclerView PASO 3*/

    private String Nombre;
    private String Apellido1;
    private String Apellido2;
    private String DNI;
    private String Curso;
    private static ArrayList<Alumno> alumnos=new ArrayList();

    private static FirebaseFirestore db;


    public static void leerAlumnos(){

        db = FirebaseFirestore.getInstance();

        System.out.println("Leer alumnos");
        db.collection("alumnos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            System.out.println("Bien");
                            alumnos.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Alumno alumno = new Alumno((String) document.get("nombre"), (String) document.get("apellido1"),(String) document.get("apellido2"),
                                        (String) document.get("dni"),(String) document.get("curso"));
                                alumnos.add(alumno);
                                Log.d(TAG, String.valueOf(alumnos));

                                System.out.println("adios"+document.get("nombre"));
                            }

                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                            System.out.println("fallo");

                        }
                        Log.d("tag", "casas? " + alumnos.size());
                        System.out.println("tamaño "+alumnos.size());
                    }
                });




    }



    public Alumno (String Nombre, String Apellido1, String Apellido2, String DNI, String Curso){
        this.Nombre=Nombre;
        this.Apellido1=Apellido1;
        this.Apellido2=Apellido2;
        this.DNI=DNI;
        this.Curso=Curso;
    }


    public String getNombre() {
        return Nombre;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public String getApellido2() {
        return Apellido2;
    }

    public String getDNI() {
        return DNI;
    }

    public String getCurso() {
        return Curso;
    }

    public static ArrayList<Alumno> getAlumnos() {
        Log.d("tag", "tamaño arraylist alumnos: " + alumnos.size());
        return alumnos;
    }
}
