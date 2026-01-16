package com.example.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.controller.Controlador;
import com.example.model.Bosque;
import com.example.model.Dragon;
import com.example.model.Hechizo;
import com.example.model.Mago;
import com.example.model.Monstruo;
import com.example.model.Tipo;

/**
 * Interfaz de usuario para el juego de Dragonlandia
 */
public class Interfaz {

    static Scanner scanner = new Scanner(System.in);
    static Controlador controlador = Controlador.getInstance();
    static List<Mago> magosVivos = new ArrayList<>();
    static List<Mago> magosMuertos = new ArrayList<>();
    static List<Monstruo> monstruosVivos = new ArrayList<>();
    static List<Monstruo> monstruosMuertos = new ArrayList<>();
    static Dragon dragon;
    static Bosque bosque;

    /**
     * Inicia el juego y configura los elementos iniciales
     */
    public void iniciar() {
        System.out.println("===========================================");
        System.out.println("   BIENVENIDO A DRAGONLANDIA");
        System.out.println("===========================================");
        System.out.println();

        crearBosque();

        System.out.println("\n--- CREACION DE MONSTRUOS ---");
        System.out.println("Se deben crear al menos 3 monstruos");
        for (int i = 0; i < 3; i++) {
            System.out.println("\nCreando monstruo " + (i + 1) + ":");
            crearMonstruo();
        }

        String respuesta = "S";
        while (respuesta.equals("S")) {
            System.out.println("\nDesea crear otro monstruo? (S/N)");
            respuesta = scanner.nextLine().toUpperCase();
            while (!respuesta.equals("S") && !respuesta.equals("N")) {
                System.out.println("Respuesta invalida. Vuelve a intentarlo:");
                respuesta = scanner.nextLine().toUpperCase();
            }

            if (respuesta.equals("S")) {
                crearMonstruo();
            }
        }

        asignarMonstruoJefe();

        crearDragon();

        System.out.println("\n--- CREACION DE MAGOS ---");
        System.out.println("Se deben crear al menos 2 magos con minimo 2 hechizos cada uno");
        for (int i = 0; i < 2; i++) {
            System.out.println("\nCreando mago " + (i + 1) + ":");
            crearMago();
        }

        respuesta = "S";
        while (respuesta.equals("S")) {
            System.out.println("\nDesea crear otro mago? (S/N)");
            respuesta = scanner.nextLine().toUpperCase();
            while (!respuesta.equals("S") && !respuesta.equals("N")) {
                System.out.println("Respuesta invalida. Vuelve a intentarlo:");
                respuesta = scanner.nextLine().toUpperCase();
            }

            if (respuesta.equals("S")) {
                crearMago();
            }
        }

        System.out.println("\n===========================================");
        System.out.println("   COMIENZA LA BATALLA");
        System.out.println("===========================================");
        iniciarJuego();
    }

    /**
     * Crea el bosque del juego
     */
    private void crearBosque() {
        System.out.println("\n--- CREACION DEL BOSQUE ---");
        System.out.print("Nombre del bosque: ");
        String nombreBosque = scanner.nextLine();
        System.out.print("Nivel de peligro del bosque: ");
        int nivelPeligroBosque = scanner.nextInt();
        scanner.nextLine();

        bosque = new Bosque();
        bosque.setNombre(nombreBosque);
        bosque.setNivelPeligro(nivelPeligroBosque);
        controlador.guardarBosque(bosque);
        System.out
                .println("Bosque '" + bosque.getNombre() + "' creado con nivel de peligro " + bosque.getnivelPeligro());
    }

    /**
     * Crea un nuevo monstruo
     */
    private void crearMonstruo() {
        System.out.print("Nombre del monstruo: ");
        String nombreMonstruo = scanner.nextLine();
        System.out.print("Vida del monstruo: ");
        int vidaMonstruo = scanner.nextInt();
        System.out.print("Fuerza del monstruo: ");
        int fuerzaMonstruo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Tipo del monstruo (SPECTRO, TROLL, OGRO): ");
        String tipoMonstruo = scanner.nextLine().toUpperCase();
        while (!tipoMonstruo.equals("SPECTRO") && !tipoMonstruo.equals("TROLL") && !tipoMonstruo.equals("OGRO")) {
            System.out.println("Tipo de monstruo invalido. Vuelve a intentarlo:");
            tipoMonstruo = scanner.nextLine().toUpperCase();
        }

        Monstruo monstruo = new Monstruo();
        monstruo.setNombre(nombreMonstruo);
        monstruo.setVida(vidaMonstruo);
        monstruo.setFuerza(fuerzaMonstruo);
        monstruo.setTipo(Tipo.valueOf(tipoMonstruo));
        monstruo.setBosque(bosque);

        controlador.guardarMonstruo(monstruo);
        bosque.addMonstruo(monstruo);
        controlador.actualizarBosque(bosque);

        monstruosVivos.add(monstruo);
        System.out.println("Monstruo '" + monstruo.getNombre() + "' creado exitosamente");
    }

    /**
     * Asigna un monstruo como jefe del bosque
     */
    private void asignarMonstruoJefe() {
        System.out.println("\n--- ASIGNACION DE MONSTRUO JEFE ---");
        System.out.println("Monstruos disponibles:");
        for (int i = 0; i < monstruosVivos.size(); i++) {
            Monstruo m = monstruosVivos.get(i);
            System.out.println((i + 1) + ". " + m.getNombre() + " - Tipo: " + m.geTipo() + ", Vida: " + m.getVida()
                    + ", Fuerza: " + m.getFuerza());
        }
        System.out.print("Seleccione el numero del monstruo jefe: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine();

        while (seleccion < 1 || seleccion > monstruosVivos.size()) {
            System.out.println("Seleccion invalida. Intente de nuevo:");
            seleccion = scanner.nextInt();
            scanner.nextLine();
        }

        Monstruo jefe = monstruosVivos.get(seleccion - 1);
        bosque.setMonstruoJefe(jefe);
        controlador.actualizarBosque(bosque);
        System.out.println("'" + jefe.getNombre() + "' ha sido asignado como monstruo jefe del bosque");
    }

    /**
     * Crea el dragon del juego
     */
    private void crearDragon() {
        System.out.println("\n--- CREACION DEL DRAGON ---");
        System.out.print("Nombre del dragon: ");
        String nombreDragon = scanner.nextLine();
        System.out.print("Resistencia del dragon: ");
        int resistenciaDragon = scanner.nextInt();
        System.out.print("Intensidad de fuego del dragon: ");
        int intensidadFuego = scanner.nextInt();
        scanner.nextLine();

        dragon = new Dragon();
        dragon.setNombre(nombreDragon);
        dragon.setResistencia(resistenciaDragon);
        dragon.setIntensidadFuego(intensidadFuego);
        dragon.setBosque(bosque);

        controlador.guardarDragon(dragon);
        bosque.addDragon(dragon);
        controlador.actualizarBosque(bosque);
        System.out.println("Dragon '" + dragon.getNombre() + "' creado exitosamente");
    }

    /**
     * Crea un nuevo mago
     */
    private void crearMago() {
        System.out.print("Nombre del mago: ");
        String nombreMago = scanner.nextLine();
        System.out.print("Vida del mago: ");
        int vidaMago = scanner.nextInt();
        System.out.print("Nivel de magia del mago: ");
        int nivelMagiaMago = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Cuantos hechizos conoce el mago? (minimo 2)");
        int numHechizos = scanner.nextInt();
        scanner.nextLine();
        while (numHechizos < 2) {
            System.out.println("Debe conocer al menos 2 hechizos. Intente de nuevo:");
            numHechizos = scanner.nextInt();
            scanner.nextLine();
        }

        List<Hechizo> hechizosConocidos = new ArrayList<>();
        for (int i = 0; i < numHechizos; i++) {
            System.out.print("Nombre del hechizo " + (i + 1) + " (BOLA_DE_FUEGO, BOLA_DE_NIEVE, RAYO, PUTREFACCION): ");
            String nombreHechizo = scanner.nextLine().toUpperCase();
            while (!nombreHechizo.equals("BOLA_DE_FUEGO") && !nombreHechizo.equals("BOLA_DE_NIEVE") &&
                    !nombreHechizo.equals("RAYO") && !nombreHechizo.equals("PUTREFACCION")) {
                System.out.println("Hechizo invalido. Vuelve a intentarlo:");
                nombreHechizo = scanner.nextLine().toUpperCase();
            }
            hechizosConocidos.add(Hechizo.valueOf(nombreHechizo));
        }

        Mago mago = new Mago();
        mago.setNombre(nombreMago);
        mago.setVida(vidaMago);
        mago.setNivelMagia(nivelMagiaMago);
        mago.setConjuro(hechizosConocidos);

        controlador.guardarMago(mago);
        magosVivos.add(mago);
        System.out.println("Mago '" + mago.getNombre() + "' creado exitosamente");
    }

    /**
     * Inicia el bucle principal del juego
     */
    private void iniciarJuego() {
        int ronda = 1;
        boolean juegoActivo = true;

        while (juegoActivo && !magosVivos.isEmpty() && !monstruosVivos.isEmpty()) {
            System.out.println("\n===========================================");
            System.out.println("   RONDA " + ronda);
            System.out.println("===========================================");

            turnoMagos();

            if (!monstruosVivos.isEmpty()) {
                turnoMonstruos();
            }

            if (!magosVivos.isEmpty() && !monstruosVivos.isEmpty()) {
                turnoDragon();
            }

            mostrarEstado();

            ronda++;
        }

        mostrarResultadoFinal();
    }

    /**
     * Ejecuta el turno de los magos
     */
    private void turnoMagos() {
        System.out.println("\n--- TURNO DE LOS MAGOS ---");

        Hechizo[] hechizosDisponibles = Hechizo.values();

        for (Mago mago : new ArrayList<>(magosVivos)) {
            if (mago.getVida() > 0) {

                System.out.println("\nTurno de " + mago.getNombre() + " (Vida: " + mago.getVida() + ")");
                System.out.println("Hechizos conocidos: " + mago.getConjuro());
                System.out.println("\nHechizos disponibles en el juego:");
                for (int i = 0; i < hechizosDisponibles.length; i++) {
                    System.out.println((i + 1) + ". " + hechizosDisponibles[i]);
                }

                // Seleccionar monstruo objetivo
                if (!monstruosVivos.isEmpty()) {

                    System.out.print("Seleccione el numero del hechizo a lanzar: ");
                    int seleccion = scanner.nextInt();
                    scanner.nextLine();

                    while (seleccion < 1 || seleccion > hechizosDisponibles.length) {
                        System.out.println("Seleccion invalida. Intente de nuevo:");
                        seleccion = scanner.nextInt();
                        scanner.nextLine();
                    }

                    Hechizo hechizoSeleccionado = hechizosDisponibles[seleccion - 1];

                    System.out.println("\nMonstruos disponibles:");
                    for (int i = 0; i < monstruosVivos.size(); i++) {
                        Monstruo m = monstruosVivos.get(i);
                        System.out.println((i + 1) + ". " + m.getNombre() + " (Vida: " + m.getVida() + ")");
                    }
                    System.out.print("Seleccione el numero del monstruo a atacar: ");
                    int seleccionMonstruo = scanner.nextInt();
                    scanner.nextLine();

                    while (seleccionMonstruo < 1 || seleccionMonstruo > monstruosVivos.size()) {
                        System.out.println("Seleccion invalida. Intente de nuevo:");
                        seleccionMonstruo = scanner.nextInt();
                        scanner.nextLine();
                    }

                    Monstruo monstruoObjetivo = monstruosVivos.get(seleccionMonstruo - 1);

                    // Lanzar hechizo
                    mago.lanzarHechizo(monstruoObjetivo, hechizoSeleccionado);

                    // Verificar si el mago murio por usar un hechizo desconocido
                    if (mago.getVida() <= 0) {
                        System.out.println("El mago " + mago.getNombre() + " ha muerto!");
                        magosVivos.remove(mago);
                        magosMuertos.add(mago);
                        controlador.eliminarMago(mago);
                    } else {
                        controlador.actualizarMago(mago);
                    }

                    // Verificar si el monstruo murio
                    if (monstruoObjetivo.getVida() <= 0) {
                        System.out.println("El monstruo " + monstruoObjetivo.getNombre() + " ha sido derrotado!");
                        monstruosVivos.remove(monstruoObjetivo);
                        monstruosMuertos.add(monstruoObjetivo);
                        bosque.getListaMontruos().remove(monstruoObjetivo);
                        controlador.eliminarMonstruo(monstruoObjetivo);

                        // Si era el jefe, asignar nuevo jefe
                        if (bosque.getMonstruoJefe() != null
                                && bosque.getMonstruoJefe().getId() == monstruoObjetivo.getId()) {
                            asignarNuevoJefe();
                        }
                    } else {
                        controlador.actualizarMonstruo(monstruoObjetivo);
                    }
                }
            }
        }
    }

    /**
     * Ejecuta el turno de los monstruos
     */
    private void turnoMonstruos() {
        System.out.println("\n--- TURNO DE LOS MONSTRUOS ---");

        for (Monstruo monstruo : new ArrayList<>(monstruosVivos)) {
            if (!magosVivos.isEmpty()) {
                // Seleccionar un mago aleatorio para atacar
                int indiceAleatorio = (int) (Math.random() * magosVivos.size());
                Mago magoObjetivo = magosVivos.get(indiceAleatorio);

                System.out.println(monstruo.getNombre() + " ataca a " + magoObjetivo.getNombre());
                monstruo.atacar(magoObjetivo);
                System.out.println("Vida restante de " + magoObjetivo.getNombre() + ": " + magoObjetivo.getVida());

                // Verificar si el mago murio
                if (magoObjetivo.getVida() <= 0) {
                    System.out.println("El mago " + magoObjetivo.getNombre() + " ha muerto!");
                    magosVivos.remove(magoObjetivo);
                    magosMuertos.add(magoObjetivo);
                    controlador.eliminarMago(magoObjetivo);
                } else {
                    controlador.actualizarMago(magoObjetivo);
                }
            }
        }
    }

    /**
     * Ejecuta el turno del dragon
     */
    private void turnoDragon() {
        System.out.println("\n--- TURNO DEL DRAGON ---");

        if (dragon.getResistencia() <= 0) {
            System.out.println("El dragon ha muerto y no puede atacar");
        } else {

            Monstruo jefe = bosque.getMonstruoJefe();
            if (jefe == null) {
                System.out.println("No hay monstruo jefe para atacar");

            } else {

                System.out.println(dragon.getNombre() + " ataca al monstruo jefe " + jefe.getNombre());
                dragon.exhalar(jefe);
                System.out.println("Vida restante de " + jefe.getNombre() + ": " + jefe.getVida());

                // Verificar si el jefe murio
                if (jefe.getVida() <= 0) {
                    System.out.println("El monstruo jefe " + jefe.getNombre() + " ha sido derrotado por el dragon!");
                    monstruosVivos.remove(jefe);
                    monstruosMuertos.add(jefe);
                    bosque.getListaMontruos().remove(jefe);
                    controlador.eliminarMonstruo(jefe);

                    asignarNuevoJefe();
                } else {
                    controlador.actualizarMonstruo(jefe);
                }

                // El jefe contraataca al dragon
                if (jefe.getVida() > 0) {
                    System.out.println("El monstruo jefe " + jefe.getNombre() + " contraataca al dragon");
                    int nuevaResistencia = dragon.getResistencia() - jefe.getFuerza();
                    dragon.setResistencia(nuevaResistencia);
                    System.out.println("Resistencia restante del dragon: " + dragon.getResistencia());

                    if (dragon.getResistencia() <= 0) {
                        System.out.println("El dragon " + dragon.getNombre() + " ha muerto!");
                        controlador.eliminarDragon(dragon);
                    } else {
                        controlador.actualizarDragon(dragon);
                    }
                }
            }
        }
    }

    /**
     * Asigna un nuevo jefe cuando el actual muere
     */
    private void asignarNuevoJefe() {
        if (monstruosVivos.isEmpty()) {
            System.out.println("No quedan monstruos vivos para asignar como jefe");
            bosque.setMonstruoJefe(null);
            controlador.actualizarBosque(bosque);
        } else {

            // Asignar el primer monstruo vivo como nuevo jefe
            Monstruo nuevoJefe = monstruosVivos.get(0);
            bosque.setMonstruoJefe(nuevoJefe);
            controlador.actualizarBosque(bosque);
            System.out.println("Nuevo monstruo jefe asignado: " + nuevoJefe.getNombre());
        }
    }

    /**
     * Muestra el estado actual del juego
     */
    private void mostrarEstado() {
        System.out.println("\n===========================================");
        System.out.println("   ESTADO ACTUAL DEL JUEGO");
        System.out.println("===========================================");

        System.out.println("\nMONSTRUO JEFE DEL BOSQUE:");
        if (bosque.getMonstruoJefe() != null) {
            Monstruo jefe = bosque.getMonstruoJefe();
            System.out.println("- " + jefe.getNombre() + " (Tipo: " + jefe.geTipo() + ", Vida: " + jefe.getVida()
                    + ", Fuerza: " + jefe.getFuerza() + ")");
        } else {
            System.out.println("- No hay monstruo jefe");
        }

        System.out.println("\nMAGOS VIVOS:");
        if (magosVivos.isEmpty()) {
            System.out.println("- No quedan magos vivos");
        } else {
            for (Mago mago : magosVivos) {
                System.out.println("- " + mago.getNombre() + " (Vida: " + mago.getVida() + ", Nivel de magia: "
                        + mago.getNivelMagia() + ")");
            }
        }

        System.out.println("\nMAGOS MUERTOS:");
        if (magosMuertos.isEmpty()) {
            System.out.println("- Ninguno");
        } else {
            for (Mago mago : magosMuertos) {
                System.out.println("- " + mago.getNombre());
            }
        }

        System.out.println("\nMONSTRUOS VIVOS:");
        if (monstruosVivos.isEmpty()) {
            System.out.println("- No quedan monstruos vivos");
        } else {
            for (Monstruo monstruo : monstruosVivos) {
                System.out.println("- " + monstruo.getNombre() + " (Tipo: " + monstruo.geTipo() + ", Vida: "
                        + monstruo.getVida() + ", Fuerza: " + monstruo.getFuerza() + ")");
            }
        }

        System.out.println("\nMONSTRUOS MUERTOS:");
        if (monstruosMuertos.isEmpty()) {
            System.out.println("- Ninguno");
        } else {
            for (Monstruo monstruo : monstruosMuertos) {
                System.out.println("- " + monstruo.getNombre());
            }
        }

        System.out.println("\nDRAGON:");
        if (dragon.getResistencia() > 0) {
            System.out.println("- " + dragon.getNombre() + " (Resistencia: " + dragon.getResistencia()
                    + ", Intensidad de fuego: " + dragon.getIntensidadFuego() + ")");
        } else {
            System.out.println("- " + dragon.getNombre() + " (MUERTO)");
        }
    }

    /**
     * Muestra el resultado final del juego
     */
    private void mostrarResultadoFinal() {
        System.out.println("\n===========================================");
        System.out.println("   FIN DEL JUEGO");
        System.out.println("===========================================");

        if (monstruosVivos.isEmpty()) {
            System.out.println("\nLOS MAGOS HAN GANADO!");
            System.out.println("Todos los monstruos del bosque han sido derrotados");
        } else if (magosVivos.isEmpty()) {
            System.out.println("\nLOS MONSTRUOS HAN GANADO!");
            System.out.println("Todos los magos han sido derrotados");
        }

        mostrarEstado();

        System.out.println("\n===========================================");
        System.out.println("   GRACIAS POR JUGAR");
        System.out.println("===========================================");
    }
}
