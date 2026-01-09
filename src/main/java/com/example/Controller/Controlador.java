package com.example.Controller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import com.example.Model.Bosque;
import com.example.Model.Dragon;
import com.example.Model.Mago;
import com.example.Model.Monstruo;

public class Controlador {

    private static Controlador instance;
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dragolandiaServizo");

    private ControladorMago controladorMago;
    private ControladorMonstruo controladorMonstruo;
    private ControladorDragon controladorDragon;
    private ControladorBosque controladorBosque;
    
    private Controlador() {
        this.controladorMago = new ControladorMago(this);
        this.controladorMonstruo = new ControladorMonstruo(this);
        this.controladorDragon = new ControladorDragon(this);
        this.controladorBosque = new ControladorBosque(this);
    }

    public static Controlador getInstance() {
        if (instance == null) {
            instance = new Controlador();
        }
        return instance;
    }
    
    public ControladorMago getControladorMago() {
        return controladorMago;
    }
    
    public ControladorMonstruo getControladorMonstruo() {
        return controladorMonstruo;
    }
    
    public ControladorDragon getControladorDragon() {
        return controladorDragon;
    }
    
    public ControladorBosque getControladorBosque() {
        return controladorBosque;
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public EntityTransaction getTransaction(){
        return getEntityManager().getTransaction();
    }

    
    
    public static void cerrarEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
    
    // Método de compatibilidad
    public static void cerrarSessionFactory() {
        cerrarEntityManagerFactory();
    }

// Metodos delegados para Mago
    public void guardarMago(Mago mago){
        controladorMago.guardar(mago);
    }

    public void actualizarMago(Mago mago){
        controladorMago.actualizar(mago);
    }

    public void eliminarMago(Mago mago){
        controladorMago.eliminar(mago);
    }

    public void obtenerMago(int id){
        controladorMago.obtener(id);
    }

    // Metodos delegados para Bosque
    public void guardarBosque(Bosque bosque){
        controladorBosque.guardar(bosque);
    }

    public void actualizarBosque(Bosque bosque){
        controladorBosque.actualizar(bosque);
    }

    public void eliminarBosque(Bosque bosque){
        controladorBosque.eliminar(bosque);
    }

    public void obtenerBosque(int id){
        controladorBosque.obtener(id);
    }

    // Metodos delegados para Monstruo
    public void guardarMonstruo(Monstruo monstruo){
        controladorMonstruo.guardar(monstruo);
    }

    public void actualizarMonstruo(Monstruo monstruo){
        controladorMonstruo.actualizar(monstruo);
    }

    public void eliminarMonstruo(Monstruo monstruo){
        controladorMonstruo.eliminar(monstruo);
    }

    public void obtenerMonstruo(int id){
        controladorMonstruo.obtener(id);
    }

    // Metodos delegados para Dragon
    public void guardarDragon(Dragon dragon){
        controladorDragon.guardar(dragon);
    }

    public void actualizarDragon(Dragon dragon){
        controladorDragon.actualizar(dragon);
    }

    public void eliminarDragon(Dragon dragon){
        controladorDragon.eliminar(dragon);
    }

    public void obtenerDragon(int id){
        controladorDragon.obtener(id);
    }
    
    
    
    /* public String combate(Monstruo monstruo, Mago mago, Hechizo hechizo){
        while (monstruo.getVida() >0 && mago.getVida()>0){
            mago.lanzarHechizo(monstruo, hechizo);
            if (monstruo.getVida() >0){
                monstruo.atacar(mago);
            }
        }
        String resultado = "";
        if (monstruo.getVida() <=0){
            resultado="El mago "+mago.getNombre()+" ha ganado el combate.";
            eliminarMonstruo(monstruo);
            actualizarMago(mago);
        } else if( mago.getVida() <=0) {
            resultado="El monstruo "+monstruo.getNombre()+" ha ganado el combate.";
            eliminarMago(mago);
            actualizarMonstruo(monstruo);
        }
        return resultado;
    }
} */
}
