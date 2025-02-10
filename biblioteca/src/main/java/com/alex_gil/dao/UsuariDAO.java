package com.alex_gil.dao;

import com.alex_gil.model.Usuari;

import jakarta.validation.ConstraintViolationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.JDBCConnectionException;

import java.util.List;

public class UsuariDAO {

    private final SessionFactory sessionFactory;

    public UsuariDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Afegir usuari
    public void create(Usuari usuari) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(usuari); // Afegeix l'usuari a la base de dades
            tx.commit(); // Si tot va bé, commit la transacció
            System.out.println("✅ Usuari afegit correctament.");
        } catch (ConstraintViolationException cve) {
            System.err.println(
                    "Error de restricció de base de dades: " + cve.getMessage());
            System.out.println("❌ No s'ha pogut afegir l'usuari.");
            if (tx != null)
                tx.rollback();
        } catch (JDBCConnectionException jce) {
            System.err.println("Error de connexió a la base de dades: " + jce.getMessage());
            System.out.println("❌ No s'ha pogut afegir l'usuari.");
            if (tx != null)
                tx.rollback();
        } catch (HibernateException hbe) {
            System.err.println("Error d'Hibernate: " + hbe.getMessage());
            System.out.println("❌ No s'ha pogut afegir l'usuari.");
            if (tx != null)
                tx.rollback();
        } catch (Exception e) {
            System.err.println("Error inesperat: " + e.getMessage());
            System.out.println("❌ No s'ha pogut afegir l'usuari.");
            if (tx != null)
                tx.rollback();
        }
    }

    // Llegir un usuari pel seu DNI
    public Usuari read(String dni) {
        try (Session session = sessionFactory.openSession()) {
            // Executar la consulta per trobar l'usuari pel DNI
            Usuari usuari = session.createQuery("FROM Usuari WHERE dni = :dni", Usuari.class)
                    .setParameter("dni", dni)
                    .uniqueResult();

            // Comprovem si l'usuari és null
            if (usuari == null) {
                System.out.println("❌ No s'ha trobat cap usuari amb el DNI: " + dni);
            } else {
                System.out.println("🆔 Usuari trobat: " + usuari);
            }
            return usuari;
        } catch (HibernateException hbe) {
            System.err.println("Error d'Hibernate: " + hbe.getMessage());
            return null; // Retorna null si no es pot llegir l'usuari
        } catch (Exception e) {
            System.err.println("Error inesperat: " + e.getMessage());
            return null; // Retorna null si hi ha altres errors
        }
    }

    // Actualitzar un usuari
    public void update(Usuari usuari) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(usuari); // Actualitza l'usuari a la base de dades
            tx.commit(); // Commit de la transacció per guardar els canvis
        } catch (ConstraintViolationException cve) {
            System.err.println("Error de restricció de base de dades: " + cve.getMessage());
            System.out.println("❌ No s'ha pogut actualitzar l'usuari.");
            if (tx != null)
                tx.rollback();
        } catch (JDBCConnectionException jce) {
            System.err.println("Error de connexió a la base de dades: " + jce.getMessage());
            System.out.println("❌ No s'ha pogut actualitzar l'usuari.");
            if (tx != null)
                tx.rollback();
        } catch (HibernateException hbe) {
            System.err.println("Error d'Hibernate: " + hbe.getMessage());
            System.out.println("❌ No s'ha pogut actualitzar l'usuari.");
            if (tx != null)
                tx.rollback();
        } catch (Exception e) {
            System.err.println("Error inesperat: " + e.getMessage());
            System.out.println("❌ No s'ha pogut actualitzar l'usuari.");
            if (tx != null)
                tx.rollback();
        }
    }

    // Eliminar un usuari
    public void delete(Usuari usuari) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.remove(usuari); // Eliminar l'usuari de la base de dades
            tx.commit(); // Commit per fer efectiva l'eliminació
            System.out.println("✅ Usuari eliminat correctament!");
        } catch (ConstraintViolationException cve) {
            System.err.println("Error de restricció de base de dades: " + cve.getMessage());
            System.out.println("❌ No s'ha pogut eliminar l'usuari.");
            if (tx != null && tx.isActive())
                tx.rollback();
        } catch (JDBCConnectionException jce) {
            System.err.println("Error de connexió a la base de dades: " + jce.getMessage());
            System.out.println("❌ No s'ha pogut eliminar l'usuari.");
            if (tx != null && tx.isActive())
                tx.rollback();
        } catch (HibernateException hbe) {
            System.err.println("Error d'Hibernate: " + hbe.getMessage());
            System.out.println("❌ No s'ha pogut eliminar l'usuari.");
            if (tx != null && tx.isActive())
                tx.rollback();
        } catch (Exception e) {
            System.err.println("Error inesperat: " + e.getMessage());
            System.out.println("❌ No s'ha pogut eliminar l'usuari.");
            if (tx != null && tx.isActive())
                tx.rollback();
        }
    }

    // Trobar tots els usuaris
    public List<Usuari> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Usuari> usuaris = session.createQuery("FROM Usuari", Usuari.class).list();

            // Comprovem si no es troben usuaris
            if (usuaris.isEmpty()) {
                System.out.println("❌ No s'han trobat usuaris.");
            }

            return usuaris;
        } catch (HibernateException e) {
            System.err.println("Error en findAll(): " + e.getMessage());
            e.printStackTrace();
            return null; // Retorna null si hi ha un error
        } catch (Exception e) {
            System.err.println("Error inesperat: " + e.getMessage());
            throw e; // Llança altres tipus d'errors
        }
    }
}
