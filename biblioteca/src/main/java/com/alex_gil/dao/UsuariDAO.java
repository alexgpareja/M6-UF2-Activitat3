package com.alex_gil.dao;

import com.alex_gil.model.Usuari;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UsuariDAO extends GenericDAO<Usuari> {

    // Constructor
    public UsuariDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Usuari.class);
    }
}
