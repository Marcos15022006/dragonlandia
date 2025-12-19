package com.example.Controller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.Model.Bosque;
import com.example.Model.Dragon;
import com.example.Model.Hechizo;
import com.example.Model.Mago;
import com.example.Model.Monstruo;

public class Controlador {

    private static SessionFactory sessionFactory;

    
    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Exception e) {
                System.err.println("Error al crear SessionFactory: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    
    public static void cerrarSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    public void guardarMago(Mago mago){
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(mago);
            tx.commit();
            System.out.println("Mago guardado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al guardar mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void actualizarMago(Mago mago){
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.merge(mago);
            tx.commit();
            System.out.println("Mago actualizado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al actualizar mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void eliminarMago(Mago mago){
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.remove(mago);
            tx.commit();
            System.out.println("Mago eliminado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al eliinar mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void obtenerMago(int id){
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Mago mago = session.get(Mago.class, id);
            if (mago != null) {
                System.out.println("Mago encontrado: " + mago.getNombre());
            } else {
                System.out.println("No se encontró ningún mago con el ID proporcionado.");
            }
        } catch(Exception e) {
            System.err.println("Error al obtener mago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }






    public void guardarBosque(Bosque bosque){
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(bosque);
            tx.commit();
            System.out.println("Bosque guardado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al guardar bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void actualizarBosque(Bosque bosque){
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.merge(bosque);
            tx.commit();
            System.out.println("Bosque actualizado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al actualizar bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void eliminarBosque(Bosque bosque){
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.remove(bosque);
            tx.commit();
            System.out.println("Bosque eliminada correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al eliminar bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void obtenerBosque(int id){
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Bosque bosque = session.get(Bosque.class, id);
            if (bosque != null) {
                System.out.println("Bosque encontrado: " + bosque.getNombre());
            } else {
                System.out.println("No se encontró ningún bosque con el ID proporcionado.");
            }
        } catch(Exception e) {
            System.err.println("Error al obtener bosque: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }



    public void guardarMonstruo(Monstruo monstruo){
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(monstruo);
            tx.commit();
            System.out.println("Monstruo guardado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al guardar monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public void actualizarMonstruo(Monstruo monstruo){
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.merge(monstruo);
            tx.commit();
            System.out.println("Monstruo actualizado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al actualizar monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void eliminarMonstruo(Monstruo monstruo){
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.remove(monstruo);
            tx.commit();
            System.out.println("Monstruo eliminado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al eliminar monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void obtenerMonstruo(int id){
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Monstruo monstruo = session.get(Monstruo.class, id);
            if (monstruo != null) {
                System.out.println("Monstruo encontrado: " + monstruo.getNombre());
            } else {
                System.out.println("No se encontró ningún monstruo con el ID proporcionado.");
            }
        } catch(Exception e) {
            System.err.println("Error al obtener monstruo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }





    public void guardarDragon(Dragon dragon){
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(dragon);
            tx.commit();
            System.out.println("Dragón guardado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al guardar dragón: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void actualizarDragon(Dragon dragon){
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.merge(dragon);
            tx.commit();
            System.out.println("Dragón actualizado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al actualizar dragón: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void eliminarDragon(Dragon dragon){
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.remove(dragon);
            tx.commit();
            System.out.println("Dragón eliminado correctamente en la base de datos");
        } catch(Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error al eliminar dragón: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void obtenerDragon(int id){
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            Dragon dragon = session.get(Dragon.class, id);
            if (dragon != null) {
                System.out.println("Dragón encontrado: " + dragon.getNombre());
            } else {
                System.out.println("No se encontró ningún dragón con el ID proporcionado.");
            }
        } catch(Exception e) {
            System.err.println("Error al obtener dragón: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    
    
    /*public String combate(Monstruo monstruo, Mago mago, Hechizo hechizo){
        while (monstruo.getVida() >0 && mago.getVida()>0){
            mago.lanzarHechizo(monstruo, hechizo);
            if (monstruo.getVida() >0){
                monstruo.atacar(mago);
            }
        }
        String resultado;
        if (monstruo.getVida() <=0){
            resultado="El mago "+mago.getNombre()+" ha ganado el combate.";
            eliminarMonstruo(monstruo);
            actualizarMago(mago);
        } else {
            resultado="El monstruo "+monstruo.getNombre()+" ha ganado el combate.";
            eliminarMago(mago);
            actualizarMonstruo(monstruo);
        }
        return resultado;
    }
}
    */

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
