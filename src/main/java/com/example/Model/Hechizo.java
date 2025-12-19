package com.example.Model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hechizos")
public class Hechizo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Enumerated(EnumType.STRING)
    private Hechicin nombre;
    
    public Hechizo() {
    }
    
    public Hechizo(Hechicin nombre) {
        this.nombre = nombre;
    }
    
    public static Hechizo bolaDeFuego() {
        return new Hechizo(Hechicin.BOLA_DE_FUEGO);
    }
    
    public static Hechizo congelacion() {
        return new Hechizo(Hechicin.CONGELACION);
    }
    
    public static Hechizo rayo() {
        return new Hechizo(Hechicin.RAYO);
    }
    
    public static Hechizo fromString(String nombre) {
        try {
            Hechicin hechicin = Hechicin.valueOf(nombre.toUpperCase());
            return new Hechizo(hechicin);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Hechicin getNombre() {
        return nombre;
    }
    
    public void setNombre(Hechicin nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hechizo hechizo = (Hechizo) o;
        return Objects.equals(nombre, hechizo.nombre);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
    
    @Override
    public String toString() {
        return nombre.toString();
    }
}
