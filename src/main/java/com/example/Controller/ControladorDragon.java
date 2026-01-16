package com.example.controller;

import com.example.model.Dragon;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

/**
 * Controlador para operaciones CRUD de Dragon
 */
public class ControladorDragon {
    
    private Controlador controladorPrincipal;
    
    public ControladorDragon(Controlador controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }
    
    /**
     * Guarda un dragon en la base de datos
     * @param dragon el dragon a guardar
     */
    public void guardar(Dragon dragon) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = controladorPrincipal.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(dragon);
            tx.commit();
            System.out.println("Dragon guardado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al guardar dragon: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Actualiza un dragon en la base de datos
     * @param dragon el dragon a actualizar
     */
    public void actualizar(Dragon dragon) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = controladorPrincipal.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(dragon);
            tx.commit();
            System.out.println("Dragon actualizado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al actualizar dragon: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Elimina un dragon de la base de datos
     * @param dragon el dragon a eliminar
     */
    public void eliminar(Dragon dragon) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = controladorPrincipal.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Dragon dragonManaged = em.merge(dragon);
            em.remove(dragonManaged);
            tx.commit();
            System.out.println("Dragon eliminado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al eliminar dragon: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Obtiene un dragon por su ID
     * @param id el identificador del dragon
     * @return el dragon encontrado o null
     */
    public Dragon obtener(int id) {
        EntityManager em = null;
        try {
            em = controladorPrincipal.getEntityManager();
            Dragon dragon = em.find(Dragon.class, id);
            if (dragon != null) {
                System.out.println("Dragon encontrado: " + dragon.getNombre());
            } else {
                System.out.println("No se encontro ningun dragon con el ID proporcionado.");
            }
            return dragon;
        } catch(Exception e) {
            System.err.println("Error al obtener dragon: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
