package com.kkolcz.model;

public abstract class AbstractModel<C> {
    public abstract void  fillDataFromCommandObject(C command);
    public abstract boolean isNew();
}
