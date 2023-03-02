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

public class SeleccionarPersona extends AppCompatActivity {

    EditText nombres, apellidos, edades, correos, direcciones, codigos;
    Button btActualizar, btEliminar, btRegresar;

    @Override public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_persona);

        codigos = (EditText) findViewById(R.id.txtCodigo);
        nombres = (EditText) findViewById(R.id.txtNom);
        apellidos = (EditText) findViewById(R.id.txtApelli);
        edades = (EditText) findViewById(R.id.txtEd);
        correos = (EditText) findViewById(R.id.txtCorreos);
        direcciones = (EditText) findViewById(R.id.txtDirecc);

        btActualizar = (Button) findViewById(R.id.btnActualizar);
        btEliminar = (Button) findViewById(R.id.btnEliminar);
        btRegresar = (Button) findViewById(R.id.btnVolver2) ;

        codigos.setText(getIntent().getStringExtra("codigo"));
        nombres.setText(getIntent().getStringExtra("nombre"));
        apellidos.setText(getIntent().getStringExtra("apellidos"));
        edades.setText(getIntent().getStringExtra("edad"));
        correos.setText(getIntent().getStringExtra("correo"));
        direcciones.setText(getIntent().getStringExtra("direccion"));

        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActualizarPerson();

            }
        });

        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EliminarPerson();
            }
        });

        btRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Lista.class);
                startActivity(intent);
            }
        });
    }

    private void ActualizarPerson() {
        Conexion conexion = new Conexion(this, Operaciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = codigos.getText().toString();

        ContentValues valores = new ContentValues();
        valores.put(Operaciones.nombres, nombres.getText().toString());
        valores.put(Operaciones.apellidos, apellidos.getText().toString());
        valores.put(Operaciones.edad, edades.getText().toString());
        valores.put(Operaciones.correo, correos.getText().toString());
        valores.put(Operaciones.direccion, direcciones.getText().toString());

        db.update(Operaciones.tablapersonas, valores , Operaciones.id +" = "+obtenerCodigo, null);
        db.close();

        Intent intent = new Intent(getApplicationContext(),Lista.class);
        startActivity(intent);

    }

    private void EliminarPerson() {
        Conexion conexion = new Conexion(this, Operaciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = codigos.getText().toString();

        db.delete(Operaciones.tablapersonas,Operaciones.id +" = "+ obtenerCodigo, null);

        Toast.makeText(getApplicationContext(), "Registro eliminado, Codigo " + obtenerCodigo
                ,Toast.LENGTH_LONG).show();
        db.close();

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }
}