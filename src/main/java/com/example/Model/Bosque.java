package com.example.Model;
import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "bosques")

/*Posteriormente añadir que en un bosque puede haber varios monstruos */

public class Bosque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne
    @JoinColumn(name = "monstruo_jefe_id")
    private Monstruo monstruoJefe;
    
    private String nombre;
    private int nivelPeligro;

    @OneToMany ( mappedBy = "bosque")
    private List<Monstruo> listaMontruos;
    


    public Bosque(int id, String nombre, int nivelPeligro, Monstruo monstruoJefe) {
        this.id = id;
        this.nombre = nombre;
        this.nivelPeligro= nivelPeligro;
        this.monstruoJefe= monstruoJefe;
    }

    public Bosque(){
        
    }

    public void setListaMontruos(List<Monstruo> listaMontruos) {
        this.listaMontruos = listaMontruos;
    }

    public List<Monstruo> getListaMontruos() {
        return listaMontruos;
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

    public int getnivelPeligro() {
        return nivelPeligro;
    }

    public void setNivelPeligro(int nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }

    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    public void setMonstruoJefe(Monstruo monstruo) {
        this.monstruoJefe = monstruo;
    }

    public void mostrarJefe() {
        System.out.println("El mounstro Jefe del bosque: " + monstruoJefe.getNombre() +", " + monstruoJefe.geTipo()+", vida: "+monstruoJefe.getVida()+", fuerza:"+monstruoJefe.getFuerza() );
    }

    public void cambiarJefe( Monstruo nuevoJefe){
        this.monstruoJefe=nuevoJefe;
    }
}