package com.example.Model;

import com.example.Controller.Controlador;
import com.example.View.Interfaz;

public class Main {
    public static void main(String[] args) {
        try {
            Interfaz interfaz = new Interfaz();
            interfaz.iniciar();
        } finally {
            Controlador.cerrarSessionFactory();
            System.out.println("SessionFactory cerrada correctamente");
        }
    }
}