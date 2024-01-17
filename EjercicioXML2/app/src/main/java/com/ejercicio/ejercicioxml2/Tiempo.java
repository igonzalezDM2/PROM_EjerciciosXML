package com.ejercicio.ejercicioxml2;

import java.util.Date;

public class Tiempo {
    private String direccion;
    private int tempMax, tempMin, sensMax, sensMin, humMax, humMin, velocidad;

    private Date dia;

    public String getDireccion() {
        return direccion;
    }
    public Tiempo setDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }
    public int getTempMax() {
        return tempMax;
    }
    public Tiempo setTempMax(int tempMax) {
        this.tempMax = tempMax;
        return this;
    }
    public int getTempMin() {
        return tempMin;
    }
    public Tiempo setTempMin(int tempMin) {
        this.tempMin = tempMin;
        return this;
    }
    public int getSensMax() {
        return sensMax;
    }
    public Tiempo setSensMax(int sensMax) {
        this.sensMax = sensMax;
        return this;
    }
    public int getSensMin() {
        return sensMin;
    }
    public Tiempo setSensMin(int sensMin) {
        this.sensMin = sensMin;
        return this;
    }
    public int getHumMax() {
        return humMax;
    }
    public Tiempo setHumMax(int humMax) {
        this.humMax = humMax;
        return this;
    }
    public int getHumMin() {
        return humMin;
    }
    public Tiempo setHumMin(int humMin) {
        this.humMin = humMin;
        return this;
    }
    public int getVelocidad() {
        return velocidad;
    }
    public Tiempo setVelocidad(int velocidad) {
        this.velocidad = velocidad;
        return this;
    }

    public Date getDia() {
        return dia;
    }
    public Tiempo setDia(Date dia) {
        this.dia = dia;
        return this;
    }
}
