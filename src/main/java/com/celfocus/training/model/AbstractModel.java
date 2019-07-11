package com.celfocus.training.model;

public abstract class AbstractModel {

    private int id;

    public AbstractModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
