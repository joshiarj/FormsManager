package com.arj.formsmanager.dao.impl;

import com.arj.formsmanager.dao.FormFieldDAO;
import com.arj.formsmanager.entity.FormField;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "ffDAO")
public class FormFieldDAOImpl implements FormFieldDAO{

    @Autowired
    private SessionFactory sessionFactory;
    private Transaction trans;
    private Session session;

    @Override
    public int insert(FormField t) {
        session = sessionFactory.openSession();
        trans = session.beginTransaction();
        int result = (int) session.save(t);
        trans.commit();
        session.close();
        return result;
    }

    @Override
    public void update(FormField t) {
        session = sessionFactory.openSession();
        trans = session.beginTransaction();
        session.saveOrUpdate(t);
        trans.commit();
        session.close();
    }

    @Override
    public boolean delete(int id) {
        boolean success = false;
        session = sessionFactory.openSession();
        trans = session.beginTransaction();
        FormField formField = (FormField) session.get(FormField.class, id);
        if (formField != null) {
            session.delete(formField);
            success = true;
            trans.commit();
        }
        session.close();
        return success;
    }

    @Override
    public FormField getById(int id) {
        session = sessionFactory.openSession();
        FormField formField = (FormField) session.get(FormField.class, id);
        session.close();
        return formField;
    }

    @Override
    public List<FormField> getAll() {
        List<FormField> formFields = null;
        session = sessionFactory.openSession();
        formFields = session.createQuery("Select u from FormField u").list();
        session.close();
        return formFields;
    }
    
}
