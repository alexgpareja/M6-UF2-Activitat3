package com.alex_gil.dao;

import com.alex_gil.model.Llibre;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;

import java.util.List;

public class LlibreDAO {

    private final SessionFactory sessionFactory;

    public LlibreDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Afegir llibre
    public void create(Llibre llibre) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(llibre);
            tx.commit(); // Commit de la transacció
            System.out.println("✅ Llibre afegit correctament.");
        } catch (ConstraintViolationException cve) {
            System.err.println("Error de restricció de base de dades: " + cve.getMessage());
            System.out.println("❌ No s'ha pogut afegir el llibre.");
            if (tx != null)
                tx.rollback();
        } catch (JDBCConnectionException jce) {
            System.err.println("Error de connexió a la base de dades: " + jce.getMessage());
            System.out.println("❌ No s'ha pogut afegir el llibre.");
            if (tx != null)
                tx.rollback();
        } catch (HibernateException hbe) {
            System.err.println("Error d'Hibernate: " + hbe.getMessage());
            System.out.println("❌ No s'ha pogut afegir el llibre.");
            if (tx != null)
                tx.rollback();
        } catch (Exception e) {
            System.err.println("Error inesperat: " + e.getMessage());
            System.out.println("❌ No s'ha pogut afegir el llibre.");
            if (tx != null)
                tx.rollback();
        }
    }

    // Llegir llibre per ID
    public Llibre read(int id) {
        try (Session session = sessionFactory.openSession()) {
            Llibre llibre = session.find(Llibre.class, id); // Buscar llibre per ID

            if (llibre == null) {
                System.out.println("❌ No s'ha trobat cap llibre amb aquest ID.");
            } else {
                System.out.println("📘 Llibre trobat: " + llibre);
            }
            return llibre; // Retorna el llibre o null si no es troba
        } catch (HibernateException hbe) {
            System.err.println("Error d'Hibernate: " + hbe.getMessage());
            return null; // Retorna null si hi ha un error
        } catch (Exception e) {
            System.err.println("Error inesperat: " + e.getMessage());
            return null; // Retorna null en cas d'altres errors
        }
    }

    // Actualitzar llibre
    public void update(Llibre llibre) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(llibre); // Actualitza el llibre a la base de dades
            tx.commit(); // Commit de la transacció
        } catch (ConstraintViolationException cve) {
            System.err.println("Error de restricció de base de dades: " + cve.getMessage());
            System.out.println("❌ No s'ha pogut actualitzar el llibre.");
            if (tx != null)
                tx.rollback();
        } catch (JDBCConnectionException jce) {
            System.err.println("Error de connexió a la base de dades: " + jce.getMessage());
            System.out.println("❌ No s'ha pogut actualitzar el llibre.");
            if (tx != null)
                tx.rollback();
        } catch (HibernateException hbe) {
            System.err.println("Error d'Hibernate: " + hbe.getMessage());
            System.out.println("❌ No s'ha pogut actualitzar el llibre.");
            if (tx != null)
                tx.rollback();
        } catch (Exception e) {
            System.err.println("Error inesperat: " + e.getMessage());
            System.out.println("❌ No s'ha pogut actualitzar el llibre.");
            if (tx != null)
                tx.rollback();
        }
    }

    // Eliminar llibre
    public void delete(Llibre llibre) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {

            // Comprovar si el llibre existeix abans de realitzar la transacció
            Llibre llibreExistent = session.find(Llibre.class, llibre.getIdLlibre());

            if (llibreExistent == null) {
                System.out.println("❌ No s'ha trobat cap llibre amb aquest ID. No s'ha pogut eliminar.");
                return; // Sortim si no es troba el llibre
            }

            // Eliminar el llibre de la sessió per evitar el conflicte
            session.evict(llibreExistent); // Desvincula el llibre de la sessió per evitar errors al eliminar

            tx = session.beginTransaction();
            session.remove(llibre); // Eliminar el llibre de la base de dades
            tx.commit(); // Commit per fer efectiva l'eliminació
            System.out.println("✅ Llibre eliminat correctament!");
        } catch (ConstraintViolationException cve) {
            System.err.println("Error de restricció de base de dades: " + cve.getMessage());
            System.out.println("❌ No s'ha pogut eliminar el llibre.");
            if (tx != null && tx.isActive())
                tx.rollback();
        } catch (JDBCConnectionException jce) {
            System.err.println("Error de connexió a la base de dades: " + jce.getMessage());
            System.out.println("❌ No s'ha pogut eliminar el llibre.");
            if (tx != null && tx.isActive())
                tx.rollback();
        } catch (HibernateException hbe) {
            System.err.println("Error d'Hibernate: " + hbe.getMessage());
            System.out.println("❌ No s'ha pogut eliminar el llibre.");
            if (tx != null && tx.isActive())
                tx.rollback();
        } catch (Exception e) {
            System.err.println("Error inesperat: " + e.getMessage());
            System.out.println("❌ No s'ha pogut eliminar el llibre.");
            if (tx != null && tx.isActive())
                tx.rollback();
        }
    }

    // Trobar tots els llibres
    public List<Llibre> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Llibre> llibres = session.createQuery("FROM Llibre", Llibre.class).list();

            // Comprovem si no es troben llibres
            if (llibres.isEmpty()) {
                System.out.println("❌ No s'han trobat llibres.");
            }

            return llibres;
        } catch (HibernateException e) {
            System.err.println("Error en findAll(): " + e.getMessage());
            e.printStackTrace();
            return null; // Retorna null si hi ha un error
        } catch (Exception e) {
            System.err.println("Error inesperat: " + e.getMessage());
            throw e; // Llança altres tipus d'errors
        }
    }

    // Obtenir llibres disponibles (sense reserva)
    public List<Llibre> obtenirLlibresDisponibles() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Llibre l WHERE l.reserva IS NULL", Llibre.class).list();
        } catch (HibernateException e) {
            System.err.println("Error en obtenirLlibresDisponibles(): " + e.getMessage());
            return null; // Retorna null si hi ha un error
        } catch (Exception e) {
            System.err.println("Error inesperat: " + e.getMessage());
            return null; // Retorna null si hi ha un error inesperat
        }
    }
}
