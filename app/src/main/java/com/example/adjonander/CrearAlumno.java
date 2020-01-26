package com.example.adjonander;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import static android.content.ContentValues.TAG;

public class CrearAlumno extends AppCompatActivity  {

    private Button btnAñadir;
    private EditText etNombre, etApellido1, etApellido2, etDni, etCurso;
    private static FirebaseFirestore dbOnline;
    private String nombreOnline, apellido1Online, apellido2Online, dniOnline, cursoOnline;
    DBHelper dbHelper;
    SQLiteDatabase dbsqlite;
    private int existeEnLocal=0;
    private int existeEnOnline=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alumno);

        dbHelper = new DBHelper(this);
        dbsqlite = dbHelper.getWritableDatabase();

        btnAñadir=findViewById(R.id.buttonAñadirBD);
        etNombre=findViewById(R.id.editTextNombre);
        etApellido1=findViewById(R.id.editTextApellido1);
        etApellido2=findViewById(R.id.editTextApellido2);
        etDni=findViewById(R.id.editTextDni);
        etCurso=findViewById(R.id.editTextCurso);


        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (etNombre.getText().toString().equals("")||etApellido1.getText().toString().equals("")||etApellido2.getText().toString().equals("")||etDni.getText().toString().equals("")||
                        etCurso.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), (R.string.vacio), Toast.LENGTH_SHORT).show();
                } else{

                    existeEnLocal=0;
                    existeEnOnline=0;

                    CompruebaOnline();
                    CompruebaLocal();


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            //Si no existe en la BD local se añade
                            if (existeEnOnline==0){

                                ContentValues values = new ContentValues();
                                values.put(DBHelper.entidadAlumnos.COLUMN_NAME_NOMBRE,etNombre.getText().toString());
                                values.put(DBHelper.entidadAlumnos.COLUMN_NAME_APELLIDO1,etApellido1.getText().toString());
                                values.put(DBHelper.entidadAlumnos.COLUMN_NAME_APELLIDO2, etApellido2.getText().toString());
                                values.put(DBHelper.entidadAlumnos.COLUMN_NAME_DNI, etDni.getText().toString());
                                values.put(DBHelper.entidadAlumnos.COLUMN_NAME_CURSO, etCurso.getText().toString());
                                dbsqlite.insert(DBHelper.entidadAlumnos.TABLE_NAME, null, values);

                                Toast.makeText(getApplicationContext(), (R.string.alumno_añadido), Toast.LENGTH_SHORT).show();

                                existeEnLocal=1;
                            }

                            //Si existe en la online pero no en la local, se añade
                            if (existeEnLocal==0){

                                ContentValues values = new ContentValues();
                                values.put(DBHelper.entidadAlumnos.COLUMN_NAME_NOMBRE,nombreOnline);
                                values.put(DBHelper.entidadAlumnos.COLUMN_NAME_APELLIDO1, apellido1Online);
                                values.put(DBHelper.entidadAlumnos.COLUMN_NAME_APELLIDO2, apellido2Online);
                                values.put(DBHelper.entidadAlumnos.COLUMN_NAME_DNI, dniOnline);
                                values.put(DBHelper.entidadAlumnos.COLUMN_NAME_CURSO, cursoOnline);
                                dbsqlite.insert(DBHelper.entidadAlumnos.TABLE_NAME, null, values);

                                Toast.makeText(getApplicationContext(), (R.string.insertado), Toast.LENGTH_SHORT).show();

                            }

                        }
                    }, 2000);
                }



            }
        });



    }


    public void CompruebaOnline(){

        dbOnline = FirebaseFirestore.getInstance();

        //Comprueba si el DNI introducido existe en la BD online
        DocumentReference DatosCasas = dbOnline.collection("alumnos").document(etDni.getText().toString());
        DatosCasas.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                        nombreOnline= (String) document.get("nombre");
                        apellido1Online=(String) document.get("apellido1");
                        apellido2Online=(String) document.get("apellido2");
                        dniOnline=(String) document.get("dni");
                        cursoOnline=(String) document.get("curso");

                                /*
                                System.out.println((String) document.get("nombre")+" "+(String) document.get("apellido1")+" "+(String) document.get("apellido2")+" "+
                                        (String) document.get("dni")+" "+(String) document.get("curso"));
                                System.out.println("El DNI ya existe");
                                 */
                        Toast.makeText(getApplicationContext(), (R.string.existe_online), Toast.LENGTH_SHORT).show();

                        existeEnOnline=1;



                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });



    }


    public void CompruebaLocal(){

        //Busca si el DNI existe en la BD local
        dbHelper = new DBHelper(getApplicationContext());
        dbsqlite = dbHelper.getWritableDatabase();
        Cursor cursorcantidad = dbsqlite.query(DBHelper.entidadAlumnos.TABLE_NAME,null,null,null,null,null,null);
        while (cursorcantidad.moveToNext()) {
            String nombreL = cursorcantidad.getString(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadAlumnos.COLUMN_NAME_NOMBRE));
            String apellido1L = cursorcantidad.getString(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadAlumnos.COLUMN_NAME_APELLIDO1));
            String apellido2L = cursorcantidad.getString(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadAlumnos.COLUMN_NAME_APELLIDO2));
            String dniL = cursorcantidad.getString(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadAlumnos.COLUMN_NAME_DNI));
            String cursoL = cursorcantidad.getString(cursorcantidad.getColumnIndexOrThrow(DBHelper.entidadAlumnos.COLUMN_NAME_CURSO));

            if (etDni.getText().toString().equals(dniL)){
                System.out.println("El DNI ya existe");
                Toast.makeText(getApplicationContext(), (R.string.existe_local), Toast.LENGTH_SHORT).show();
                existeEnLocal=1;
                existeEnOnline=1;
            }

        }


    }


}
