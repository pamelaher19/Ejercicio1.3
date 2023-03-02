package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio13.Configuracion.Conexion;
import com.example.ejercicio13.Configuracion.Operaciones;

public class Personas extends AppCompatActivity {


    EditText editNombres, editApellidos, editEdad, editCorreo, editDireccion;
    Button btGuardar, Volver;

    @Override public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas);

        editNombres = (EditText) findViewById(R.id.txtNombre);
        editApellidos = (EditText) findViewById(R.id.txtApellido);
        editEdad = (EditText) findViewById(R.id.txtEdad);
        editCorreo = (EditText) findViewById(R.id.txtCorreo);
        editDireccion = (EditText) findViewById(R.id.txtDireccion);
        btGuardar = (Button) findViewById(R.id.btnGuardar);
        Volver = (Button) findViewById(R.id.btnVolver);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarPersonas();
            }
        });

        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void agregarPersonas() {

        Conexion conexion = new Conexion(this, Operaciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(Operaciones.nombres, editNombres.getText().toString());
        valores.put(Operaciones.apellidos, editApellidos.getText().toString());
        valores.put(Operaciones.edad, editEdad.getText().toString());
        valores.put(Operaciones.correo, editCorreo.getText().toString());
        valores.put(Operaciones.direccion, editDireccion.getText().toString());

        Long resultado = db.insert(Operaciones.tablapersonas, Operaciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registrado, Codigo " + resultado.toString()
                ,Toast.LENGTH_LONG).show();

        db.close();

        limpiarPantalla();

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    private void limpiarPantalla() {
        editNombres.setText("");
        editApellidos.setText("");
        editEdad.setText("");
        editCorreo.setText("");
        editDireccion.setText("");
    }

}