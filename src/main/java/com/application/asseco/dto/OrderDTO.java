package com.application.asseco.dto;


import com.application.asseco.enums.SortDirection;

import java.io.Serializable;

public class OrderDTO implements Serializable {

    private String name;
    private SortDirection direction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderDTO name(String name) {
        this.name = name;
        return this;
    }

    public SortDirection getDirection() {
        return direction;
    }

    public void setDirection(SortDirection direction) {
        this.direction = direction;
    }

    public OrderDTO direction(SortDirection direction) {
        this.direction = direction;
        return this;
    }

    @Override
    public String toString() {
        return name + (direction != null ? ", " + direction.getUrlEncodedName() : "");
    }
}
