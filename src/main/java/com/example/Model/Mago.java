
package com.example.Model;
import java.util.List;

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
    
    @ElementCollection
    @CollectionTable(name = "mago_hechizos", joinColumns = @JoinColumn(name = "mago_id"))
    @Column(name = "hechizo")
    private List<String> conjuroNombres;
    
    @Transient
    private List<Hechizo> conjuro;

    public Mago(int id, String nombre, int vida, int nivelMagia, List<Hechizo> conjuro) {
        this.id = id;
        this.nombre = nombre;
        setVida(vida);
        setNivelMagia(nivelMagia);
        this.conjuro = conjuro;
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

    public List<Hechizo> getConjuro() {
        if (conjuro == null && conjuroNombres != null) {
            conjuro = new java.util.ArrayList<>();
            for (String nombre : conjuroNombres) {
                conjuro.add(Hechizo.valueOf(nombre));
            }
        }
        return conjuro;
    }

    public void setConjuro(List<Hechizo> conjuro) {
        this.conjuro = conjuro;
        if (conjuro != null) {
            conjuroNombres = new java.util.ArrayList<>();
            for (Hechizo h : conjuro) {
                conjuroNombres.add(h.getNombre());
            }
        }
    }

    public void lanzarHechizo(Monstruo mostruo) {
        int nuevaVida = mostruo.getVida() - this.nivelMagia;
        mostruo.setVida(nuevaVida);
    }

    public void lanzarHechizo(Monstruo mostruo, Hechizo hechicin) {
        int dano = 0;
        List<Hechizo> hechizosConocidos = getConjuro();
        boolean conoceHechizo = false;
        
        if (hechizosConocidos != null) {
            for (Hechizo h : hechizosConocidos) {
                if (h.equals(hechicin)) {
                    conoceHechizo = true;
                }
            }
        }
        
        if (!conoceHechizo) {
            System.out.println("El mago no conoce ese hechizo, le explota en la cara restandole un punto de vida.");
            int nuevaVida = this.getVida() - 1;
            this.setVida(nuevaVida);
        } else {
            if (hechicin.equals(Hechizo.BOLA_DE_FUEGO)) {
                dano = this.nivelMagia + 5;
            } else if (hechicin.equals(Hechizo.BOLA_DE_NIEVE)) {
                dano = mostruo.getVida();
            } else if (hechicin.equals(Hechizo.RAYO)) {
                dano = this.nivelMagia + 3;
            } else if (hechicin.equals(Hechizo.PUTREFACCION)) {
                dano = 10;
            } else {
                dano = -1;
            }
            
            if (dano > 0) {
                int nuevaVida = mostruo.getVida() - dano;
                mostruo.setVida(nuevaVida);
                System.out.println("Mago " + this.getNombre() + " lanza hechizo " + hechicin + " al monstruo " + mostruo.getNombre() + ". Vida restante del monstruo: " + mostruo.getVida());
            } else {
                int nuevaVida = this.getVida() - dano;
                this.setVida(nuevaVida);
            }
        }
    }
    
}
