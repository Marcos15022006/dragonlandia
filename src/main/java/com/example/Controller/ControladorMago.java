package com.example.controller;

import com.example.model.Mago;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/**
 * Controlador para operaciones CRUD de Mago
 */
public class ControladorMago {
    
    private Controlador controladorPrincipal;
    
    public ControladorMago(Controlador controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }
    
    /**
     * Guarda un mago en la base de datos
     * @param mago el mago a guardar
     */
    public void guardar(Mago mago) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = controladorPrincipal.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(mago);
            tx.commit();
            System.out.println("Mago guardado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al guardar mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Actualiza un mago en la base de datos
     * @param mago el mago a actualizar
     */
    public void actualizar(Mago mago) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = controladorPrincipal.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(mago);
            tx.commit();
            System.out.println("Mago actualizado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al actualizar mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Elimina un mago de la base de datos
     * @param mago el mago a eliminar
     */
    public void eliminar(Mago mago) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = controladorPrincipal.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Mago magoManaged = em.merge(mago);
            em.remove(magoManaged);
            tx.commit();
            System.out.println("Mago eliminado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al eliminar mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Obtiene un mago por su ID
     * @param id el identificador del mago
     * @return el mago encontrado o null
     */
    public Mago obtener(int id) {
        EntityManager em = null;
        try {
            em = controladorPrincipal.getEntityManager();
            Mago mago = em.find(Mago.class, id);
            if (mago != null) {
                System.out.println("Mago encontrado: " + mago.getNombre());
            } else {
                System.out.println("No se encontro ningun mago con el ID proporcionado.");
            }
            return mago;
        } catch(Exception e) {
            System.err.println("Error al obtener mago: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
