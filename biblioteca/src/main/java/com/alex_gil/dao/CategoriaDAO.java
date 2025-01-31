package com.alex_gil.dao;

import com.alex_gil.model.Categoria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class CategoriaDAO {
    private final SessionFactory sessionFactory;

    public CategoriaDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Categoria categoria) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(categoria);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error en create(): " + e.getMessage());
        }
    }

    public Categoria read(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Categoria.class, id);
        } catch (HibernateException e) {
            System.err.println("Error en read(): " + e.getMessage());
            return null;
        }
    }

    public List<Categoria> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Categoria", Categoria.class).list();
        } catch (HibernateException e) {
            System.err.println("Error en findAll(): " + e.getMessage());
            return null;
        }
    }

    public List<Object[]> comptarLlibresPerCategoria() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT c.nomCategoria, COUNT(l) FROM Categoria c LEFT JOIN c.llibres l GROUP BY c.nomCategoria",
                    Object[].class).list();
        } catch (HibernateException e) {
            System.err.println("Error en comptarLlibresPerCategoria(): " + e.getMessage());
            return null;
        }
    }

}
