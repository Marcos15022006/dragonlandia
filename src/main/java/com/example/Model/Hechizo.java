package com.example.Model;

import java.util.Objects;

/**
 * Representa un hechizo que puede lanzar un mago
 */
public class Hechizo {
    
    public static final Hechizo BOLA_DE_FUEGO = new Hechizo("BOLA_DE_FUEGO");
    public static final Hechizo BOLA_DE_NIEVE = new Hechizo("BOLA_DE_NIEVE");
    public static final Hechizo RAYO = new Hechizo("RAYO");
    public static final Hechizo PUTREFACCION = new Hechizo("PUTREFACCION");
    
    private String nombre;
    
    private Hechizo(String nombre) {
        this.nombre = nombre;
    }
    
    public Hechizo() {
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene un hechizo por su nombre
     * @param nombre el nombre del hechizo
     * @return el hechizo correspondiente
     */
    public static Hechizo valueOf(String nombre) {
        switch (nombre.toUpperCase()) {
            case "BOLA_DE_FUEGO":
                return BOLA_DE_FUEGO;
            case "BOLA_DE_NIEVE":
                return BOLA_DE_NIEVE;
            case "RAYO":
                return RAYO;
            case "PUTREFACCION":
                return PUTREFACCION;
            default:
                throw new IllegalArgumentException("Hechizo desconocido: " + nombre);
        }
    }
    
    public static Hechizo[] values() {
        return new Hechizo[] { BOLA_DE_FUEGO, BOLA_DE_NIEVE, RAYO, PUTREFACCION };
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Hechizo hechizo = (Hechizo) obj;
        return Objects.equals(nombre, hechizo.nombre);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
