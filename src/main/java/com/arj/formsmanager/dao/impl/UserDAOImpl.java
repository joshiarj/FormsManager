package com.arj.formsmanager.dao.impl;

import com.arj.formsmanager.dao.UserDAO;
import com.arj.formsmanager.entity.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value = "userDAO")
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;
    private Transaction trans;
    private Session session;

    @Override
    public int insert(User t) {
        session = sessionFactory.openSession();
        trans = session.beginTransaction();
        int result = (int) session.save(t);
        trans.commit();
        session.close();
        return result;
    }

    @Override
    public void update(User t) {
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
        User user = (User) session.get(User.class, id);
        if (user != null) {
            session.delete(user);
            success = true;
            trans.commit();
        }
        session.close();
        return success;
    }

    @Override
    public User getById(int id) {
        session = sessionFactory.openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = null;
        session = sessionFactory.openSession();
        users = session.createQuery("Select u from User u").list();
        session.close();
        return users;
    }
    
}
