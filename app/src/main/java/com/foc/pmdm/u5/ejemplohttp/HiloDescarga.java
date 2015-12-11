package com.foc.pmdm.u5.ejemplohttp;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by javacasm on 10/12/2015.
 */
public  class HiloDescarga extends Thread
{
    String strURL;
    public HiloDescarga(String sURL)
    {
        this.strURL=sURL;
    }

    public void run()
    {
        URL url;
        try {
            url = new URL(strURL);
            // Necesitamos añadir el permiso android.permission.INTERNET a AndroidManifest
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            BufferedReader in=null;
            try
            {
                // Leeremos línea a línea
                in=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line=null;
                Log.i("TAG","Comienza la descarga de "+strURL);
                while((line=in.readLine())!=null)
                {
                    Log.i("TAG", line);
                }
                Log.i("TAG","Finaliza la descarga de "+strURL);
        }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                in.close();
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
