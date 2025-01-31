package com.alex_gil.dao;

import com.alex_gil.model.Llibre;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class LlibreDAO extends GenericDAO<Llibre> {

    // Constructor
    public LlibreDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Llibre.class);
    }

}
