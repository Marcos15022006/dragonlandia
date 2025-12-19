package com.example.Controller;

import com.example.Model.Hechizo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ControladorHechizo {
    
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

    public void guardarHechizo(Hechizo hechizo) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(hechizo);
            em.getTransaction().commit();
            System.out.println("Hechizo guardado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al guardar hechizo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void actualizarHechizo(Hechizo hechizo) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.merge(hechizo);
            em.getTransaction().commit();
            System.out.println("Hechizo actualizado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al actualizar hechizo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminarHechizo(Hechizo hechizo) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Hechizo hechizoToRemove = em.find(Hechizo.class, hechizo.getId());
            if (hechizoToRemove != null) {
                em.remove(hechizoToRemove);
            }
            em.getTransaction().commit();
            System.out.println("Hechizo eliminado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar hechizo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Hechizo obtenerHechizo(int id) {
        EntityManager em = null;
        Hechizo hechizo = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            hechizo = em.find(Hechizo.class, id);
            if (hechizo != null) {
                System.out.println("Hechizo encontrado: " + hechizo.getNombre());
            } else {
                System.out.println("No se encontró ningún hechizo con el ID proporcionado.");
            }
        } catch (Exception e) {
            System.err.println("Error al obtener hechizo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return hechizo;
    }
}
