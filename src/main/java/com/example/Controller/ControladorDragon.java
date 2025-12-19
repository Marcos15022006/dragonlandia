package com.example.Controller;

import com.example.Model.Dragon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ControladorDragon {
    
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

    public void guardarDragon(Dragon dragon) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(dragon);
            em.getTransaction().commit();
            System.out.println("Dragón guardado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al guardar dragón: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void actualizarDragon(Dragon dragon) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.merge(dragon);
            em.getTransaction().commit();
            System.out.println("Dragón actualizado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al actualizar dragón: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminarDragon(Dragon dragon) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Dragon dragonToRemove = em.find(Dragon.class, dragon.getId());
            if (dragonToRemove != null) {
                em.remove(dragonToRemove);
            }
            em.getTransaction().commit();
            System.out.println("Dragón eliminado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar dragón: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Dragon obtenerDragon(int id) {
        EntityManager em = null;
        Dragon dragon = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            dragon = em.find(Dragon.class, id);
            if (dragon != null) {
                System.out.println("Dragón encontrado: " + dragon.getNombre());
            } else {
                System.out.println("No se encontró ningún dragón con el ID proporcionado.");
            }
        } catch (Exception e) {
            System.err.println("Error al obtener dragón: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return dragon;
    }
}
