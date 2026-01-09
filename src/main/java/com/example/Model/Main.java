package com.example.Model;

import com.example.Controller.Controlador;
import com.example.View.Interfaz;

/**
 * Clase principal que inicia la aplicación
 */
public class Main {
    /**
     * Método principal que ejecuta el juego
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        try {
            Interfaz interfaz = new Interfaz();
            interfaz.iniciar();
        } finally {
            Controlador.cerrarEntityManagerFactory();
            System.out.println("EntityManagerFactory cerrada correctamente");
        }
    }
}