package com.alex_gil.dao;

import com.alex_gil.model.Llibre;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class LlibreDAO {

    private final SessionFactory sessionFactory;

    public LlibreDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Llibre llibre) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(llibre);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error en create(): " + e.getMessage());
        }
    }

    public Llibre read(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Llibre.class, id);
        } catch (HibernateException e) {
            System.err.println("Error en read(): " + e.getMessage());
            return null;
        }
    }

    public void update(Llibre llibre) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(llibre);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error en update(): " + e.getMessage());
        }
    }

    public void delete(Llibre llibre) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(llibre);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error en delete(): " + e.getMessage());
        }
    }

    public List<Llibre> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Llibre", Llibre.class).list();
        } catch (HibernateException e) {
            System.err.println("Error en findAll(): " + e.getMessage());
            return null;
        }
    }
}
