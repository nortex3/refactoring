package com.celfocus.training.business;

import com.celfocus.training.business.exception.DeleteException;
import com.celfocus.training.business.exception.SaveException;

import java.util.List;

public interface IOperations<T> {

    void save(T model) throws SaveException;
    void update(int indexInList, T model) throws SaveException;
    List<T> getAll();
    void delete(int index) throws DeleteException;

}
