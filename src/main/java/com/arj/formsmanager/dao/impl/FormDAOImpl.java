package com.arj.formsmanager.dao.impl;

import com.arj.formsmanager.dao.FormDAO;
import com.arj.formsmanager.entity.Form;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "formDAO")
public class FormDAOImpl implements FormDAO{

    @Autowired
    private SessionFactory sessionFactory;
    private Transaction trans;
    private Session session;

    @Override
    public int insert(Form t) {
        session = sessionFactory.openSession();
        trans = session.beginTransaction();
        int result = (int) session.save(t);
        trans.commit();
        session.close();
        return result;
    }

    @Override
    public void update(Form t) {
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
        Form form = (Form) session.get(Form.class, id);
        if (form != null) {
            session.delete(form);
            success = true;
            trans.commit();
        }
        session.close();
        return success;
    }

    @Override
    public Form getById(int id) {
        session = sessionFactory.openSession();
        Form form = (Form) session.get(Form.class, id);
        session.close();
        return form;
    }

    @Override
    public List<Form> getAll() {
        List<Form> forms = null;
        session = sessionFactory.openSession();
        forms = session.createQuery("Select u from Form u").list();
        session.close();
        return forms;
    }
    
}
