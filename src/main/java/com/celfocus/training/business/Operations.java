package com.celfocus.training.business;

import com.celfocus.training.business.exception.DeleteException;
import com.celfocus.training.business.exception.SaveException;

public interface Operations<T> {

    void save(T t) throws SaveException;
    T get(String name);
    boolean delete(T t) throws DeleteException;

}
