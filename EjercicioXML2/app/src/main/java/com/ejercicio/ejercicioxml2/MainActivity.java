package com.ejercicio.ejercicioxml2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://www.aemet.es/xml/municipios/localidad_01059.xml";

    private TextView tvTempMax, tvTempMin, tvSensMax, tvSensMin, tvHumMax, tvHumMin;
    private List<Tiempo> tiempos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTempMax = findViewById(R.id.tvTempMax);
        tvTempMin = findViewById(R.id.tvTempMin);
        tvSensMax = findViewById(R.id.tvSensMax);
        tvSensMin = findViewById(R.id.tvSensMin);
        tvHumMax = findViewById(R.id.tvHumMax);
        tvHumMin = findViewById(R.id.tvHumMin);

        new CargarXmlTask().execute();
    }

    private class CargarXmlTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            RssParserXMLPull xmlPullParser = new RssParserXMLPull(URL);
            tiempos = xmlPullParser.parse();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            Tiempo tiempo = tiempos.stream().filter(t -> mismoDia(new Date(), t.getDia())).findFirst().orElse(null);
            tvTempMax.setText(tiempo.getTempMax() + " ºC");
            tvTempMin.setText(tiempo.getTempMin() + " ºC");
            tvSensMax.setText(tiempo.getSensMax() + " ºC");
            tvSensMin.setText(tiempo.getSensMin() + " ºC");
            tvHumMax.setText(tiempo.getHumMax() + " %");
            tvHumMin.setText(tiempo.getHumMin() + " %");
        }
    }

    private static boolean mismoDia(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            Calendar cal = Calendar.getInstance();
            Calendar cal1 = Calendar.getInstance();
            cal.setTime(date1);
            cal.setTime(date2);
            return (cal.get(Calendar.YEAR) == cal1.get(Calendar.YEAR) && cal.get(Calendar.DAY_OF_YEAR) == cal1.get(Calendar.DAY_OF_YEAR));
        }
        return false;
    }

}