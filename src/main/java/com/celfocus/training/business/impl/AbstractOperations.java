package com.celfocus.training.business.impl;

import com.celfocus.training.business.IOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractOperations<T> implements IOperations<T> {

    private List<Class<T>> listModel;
    private Class<T> modelType;

    public AbstractOperations(Class<T> modelType) {
        this.modelType = modelType;
        this.listModel = new ArrayList<>();
    }


    public void save(Class<T> model) {
        this.listModel.add(model);
    }

    public void update(Class<T> model, int indexInList) {
        this.listModel.set(indexInList, model);
    }

    public List<Class<T>> findAll() {
        return this.listModel;
    }

    public void delete(Class<T> model) {
        this.listModel.remove(model);
    }

}
