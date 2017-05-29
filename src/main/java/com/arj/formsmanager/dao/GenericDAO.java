package com.arj.formsmanager.dao;

import java.util.List;

public interface GenericDAO<T> {
    List<T> getAll();
    int insert(T t);
    void update (T t);
    T getById(int id);
    boolean delete(int id);
}
