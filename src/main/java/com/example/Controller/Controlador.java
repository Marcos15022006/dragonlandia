package com.example.Controller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.example.Model.Bosque;
import com.example.Model.Dragon;
import com.example.Model.Mago;
import com.example.Model.Monstruo;

/**
 * Controlador principal que gestiona las operaciones de persistencia
 */
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

    /**
     * Obtiene la instancia única del controlador
     * @return instancia del controlador
     */
    public static Controlador getInstance() {
        if (instance == null) {
            instance = new Controlador();
        }
        return instance;
    }
    
    /**
     * Obtiene el controlador de magos
     * @return el controlador de magos
     */
    public ControladorMago getControladorMago() {
        return controladorMago;
    }
    
    /**
     * Obtiene el controlador de monstruos
     * @return el controlador de monstruos
     */
    public ControladorMonstruo getControladorMonstruo() {
        return controladorMonstruo;
    }
    
    /**
     * Obtiene el controlador de dragones
     * @return el controlador de dragones
     */
    public ControladorDragon getControladorDragon() {
        return controladorDragon;
    }
    
    /**
     * Obtiene el controlador de bosques
     * @return el controlador de bosques
     */
    public ControladorBosque getControladorBosque() {
        return controladorBosque;
    }

    /**
     * Crea y retorna un nuevo EntityManager
     * @return EntityManager
     */
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Cierra el EntityManagerFactory
     */
    public static void cerrarEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
    
    /**
     * Cierra la session factory
     */
    public static void cerrarSessionFactory() {
        cerrarEntityManagerFactory();
    }

    /**
     * Guarda un mago en la base de datos
     * @param mago el mago a guardar
     */
    public void guardarMago(Mago mago){
        controladorMago.guardar(mago);
    }

    /**
     * Actualiza un mago en la base de datos
     * @param mago el mago a actualizar
     */
    public void actualizarMago(Mago mago){
        controladorMago.actualizar(mago);
    }

    /**
     * Elimina un mago de la base de datos
     * @param mago el mago a eliminar
     */
    public void eliminarMago(Mago mago){
        controladorMago.eliminar(mago);
    }

    /**
     * Obtiene un mago por su ID
     * @param id el identificador del mago
     */
    public void obtenerMago(int id){
        controladorMago.obtener(id);
    }

    /**
     * Guarda un bosque en la base de datos
     * @param bosque el bosque a guardar
     */
    public void guardarBosque(Bosque bosque){
        controladorBosque.guardar(bosque);
    }

    /**
     * Actualiza un bosque en la base de datos
     * @param bosque el bosque a actualizar
     */
    public void actualizarBosque(Bosque bosque){
        controladorBosque.actualizar(bosque);
    }

    /**
     * Elimina un bosque de la base de datos
     * @param bosque el bosque a eliminar
     */
    public void eliminarBosque(Bosque bosque){
        controladorBosque.eliminar(bosque);
    }

    /**
     * Obtiene un bosque por su ID
     * @param id el identificador del bosque
     */
    public void obtenerBosque(int id){
        controladorBosque.obtener(id);
    }

    /**
     * Guarda un monstruo en la base de datos
     * @param monstruo el monstruo a guardar
     */
    public void guardarMonstruo(Monstruo monstruo){
        controladorMonstruo.guardar(monstruo);
    }

    /**
     * Actualiza un monstruo en la base de datos
     * @param monstruo el monstruo a actualizar
     */
    public void actualizarMonstruo(Monstruo monstruo){
        controladorMonstruo.actualizar(monstruo);
    }

    /**
     * Elimina un monstruo de la base de datos
     * @param monstruo el monstruo a eliminar
     */
    public void eliminarMonstruo(Monstruo monstruo){
        controladorMonstruo.eliminar(monstruo);
    }

    /**
     * Obtiene un monstruo por su ID
     * @param id el identificador del monstruo
     */
    public void obtenerMonstruo(int id){
        controladorMonstruo.obtener(id);
    }

    /**
     * Guarda un dragon en la base de datos
     * @param dragon el dragon a guardar
     */
    public void guardarDragon(Dragon dragon){
        controladorDragon.guardar(dragon);
    }

    /**
     * Actualiza un dragon en la base de datos
     * @param dragon el dragon a actualizar
     */
    public void actualizarDragon(Dragon dragon){
        controladorDragon.actualizar(dragon);
    }

    /**
     * Elimina un dragon de la base de datos
     * @param dragon el dragon a eliminar
     */
    public void eliminarDragon(Dragon dragon){
        controladorDragon.eliminar(dragon);
    }

    /**
     * Obtiene un dragon por su ID
     * @param id el identificador del dragon
     */
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
