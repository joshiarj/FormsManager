package com.arj.formsmanager.dao.impl;

import com.arj.formsmanager.dao.FormOptionDAO;
import com.arj.formsmanager.entity.FormOption;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "fOptDAO")
public class FormOptionDAOImpl implements FormOptionDAO{

    @Autowired
    private SessionFactory sessionFactory;
    private Transaction trans;
    private Session session;

    @Override
    public int insert(FormOption t) {
        session = sessionFactory.openSession();
        trans = session.beginTransaction();
        int result = (int) session.save(t);
        trans.commit();
        session.close();
        return result;
    }

    @Override
    public void update(FormOption t) {
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
        FormOption formOption = (FormOption) session.get(FormOption.class, id);
        if (formOption != null) {
            session.delete(formOption);
            success = true;
            trans.commit();
        }
        session.close();
        return success;
    }

    @Override
    public FormOption getById(int id) {
        session = sessionFactory.openSession();
        FormOption formOption = (FormOption) session.get(FormOption.class, id);
        session.close();
        return formOption;
    }

    @Override
    public List<FormOption> getAll() {
        List<FormOption> formOptions = null;
        session = sessionFactory.openSession();
        formOptions = session.createQuery("Select u from FormOption u").list();
        session.close();
        return formOptions;
    }
    
}
