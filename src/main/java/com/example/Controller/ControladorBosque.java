package com.example.Controller;

import com.example.Model.Bosque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ControladorBosque {
    
    private static EntityManagerFactory entityManagerFactory;

    private static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            try {
                entityManagerFactory = Persistence.createEntityManagerFactory("dragonlandia2");
            } catch (Exception e) {
                System.err.println("Error al crear EntityManagerFactory: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return entityManagerFactory;
    }

    public static void cerrarEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    public void guardarBosque(Bosque bosque) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(bosque);
            em.getTransaction().commit();
            System.out.println("Bosque guardado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al guardar bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void actualizarBosque(Bosque bosque) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.merge(bosque);
            em.getTransaction().commit();
            System.out.println("Bosque actualizado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al actualizar bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminarBosque(Bosque bosque) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Bosque bosqueToRemove = em.find(Bosque.class, bosque.getId());
            if (bosqueToRemove != null) {
                em.remove(bosqueToRemove);
            }
            em.getTransaction().commit();
            System.out.println("Bosque eliminado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Bosque obtenerBosque(int id) {
        EntityManager em = null;
        Bosque bosque = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            bosque = em.find(Bosque.class, id);
            if (bosque != null) {
                System.out.println("Bosque encontrado: " + bosque.getNombre());
            } else {
                System.out.println("No se encontró ningún bosque con el ID proporcionado.");
            }
        } catch (Exception e) {
            System.err.println("Error al obtener bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return bosque;
    }
}
