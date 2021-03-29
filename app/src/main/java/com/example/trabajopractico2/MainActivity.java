package com.example.trabajopractico2;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IniciarComponentes();

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M //permiso resumido
                && checkSelfPermission(Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_SMS},9000);
        }
        ServicioMensaje();

        Intent i = new Intent(this, ServicioMensaje.class);
        startService(i);

        Log.d("mensaje", Thread.currentThread().getName() +"onCreate del MainActivity");
    }
    public void IniciarComponentes(){

    }

    private void ServicioMensaje() {


        Uri mensajes = Uri.parse("content://sms/");

        Cursor cursor = this.getContentResolver().query(mensajes, null,
                null, null, null);

        Log.d("mensaje","Listado...");
        while (cursor.moveToNext()) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                Log.d(cursor.getColumnName(i) + "", cursor.getString(i) + "");
            }
            Log.d("One row finished", "**************************************************");
        }

    }

    }