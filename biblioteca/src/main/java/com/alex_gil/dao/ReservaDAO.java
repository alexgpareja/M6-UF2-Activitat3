package com.alex_gil.dao;

import com.alex_gil.model.Reserva;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;

import java.util.List;

public class ReservaDAO {
    private final SessionFactory sessionFactory;

    public ReservaDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Crear una reserva
    public void create(Reserva reserva) {
        if (reserva == null) {
            System.out.println("❌ La reserva no pot ser null. No s'ha pogut afegir.");
            return;
        }

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(reserva); // Afegeix la reserva a la base de dades
            tx.commit(); // Commit de la transacció
            System.out.println("✅ Reserva afegida correctament.");
        } catch (Exception e) {
            handleException(tx, e, "Error en crear la reserva");
        }
    }

    // Llegir una reserva per ID
    public Reserva read(int id) {
        try (Session session = sessionFactory.openSession()) {
            Reserva reserva = session.find(Reserva.class, id);
            if (reserva == null) {
                System.out.println("❌ No s'ha trobat cap reserva amb aquest ID.");
            } else {
                System.out.println("📘 Reserva trobada: " + reserva);
            }
            return reserva;
        } catch (HibernateException hbe) {
            System.err.println("Error d'Hibernate: " + hbe.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperat: " + e.getMessage());
        }
        return null;
    }

    // Actualitzar una reserva
    public void update(Reserva reserva) {
        if (reserva == null) {
            System.out.println("❌ La reserva no pot ser null. No s'ha pogut actualitzar.");
            return; // Sortir si la reserva és null
        }

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(reserva); // Actualitza la reserva a la base de dades
            tx.commit(); // Commit de la transacció per guardar els canvis
            System.out.println("✅ Reserva actualitzada correctament.");
        } catch (Exception e) {
            handleException(tx, e, "Error en actualitzar la reserva");
        }
    }

    // Eliminar una reserva
    public void delete(Reserva reserva) {
        if (reserva == null) {
            System.out.println("❌ La reserva no pot ser null. No s'ha pogut eliminar.");
            return; // Sortir si la reserva és null
        }

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(reserva); // Elimina la reserva de la base de dades
            tx.commit(); // Commit per fer efectiva l'eliminació
            System.out.println("✅ Reserva eliminada correctament!");
        } catch (Exception e) {
            handleException(tx, e, "Error en eliminar la reserva");
        }
    }

    // Trobar totes les reserves
    public List<Reserva> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Reserva> reserves = session.createQuery("FROM Reserva", Reserva.class).list();
            if (reserves.isEmpty()) {
                System.out.println("❌ No s'han trobat reserves.");
            }
            return reserves;
        } catch (HibernateException e) {
            System.err.println("Error en findAll(): " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperat: " + e.getMessage());
            throw e;
        }
        return null;
    }

    // Mètode auxiliar per gestionar les excepcions i fer el rollback
    private void handleException(Transaction tx, Exception e, String errorMsg) {
        if (tx != null && tx.isActive()) {
            tx.rollback(); // Realitzem el rollback explícit
        }
        System.err.println(errorMsg + ": " + e.getMessage());
        e.printStackTrace(); // Perquè l'excepció sigui més detallada en el registre
    }
}
