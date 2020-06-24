package com.application.asseco.filter;

public class StringFilter extends Filter<String> {

    private static final long serialVersionUID = 1L;

    private String contains;

    public String getContains() {
        return contains;
    }

    public StringFilter setContains(final String contains) {
        this.contains = contains;
        return this;
    }

    @Override
    public String toString() {
        return getFilterName() + " ["
            + (getContains() != null ? "contains=" + getContains() + ", " : "")
            + (getEquals() != null ? "equals=" + getEquals() + ", " : "")
            + (getSpecified() != null ? "specified=" + getSpecified() : "")
            + "]";
    }

}
