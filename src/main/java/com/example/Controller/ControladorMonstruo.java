package com.example.Controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import com.example.Model.Monstruo;

public class ControladorMonstruo {
    
    private Controlador controladorPrincipal;
    
    public ControladorMonstruo(Controlador controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
    }
    
    public void guardar(Monstruo monstruo) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = controladorPrincipal.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(monstruo);
            tx.commit();
            System.out.println("Monstruo guardado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al guardar monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void actualizar(Monstruo monstruo) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = controladorPrincipal.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(monstruo);
            tx.commit();
            System.out.println("Monstruo actualizado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al actualizar monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void eliminar(Monstruo monstruo) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = controladorPrincipal.getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Monstruo monstruoManaged = em.merge(monstruo);
            em.remove(monstruoManaged);
            tx.commit();
            System.out.println("Monstruo eliminado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al eliminar monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Monstruo obtener(int id) {
        EntityManager em = null;
        try {
            em = controladorPrincipal.getEntityManager();
            Monstruo monstruo = em.find(Monstruo.class, id);
            if (monstruo != null) {
                System.out.println("Monstruo encontrado: " + monstruo.getNombre());
            } else {
                System.out.println("No se encontro ningun monstruo con el ID proporcionado.");
            }
            return monstruo;
        } catch(Exception e) {
            System.err.println("Error al obtener monstruo: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
