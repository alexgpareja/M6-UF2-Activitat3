package com.alex_gil.dao;

import com.alex_gil.model.Reserva;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ReservaDAO extends GenericDAO<Reserva> {

    // Constructor
    public ReservaDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Reserva.class);
    }
}
