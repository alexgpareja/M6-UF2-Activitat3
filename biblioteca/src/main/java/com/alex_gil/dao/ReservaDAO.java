package com.alex_gil.dao;

import com.alex_gil.model.Reserva;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class ReservaDAO {
    private final SessionFactory sessionFactory;

    public ReservaDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Reserva reserva) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(reserva);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error en create(): " + e.getMessage());
        }
    }

    public Reserva read(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Reserva.class, id);
        } catch (HibernateException e) {
            System.err.println("Error en read(): " + e.getMessage());
            return null;
        }
    }

    public List<Reserva> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Reserva", Reserva.class).list();
        } catch (HibernateException e) {
            System.err.println("Error en findAll(): " + e.getMessage());
            return null;
        }
    }
}
