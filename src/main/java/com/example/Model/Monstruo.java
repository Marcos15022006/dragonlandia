package com.example.Model;
import jakarta.persistence.*;
@Entity
@Table(name = "monstruos")

public class Monstruo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nombre;
    private int vida;
    private int fuerza;
    private tipo tipo;   
    
    @ManyToOne
    @JoinColumn(name = "bosque_id")
    private Bosque bosque;

    public Monstruo(int id, String nombre, int vida, int fuerza, tipo tipo) {
        this.id = id;
        this.nombre = nombre;
        setVida(vida);
        this.fuerza= fuerza;
        this.tipo= tipo;
    }

    public Monstruo(){
        
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

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public tipo geTipo() {
        return tipo;
    }

    public void setTipo(tipo tipo){
        this.tipo=tipo;
    }


    public void atacar(Mago mago) {
        int nuevaVida = mago.getVida() - this.fuerza;
        mago.setVida(nuevaVida);
    }
}
