package com.example.Controller;

import com.example.Model.Monstruo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ControladorMonstruo {
    
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

    public void guardarMonstruo(Monstruo monstruo) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(monstruo);
            em.getTransaction().commit();
            System.out.println("Monstruo guardado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al guardar monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void actualizarMonstruo(Monstruo monstruo) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.merge(monstruo);
            em.getTransaction().commit();
            System.out.println("Monstruo actualizado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al actualizar monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminarMonstruo(Monstruo monstruo) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Monstruo monstruoToRemove = em.find(Monstruo.class, monstruo.getId());
            if (monstruoToRemove != null) {
                em.remove(monstruoToRemove);
            }
            em.getTransaction().commit();
            System.out.println("Monstruo eliminado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Monstruo obtenerMonstruo(int id) {
        EntityManager em = null;
        Monstruo monstruo = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            monstruo = em.find(Monstruo.class, id);
            if (monstruo != null) {
                System.out.println("Monstruo encontrado: " + monstruo.getNombre());
            } else {
                System.out.println("No se encontró ningún monstruo con el ID proporcionado.");
            }
        } catch (Exception e) {
            System.err.println("Error al obtener monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return monstruo;
    }
}
