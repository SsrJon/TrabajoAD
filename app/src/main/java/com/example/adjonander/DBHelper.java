package com.example.adjonander;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    ArrayList<Alumno> alumnosLocal = new ArrayList<>();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AlumnosLocal.db";


    //Creacion de la tabla

    public static class entidadAlumnos implements BaseColumns {
        public static final String TABLE_NAME = "Alumnos";
        public static final String COLUMN_NAME_NOMBRE = "Nombre" ;
        public static final String COLUMN_NAME_APELLIDO1 = "Apellido1" ;
        public static final String COLUMN_NAME_APELLIDO2 = "Apellido2" ;
        public static final String COLUMN_NAME_DNI = "Dni" ;
        public static final String COLUMN_NAME_CURSO = "Curso" ;

    }


    private static final String SQL_CREATE_TABLE_ALUMNOS =
            "CREATE TABLE " + entidadAlumnos.TABLE_NAME + " (" +
                    entidadAlumnos.COLUMN_NAME_NOMBRE + " TEXT," +
                    entidadAlumnos.COLUMN_NAME_APELLIDO1 + " TEXT," +
                    entidadAlumnos.COLUMN_NAME_APELLIDO2 + " TEXT," +
                    entidadAlumnos.COLUMN_NAME_DNI + " TEXT PRIMARY KEY," +
                    entidadAlumnos.COLUMN_NAME_CURSO + " TEXT)";

    private static final String SQL_DELETE_TABLE_ALUMNOS =
            "DROP TABLE IF EXISTS " + entidadAlumnos.TABLE_NAME;


    //La primera vez que se inicia la app crea la BD con la tabla y los primeros datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_ALUMNOS);
        crearAlumnos();
        insertarAlumnos(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_ALUMNOS);
        onCreate(db);
    }

    public void onDowngrade (SQLiteDatabase db,int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Datos iniciales
    public void crearAlumnos(){

        Alumno AL1 = new Alumno("Paco","Garcia","Gonzalez","12345678C","DD");
        Alumno AL2 = new Alumno("Maria","Martin","Hernandez","12345678D","DD");
        Alumno AL3 = new Alumno("Miren","Silva","Sanchez","12345678E","AA");

        alumnosLocal.add(AL1);
        alumnosLocal.add(AL2);
        alumnosLocal.add(AL3);

    }
    //Se insertan los datos iniciales
    public void insertarAlumnos(SQLiteDatabase db){

        for (int i = 0;alumnosLocal.size()>i;i++) {
            ContentValues values = new ContentValues();
            values.put(DBHelper.entidadAlumnos.COLUMN_NAME_NOMBRE,alumnosLocal.get(i).Nombre);
            values.put(entidadAlumnos.COLUMN_NAME_APELLIDO1, alumnosLocal.get(i).Apellido1);
            values.put(entidadAlumnos.COLUMN_NAME_APELLIDO2, alumnosLocal.get(i).Apellido2);
            values.put(DBHelper.entidadAlumnos.COLUMN_NAME_DNI, alumnosLocal.get(i).DNI);
            values.put(entidadAlumnos.COLUMN_NAME_CURSO, alumnosLocal.get(i).Curso);
            db.insert(DBHelper.entidadAlumnos.TABLE_NAME, null, values);
        }
    }






}
