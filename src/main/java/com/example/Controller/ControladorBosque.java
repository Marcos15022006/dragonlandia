package com.example.Controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import com.example.Model.Bosque;

public class ControladorBosque {
    
    private Controlador controladorPrincipal;
    
    public ControladorBosque(Controlador controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }
    
    public void guardar(Bosque bosque) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = controladorPrincipal.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(bosque);
            tx.commit();
            System.out.println("Bosque guardado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al guardar bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void actualizar(Bosque bosque) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = controladorPrincipal.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(bosque);
            tx.commit();
            System.out.println("Bosque actualizado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al actualizar bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void eliminar(Bosque bosque) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = controladorPrincipal.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Bosque bosqueManaged = em.merge(bosque);
            em.remove(bosqueManaged);
            tx.commit();
            System.out.println("Bosque eliminado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al eliminar bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Bosque obtener(int id) {
        EntityManager em = null;
        try {
            em = controladorPrincipal.getEntityManager();
            Bosque bosque = em.find(Bosque.class, id);
            if (bosque != null) {
                System.out.println("Bosque encontrado: " + bosque.getNombre());
            } else {
                System.out.println("No se encontro ningun bosque con el ID proporcionado.");
            }
            return bosque;
        } catch(Exception e) {
            System.err.println("Error al obtener bosque: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
