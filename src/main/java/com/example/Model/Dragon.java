package com.example.Model;


import jakarta.persistence.*;


@Entity
@Table(name = "dragones")
public class Dragon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private int intensidadFuego;

    private int resistencia;

    /*Un Dragón habita en un Bosque */
    @ManyToOne
    private Bosque bosque;

    

    public Dragon(String nombre, int intensidadFuego, int resistencia) {
        this.nombre = nombre;
        this.intensidadFuego = intensidadFuego;
        this.resistencia = resistencia;
    }

    public Dragon(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIntensidadFuego() {
        return intensidadFuego;
    }

    public void setIntensidadFuego(int intensidadFuego) {
        this.intensidadFuego = intensidadFuego;
    }

    public int getResistencia() {
        return resistencia;
    }

    public Bosque getBosque() {
        return bosque;
    }

    public void setBosque(Bosque bosque) {
        this.bosque = bosque;
    }

    public void setResistencia(int resistencia) {
        if (resistencia < 0) {
            this.resistencia = 0;
        } else {
            this.resistencia = resistencia;
        }
    }

    public void exhalar(Monstruo monstruo){
        int nuevaVida = monstruo.getVida() - this.intensidadFuego;
        monstruo.setVida(nuevaVida);
    }

}
