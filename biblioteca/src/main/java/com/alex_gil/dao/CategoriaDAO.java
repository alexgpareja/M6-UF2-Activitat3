package com.alex_gil.dao;

import com.alex_gil.model.Categoria;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CategoriaDAO extends GenericDAO<Categoria> {

    // Constructor
    public CategoriaDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Categoria.class);
    }
}
