package com.ejercicio.ejercicioxml1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "https://www.europapress.es/rss/rss.aspx";

    private List<Noticia> noticias;
    private ListView lvNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noticias = new LinkedList<>();
        lvNoticias = findViewById(R.id.lvNoticias);
        new CargarXmlTask().execute();

        AdaptadorNoticia adaptadorNoticia = new AdaptadorNoticia(this, noticias);
        lvNoticias.setAdapter(adaptadorNoticia);


    }

    //Con la propiedad onClick en los botones
    public void cargarXMLConSAX (){
        RssParserSAX saxparser = new RssParserSAX(URL);
        noticias.addAll(saxparser.parse());
    }



    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {
        protected Boolean doInBackground(String... params) {
            RssParserSAX saxparser = new RssParserSAX(URL);
            noticias.addAll(saxparser.parse());
            return true;
        }
            protected void onPostExecute(Boolean result) {
                AdaptadorNoticia adaptadorNoticia = new AdaptadorNoticia(MainActivity.this, noticias);
                lvNoticias.setAdapter(adaptadorNoticia);
            }
    }

}