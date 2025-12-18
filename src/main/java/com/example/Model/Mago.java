
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
        return conjuro;
    }

    public void setConjuro(List<Hechizo> conjuro) {
        this.conjuro = conjuro;
    }

    public void lanzarHechizo(Monstruo mostruo) {
        int nuevaVida = mostruo.getVida() - this.nivelMagia;
        mostruo.setVida(nuevaVida);
    }

    public void lanzarHechizo(Monstruo mostruo, Hechizo hechicin) {
        int dano = 0;
        if(conjuro.contains(hechicin)==false){
            System.out.println("El mago no conoce ese hechizo, le explota en la cara restandole un punto de vida.");
            int nuevaVida = this.getVida() - 1;
            this.setVida(nuevaVida);
            
        }else{
            switch (hechicin) {
                case BOLA_DE_FUEGO:
                    dano = this.nivelMagia +5;
                    break;
                case BOLA_DE_NIEVE:
                    dano = mostruo.getVida();
                    break;
                case RAYO:
                    dano = this.nivelMagia +3;
                    break;
                case PUTREFACCION:
                    dano = 10;
                    break;
                default:
                    dano = -1;
                    break;
            }
            if (dano>0) {
                int nuevaVida = mostruo.getVida() - (dano);
                mostruo.setVida(nuevaVida);
                System.out.println("Mago "+this.getNombre()+" lanza hechizo "+hechicin+" al monstruo "+mostruo.getNombre()+". Vida restante del monstruo: "+mostruo.getVida());
            } else{
                int nuevaVida = this.getVida() - (dano);
                this.setVida(nuevaVida);
            }
    
        
        }
    }
    
}
