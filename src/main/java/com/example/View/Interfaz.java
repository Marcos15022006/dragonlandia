package com.example.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.example.Controller.Controlador;
import com.example.Model.Bosque;
import com.example.Model.Dragon;
import com.example.Model.Mago;
import com.example.Model.Monstruo;
import com.example.Model.Tipo;

public class Interfaz {

    static Scanner scanner = new Scanner(System.in);
    static Controlador controlador = new Controlador();
    static List<Monstruo> monstruos = new ArrayList<>();

    // Función general para crear un monstruo y añadirlo al bosque
    public Monstruo crearMonstruo(Bosque bosque) {
        System.out.println("Creando monstruo...");
        System.out.print("Nombre del monstruo: ");
        String nombreMonstruo = scanner.nextLine();
        System.out.print("Vida del monstruo: ");
        int vidaMonstruo = scanner.nextInt();
        System.out.print("Fuerza del monstruo: ");
        int fuerzaMonstruo = scanner.nextInt();
        System.out.print("Tipo del monstruo (SPECTRO, TROLL, OGRO): ");
        scanner.nextLine(); 
        String tipMonstruo = scanner.nextLine();
        String tipoMonstruo= tipMonstruo.toUpperCase();
        while (!tipoMonstruo.equals("SPECTRO") && !tipoMonstruo.equals("TROLL") && !tipoMonstruo.equals("OGRO")) {
            System.out.println("Tipo de monstruo inválido. Vuelve a intentarlo:");
            tipoMonstruo = scanner.nextLine();
        }
        
        Monstruo monstruo = new Monstruo();
        monstruo.setNombre(nombreMonstruo);
        monstruo.setVida(vidaMonstruo);
        monstruo.setFuerza(fuerzaMonstruo);
        monstruo.setTipo(Tipo.valueOf(tipoMonstruo.toUpperCase()));
        
        System.out.println("Monstruo creado: " + monstruo.getNombre() + " con vida " + monstruo.getVida() 
                + ", fuerza " + monstruo.getFuerza() + ", tipo " + monstruo.geTipo());
        

        System.out.println("Desea añadir este monstruo al bosque? (S/N)");
        String respuesta = scanner.nextLine().toUpperCase();
        while (!respuesta.equals("S") && !respuesta.equals("N")) {
            System.out.println("Respuesta inválida. Vuelve a intentarlo:");
            respuesta = scanner.nextLine().toUpperCase();
        }
        if (respuesta.equals("S")) {
            bosque.addMonstruo(monstruo);
        }
        
        return monstruo;
    }

    public void iniciar() {
        System.out.println("Bienvenido al simulador de batallas entre magos y monstruos!");
        System.out.println("Por favor, ingrese los datos del mago y el monstruo para comenzar la simulación.");
        
        System.out.println("Creando mago...");
        System.out.print("Nombre del mago: ");
        String nombreMago = scanner.nextLine();
        System.out.print("Vida del mago: ");
        int vidaMago = scanner.nextInt();
        System.out.print("Nivel de magia del mago: ");
        int nivelMagiaMago = scanner.nextInt();
        scanner.nextLine();
        Mago mago = new Mago();
        mago.setNombre(nombreMago);
        mago.setVida(vidaMago);
        mago.setNivelMagia(nivelMagiaMago);
        controlador.guardarMago(mago);
        System.out.println("Mago creado: " + mago.getNombre() + " con vida " + mago.getVida() + " y nivel de magia " + mago.getNivelMagia());
        
        System.out.println("Creando bosque...");
        System.out.print("Nombre del bosque: ");
        String nombreBosque = scanner.nextLine();
        System.out.print("Nivel de peligro del bosque: ");
        int nivelPeligroBosque = scanner.nextInt();
        scanner.nextLine();
        Bosque bosque = new Bosque();
        bosque.setNombre(nombreBosque);
        bosque.setNivelPeligro(nivelPeligroBosque);
        
        // Crear primer monstruo
        Monstruo monstruo = crearMonstruo(bosque);
        
        
        System.out.println("Seleccionando monstruo jefe para el bosque...");
        
        // Opción para crear más monstruos
        String letra = "S";
        while (letra.equals("S")) {
            System.out.println("¿Desea crear otro monstruo? (S/N)");
            letra = scanner.nextLine().toUpperCase();
            while (!letra.equals("S") && !letra.equals("N")) {
                System.out.println("Respuesta inválida. Vuelve a intentarlo:");
                letra = scanner.nextLine().toUpperCase();
            }
            
            if (letra.equals("S")) {
                crearMonstruo(bosque);
            }
        }
        
        // Guardar primero el bosque SIN el monstruo jefe
        System.out.println("Bosque creado: " + bosque.getNombre() + " con nivel de peligro " + bosque.getnivelPeligro());
        System.out.println("Total de monstruos en el bosque: " + monstruos.size());
        controlador.guardarBosque(bosque);
        
        // Ahora guardar los monstruos con la referencia al bosque
        for (Monstruo m : monstruos) {
            m.setBosque(bosque);
            controlador.guardarMonstruo(m);
        }
        
        // Finalmente, actualizar el bosque con el monstruo jefe
        bosque.setMonstruoJefe(monstruo);
        bosque.setListaMontruos(monstruos);
        controlador.actualizarBosque(bosque);

        //Creando un Dragon
        System.out.println("Creando un Dragón...");
        System.out.println("Nombre del dragón: ");
        String nombreDragon = scanner.nextLine();
        System.out.println("Vida del dragón: ");
        int vidaDragon = scanner.nextInt();
        System.out.println("Fuerza del dragón: ");
        int fuerzaDragon = scanner.nextInt();
        Dragon dragon = new Dragon();
        dragon.setNombre(nombreDragon);
        dragon.setResistencia(vidaDragon);
        dragon.setIntensidadFuego(fuerzaDragon);
        dragon.setBosque(bosque);
        controlador.guardarDragon(dragon);

        //Actualizando el bosque con el dragón
        bosque.addDragon(dragon);
        controlador.actualizarBosque(bosque);
        
        
        System.out.println("Comenzando la batalla entre el mago y el monstruo jefe del bosque...");
        System.out.println(controlador.combate(monstruo, mago));
        
    }
}
