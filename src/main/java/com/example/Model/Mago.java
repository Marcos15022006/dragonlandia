
package com.example.Model;
import jakarta.persistence.*;
@Entity
@Table(name = "magos")

public class Mago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int vida;
    private int nivelMagia;

    public Mago(int id, String nombre, int vida, int nivelMagia) {
        this.id = id;
        this.nombre = nombre;
        setVida(vida);
        setNivelMagia(nivelMagia);
    }
    public Mago(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else {
            this.vida = vida;
        }
    }

    public int getNivelMagia() {
        return nivelMagia;
    }

    public void setNivelMagia(int nivelMagia) {
        if (nivelMagia < 0) {
            this.nivelMagia = 0;
        } else {
            this.nivelMagia = nivelMagia;
        }
    }

    public void lanzarHechizo(Monstruo mostruo) {
        int nuevaVida = mostruo.getVida() - this.nivelMagia;
        mostruo.setVida(nuevaVida);
    }
    
}
