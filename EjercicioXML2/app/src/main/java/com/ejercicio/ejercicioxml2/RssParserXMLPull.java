package com.ejercicio.ejercicioxml2;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RssParserXMLPull {
    private URL rssUrl;
    private boolean prediccion = false;
    private boolean dia = false;
    private boolean temperatura = false;
    private boolean sensTermica = false;
    private boolean humedad = false;
    private boolean viento = false;
    private Tiempo tiempo = null;

    public RssParserXMLPull(String url) {
        try {
            this.rssUrl = new URL(url);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tiempo> parse() {
        List<Tiempo> tiempos = null;
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(this.getInputStream(), null);
            int evento = parser.getEventType();
            Tiempo tiempoActual = null;

            while (evento != XmlPullParser.END_DOCUMENT) {
                String etiqueta = null;
                switch (evento) {
                    case XmlPullParser.START_DOCUMENT:
                        tiempos = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        etiqueta = parser.getName();
                        Log.i("ETIQUETA: ", etiqueta);
                        if ("prediccion".equals(etiqueta)) {
                            prediccion = true;
                        } else if (prediccion && "dia".equals(etiqueta)) {
                            dia = true;
                            String fechaStr = parser.getAttributeValue(0);
                            tiempo = new Tiempo();
                            if (fechaStr != null && !fechaStr.isEmpty()) {
                                tiempo.setDia(new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr));
                            }

                        } else if (dia && "temperatura".equals(etiqueta)) {
                            temperatura = true;
                        } else if (dia && "sens_termica".equals(etiqueta)) {
                            sensTermica = true;
                        } else if (dia && "humedad_relativa".equals(etiqueta)) {
                            humedad = true;
                        } else if (dia && "viento".equals(etiqueta)) {
                            viento = true;
                        } else if ("maxima".equals(etiqueta)) {
                            if (temperatura) {
                                tiempo.setTempMax(parseInt(parser.nextText()));
                            } else if (sensTermica) {
                                tiempo.setSensMax(parseInt(parser.nextText()));
                            } else if (humedad) {
                                tiempo.setHumMax(parseInt(parser.nextText()));
                            }
                        } else if ("minima".equals(etiqueta)) {
                            if (temperatura) {
                                tiempo.setTempMin(parseInt(parser.nextText()));
                            } else if (sensTermica) {
                                tiempo.setSensMin(parseInt(parser.nextText()));
                            } else if (humedad) {
                                tiempo.setHumMin(parseInt(parser.nextText()));
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        etiqueta = parser.getName();
                        if ("prediccion".equals(etiqueta)) {
                            prediccion = false;
                        } else if (prediccion && "dia".equals(etiqueta)) {
                            tiempos.add(tiempo);
                            dia = false;
                        } else if (dia && "temperatura".equals(etiqueta)) {
                            temperatura = false;
                        } else if (dia && "sens_termica".equals(etiqueta)) {
                            sensTermica = false;
                        } else if (dia && "humedad_relativa".equals(etiqueta)) {
                            humedad = false;
                        } else if (dia && "viento".equals(etiqueta)) {
                            viento = false;
                        }
                        break;
                }
                evento = parser.next();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return tiempos;
    }


    private InputStream getInputStream() {
        try {
            return rssUrl.openConnection().getInputStream();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Integer parseInt(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException | NullPointerException e) {}
        }
        return null;
    }
}
