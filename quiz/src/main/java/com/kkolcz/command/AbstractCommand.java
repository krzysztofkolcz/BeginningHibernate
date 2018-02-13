package com.kkolcz.command;

import java.io.Serializable;

public abstract class AbstractCommand implements Serializable {

    protected static final long serialVersionUID = 1032319857109857011L;
    protected Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
