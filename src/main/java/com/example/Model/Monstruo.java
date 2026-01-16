package com.example.model;
import jakarta.persistence.*;
@Entity
@Table(name = "monstruos")

/**
 * Representa un monstruo con características de combate
 */
public class Monstruo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nombre;
    private int vida;
    private int fuerza;
    
    @Enumerated(EnumType.STRING)
    private Tipo tipo;   
    
    @ManyToOne
    @JoinColumn(name = "bosque_id", nullable = true)
    private Bosque bosque;

    

    public Monstruo(int id, String nombre, int vida, int fuerza, Tipo tipo) {
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

    public Tipo geTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo){
        this.tipo=tipo;
    }

    public Bosque getBosque() {
        return bosque;
    }

    public void setBosque(Bosque bosque) {
        this.bosque = bosque;
    }

    /**
     * Ataca a un mago reduciendo su vida
     * @param mago el mago atacado
     */
    public void atacar(Mago mago) {
        int nuevaVida = mago.getVida() - this.fuerza;
        mago.setVida(nuevaVida);
    }
}
