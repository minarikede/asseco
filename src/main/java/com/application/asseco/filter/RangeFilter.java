package com.application.asseco.filter;

public class RangeFilter<FIELD_TYPE extends Comparable<? super FIELD_TYPE>> extends
    Filter<FIELD_TYPE> {

    private static final long serialVersionUID = 1L;

    private FIELD_TYPE greaterThan;
    private FIELD_TYPE lessThan;
    private FIELD_TYPE greaterOrEqualThan;
    private FIELD_TYPE lessOrEqualThan;

    public FIELD_TYPE getGreaterThan() {
        return greaterThan;
    }

    public RangeFilter<FIELD_TYPE> setGreaterThan(final FIELD_TYPE greaterThan) {
        this.greaterThan = greaterThan;
        return this;
    }

    public FIELD_TYPE getGreaterOrEqualThan() {
        return greaterOrEqualThan;
    }

    public RangeFilter<FIELD_TYPE> setGreaterOrEqualThan(final FIELD_TYPE greaterOrEqualThan) {
        this.greaterOrEqualThan = greaterOrEqualThan;
        return this;
    }

    public FIELD_TYPE getLessThan() {
        return lessThan;
    }

    public RangeFilter<FIELD_TYPE> setLessThan(final FIELD_TYPE lessThan) {
        this.lessThan = lessThan;
        return this;
    }

    public FIELD_TYPE getLessOrEqualThan() {
        return lessOrEqualThan;
    }

    public RangeFilter<FIELD_TYPE> setLessOrEqualThan(final FIELD_TYPE lessOrEqualThan) {
        this.lessOrEqualThan = lessOrEqualThan;
        return this;
    }

    @Override
    public String toString() {
        return getFilterName() + " ["
            + (getGreaterThan() != null ? "greaterThan=" + getGreaterThan() + ", " : "")
            + (getGreaterOrEqualThan() != null ? "greaterOrEqualThan=" + getGreaterOrEqualThan()
            + ", " : "")
            + (getLessThan() != null ? "lessThan=" + getLessThan() + ", " : "")
            + (getLessOrEqualThan() != null ? "lessOrEqualThan=" + getLessOrEqualThan() + ", " : "")
            + (getEquals() != null ? "equals=" + getEquals() + ", " : "")
            + (getSpecified() != null ? "specified=" + getSpecified() + ", " : "")
            + (getIn() != null ? "in=" + getIn() : "")
            + "]";
    }

}
