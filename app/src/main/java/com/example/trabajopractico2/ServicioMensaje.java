package com.example.trabajopractico2;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Telephony;
import android.util.Log;

import androidx.annotation.Nullable;

public class ServicioMensaje extends Service {
    private Uri mensajes = Uri.parse("content://sms/");
    private Cursor cursor;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SmsLog();
        return  START_STICKY;
    }


    public void SmsLog (){

        new Thread() {

            public void rum (){

                while(true) {
                    Log.d("mensaje: ", Thread.currentThread().getName() + " " + "Lanzar tarea");

                    cursor = ServicioMensaje.this.getContentResolver().query(mensajes, null,
                            null, null, null);
                    //while (cursor.moveToNext()) {
                    if ( cursor.getCount() >= 5) {
                        for (int f = 0; f < 5; f++) {
                            cursor.moveToPosition(f);
                            for (int i = 0; i < cursor.getColumnCount(); i++) {
                                Log.d(cursor.getColumnName(i) + "", " (" + i + ") " + cursor.getString(i) + "");
                            }
                            Log.d("One row finished", "**************************************************");
                        }
                    }
                    try {
                        Thread.sleep(9000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }

        }
    }.start();
        cursor = this.getContentResolver().query(mensajes, null,
                null, null, null);


        for (int f=0; f<5; f++) {
            cursor.moveToPosition(f);
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                Log.d(cursor.getColumnName(i) + "", " (" + i +") "+cursor.getString(i) + "");
            }
            Log.d("One row finished","**************************************************");
        }

    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
