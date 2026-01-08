package com.example.Controller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import com.example.Model.Bosque;
import com.example.Model.Dragon;
import com.example.Model.Hechizo;
import com.example.Model.Mago;
import com.example.Model.Monstruo;

public class Controlador {


    private static Controlador instance;
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dragolandiaServizo");

    
    private Controlador() {
    }

    public static Controlador getInstance() {
        if (instance == null) {
            instance = new Controlador();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public EntityTransaction getTransaction(){
        return getEntityManager().getTransaction();
    }

    
    
    public static void cerrarEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
    
    // Método de compatibilidad
    public static void cerrarSessionFactory() {
        cerrarEntityManagerFactory();
    }

    public void guardarMago(Mago mago){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
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

    public void actualizarMago(Mago mago){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
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

    public void eliminarMago(Mago mago){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
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

    public void obtenerMago(int id){
        EntityManager em = null;
        try {
            em = getEntityManager();
            Mago mago = em.find(Mago.class, id);
            if (mago != null) {
                System.out.println("Mago encontrado: " + mago.getNombre());
            } else {
                System.out.println("No se encontró ningún mago con el ID proporcionado.");
            }
        } catch(Exception e) {
            System.err.println("Error al obtener mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }






    public void guardarBosque(Bosque bosque){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
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

    public void actualizarBosque(Bosque bosque){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
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

    public void eliminarBosque(Bosque bosque){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Bosque bosqueManaged = em.merge(bosque);
            em.remove(bosqueManaged);
            tx.commit();
            System.out.println("Bosque eliminada correctamente en la base de datos");
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

    public void obtenerBosque(int id){
        EntityManager em = null;
        try {
            em = getEntityManager();
            Bosque bosque = em.find(Bosque.class, id);
            if (bosque != null) {
                System.out.println("Bosque encontrado: " + bosque.getNombre());
            } else {
                System.out.println("No se encontró ningún bosque con el ID proporcionado.");
            }
        } catch(Exception e) {
            System.err.println("Error al obtener bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }



    public void guardarMonstruo(Monstruo monstruo){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
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


    public void actualizarMonstruo(Monstruo monstruo){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
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

    public void eliminarMonstruo(Monstruo monstruo){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
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

    public void obtenerMonstruo(int id){
        EntityManager em = null;
        try {
            em = getEntityManager();
            Monstruo monstruo = em.find(Monstruo.class, id);
            if (monstruo != null) {
                System.out.println("Monstruo encontrado: " + monstruo.getNombre());
            } else {
                System.out.println("No se encontró ningún monstruo con el ID proporcionado.");
            }
        } catch(Exception e) {
            System.err.println("Error al obtener monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }





    public void guardarDragon(Dragon dragon){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(dragon);
            tx.commit();
            System.out.println("Dragón guardado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al guardar dragón: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void actualizarDragon(Dragon dragon){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.merge(dragon);
            tx.commit();
            System.out.println("Dragón actualizado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al actualizar dragón: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminarDragon(Dragon dragon){
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = getEntityManager();
            tx = em.getTransaction();
            tx.begin();
            Dragon dragonManaged = em.merge(dragon);
            em.remove(dragonManaged);
            tx.commit();
            System.out.println("Dragón eliminado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            System.err.println("Error al eliminar dragón: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void obtenerDragon(int id){
        EntityManager em = null;
        try {
            em = getEntityManager();
            Dragon dragon = em.find(Dragon.class, id);
            if (dragon != null) {
                System.out.println("Dragón encontrado: " + dragon.getNombre());
            } else {
                System.out.println("No se encontró ningún dragón con el ID proporcionado.");
            }
        } catch(Exception e) {
            System.err.println("Error al obtener dragón: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
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
    

 public void combate(Monstruo monstruo, Mago mago, Hechizo hechizo){
             mago.lanzarHechizo(monstruo, hechizo);
             if (monstruo.getVida() >0){
                 System.out.println("El monstruo devuelve el ataque");
                 monstruo.atacar(mago);
                 System.out.println("Vida restante del mago "+mago.getNombre()+": "+mago.getVida());
             }
        
         if (monstruo.getVida() <=0){
             System.out.println("El mago "+mago.getNombre()+" ha ganado el combate.");
             eliminarMonstruo(monstruo);
             actualizarMago(mago);
         } else if (mago.getVida() <=0) {
             System.out.println("El monstruo "+monstruo.getNombre()+" ha ganado el combate.");
             eliminarMago(mago);
             actualizarMonstruo(monstruo);
         }
     }
}