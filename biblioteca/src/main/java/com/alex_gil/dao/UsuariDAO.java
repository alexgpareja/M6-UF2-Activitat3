package com.alex_gil.dao;

import com.alex_gil.model.Usuari;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class UsuariDAO {

    private final SessionFactory sessionFactory;

    public UsuariDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Usuari usuari) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(usuari);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error en create(): " + e.getMessage());
        }
    }

    public Usuari read(String dni) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Usuari WHERE dni = :dni", Usuari.class)
                    .setParameter("dni", dni)
                    .uniqueResult();
        } catch (HibernateException e) {
            System.err.println("Error en read(): " + e.getMessage());
            return null;
        }
    }

    public void update(Usuari usuari) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(usuari);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error en update(): " + e.getMessage());
        }
    }

    public void delete(Usuari usuari) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(usuari);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error en delete(): " + e.getMessage());
        }
    }

    public List<Usuari> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Usuari", Usuari.class).list();
        } catch (HibernateException e) {
            System.err.println("Error en findAll(): " + e.getMessage());
            return null;
        }
    }
}
