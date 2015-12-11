package com.foc.pmdm.u5.ejemplohttp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Si no utilizamos un hilo para hacer la descarga
        /*
        // permitimos todas las políticas desaconsejadas
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();

        // Permitimos solo aquello que necesitamos
        StrictMode.ThreadPolicy policy2=new StrictMode.ThreadPolicy.Builder().permitCustomSlowCalls()
                .permitNetwork()
                .penaltyDialog().build();

        //StrictMode.setThreadPolicy(policy);
        StrictMode.setThreadPolicy(policy2);

        */
        Button btDescarga=(Button)findViewById(R.id.btDescarga);
        if(estaRedActiva())
        {
            btDescarga.setEnabled(true);
        }
        else
        {
            btDescarga.setEnabled(false);
            Toast.makeText(this,"Sin conexión",Toast.LENGTH_LONG).show();
        }
    }

    public void botonDescarga(View v)
    {
        EditText etURL=(EditText)findViewById(R.id.etURL);
        String strURL=etURL.getText().toString();

        // Modo tradicional de crear un hilo y ejecutarlo
        HiloDescarga hd=new  HiloDescarga(strURL);
        hd.start();

        // Modo más fácil desde android
        this.runOnUiThread(new HiloDescarga(strURL));  // Thread implementa el interface runnable

    }


    public boolean estaRedActiva()
    {

         // Necesitamos el permiso android.permission.ACCESS_NETWORK_STATE en AndroidManifest
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=null;
        try {
            ni = cm.getActiveNetworkInfo();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(ni!=null && ni.isConnected()) // Hay conexión
            return true;
        else
            return false;
    }
}
