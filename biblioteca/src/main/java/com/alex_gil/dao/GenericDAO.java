package com.alex_gil.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Classe genèrica per gestionar operacions CRUD bàsics amb Hibernate.
 * 
 * @param <T> Tipus de l'entitat que es gestionarà.
 */
public abstract class GenericDAO<T> {
    protected final SessionFactory sessionFactory;
    private final Class<T> entityType;

    /**
     * Constructor que inicialitza el DAO amb la sessió de Hibernate i el tipus
     * d'entitat.
     */
    public GenericDAO(SessionFactory sessionFactory, Class<T> entityType) {
        this.sessionFactory = sessionFactory;
        this.entityType = entityType;
    }

    /**
     * Guarda una nova entitat a la base de dades.
     */
    public void create(T entity) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(entity); // Afegim l'entitat
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback(); // Revertim si hi ha error
            System.err.println("Error en el mètode create(): " + e.getMessage());
        }
    }

    /**
     * Cerca una entitat per ID.
     * 
     * @return L'entitat trobada o null si no existeix.
     */
    public T read(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(entityType, id);
        } catch (HibernateException e) {
            System.err.println("Error en el mètode read(): " + e.getMessage());
            return null;
        }
    }

    /**
     * Actualitza una entitat existent.
     */
    public void update(T entity) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(entity); // Hibernate 6 usa merge() per updates
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error en el mètode update(): " + e.getMessage());
        }
    }

    /**
     * Elimina una entitat de la base de dades.
     */
    public void delete(T entity) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(entity); // Canviat de delete() a remove() per Hibernate 6
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            System.err.println("Error en el metode delete(): " + e.getMessage());
        }
    }

    /**
     * Retorna una llista amb totes les entitats de la base de dades.
     */
    public List<T> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from " + entityType.getName(), entityType).list();
        } catch (HibernateException e) {
            System.err.println("Error en el metode findAll(): " + e.getMessage());
            return null;
        }
    }
}
