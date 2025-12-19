package com.example.Controller;

import com.example.Model.Mago;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ControladorMago {
    
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

    public void guardarMago(Mago mago) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.persist(mago);
            em.getTransaction().commit();
            System.out.println("Mago guardado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al guardar mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void actualizarMago(Mago mago) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            em.merge(mago);
            em.getTransaction().commit();
            System.out.println("Mago actualizado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al actualizar mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminarMago(Mago mago) {
        EntityManager em = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Mago magoToRemove = em.find(Mago.class, mago.getId());
            if (magoToRemove != null) {
                em.remove(magoToRemove);
            }
            em.getTransaction().commit();
            System.out.println("Mago eliminado correctamente en la base de datos");
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Mago obtenerMago(int id) {
        EntityManager em = null;
        Mago mago = null;
        try {
            em = getEntityManagerFactory().createEntityManager();
            mago = em.find(Mago.class, id);
            if (mago != null) {
                System.out.println("Mago encontrado: " + mago.getNombre());
            } else {
                System.out.println("No se encontró ningún mago con el ID proporcionado.");
            }
        } catch (Exception e) {
            System.err.println("Error al obtener mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return mago;
    }
}
