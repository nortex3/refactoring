package com.celfocus.training.business;

import com.celfocus.training.business.exception.DeleteException;
import com.celfocus.training.business.exception.SaveException;

public interface IOperations<T> {

    void save(T t) throws SaveException;
    T update(T t) throws SaveException;
    T find(String string);
    boolean delete(T t) throws DeleteException;

}
