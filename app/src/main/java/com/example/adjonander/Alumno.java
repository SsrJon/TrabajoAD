package com.example.adjonander;

import java.util.ArrayList;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Alumno {

    /* RecyclerView PASO 3*/

    private String Nombre;
    private String Apellido1;
    private String Apellido2;
    private String DNI;
    private String Curso;
    private static ArrayList<Alumno> alumnos=new ArrayList();

    private static FirebaseFirestore db;



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
        return alumnos;
    }
}
