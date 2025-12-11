package com.example.Controller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.Model.Bosque;
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
    
    public String combate(Monstruo monstruo, Mago mago){
        while (monstruo.getVida() >0 && mago.getVida()>0){
            mago.lanzarHechizo(monstruo);
            if (monstruo.getVida() >0){
                monstruo.atacar(mago);
            }
        }
        String resultado;
        if (monstruo.getVida() <=0){
            resultado="El mago "+mago.getNombre()+" ha ganado el combate.";
        } else {
            resultado="El monstruo "+monstruo.getNombre()+" ha ganado el combate.";
        }
        return resultado;
    }
}
