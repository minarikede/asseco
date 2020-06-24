package com.application.asseco.filter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.LocalDate;
import java.util.List;

public class LocalDateFilter extends RangeFilter<LocalDate> {

    private static final long serialVersionUID = 1L;

    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setEquals(final LocalDate equals) {
        super.setEquals(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setGreaterThan(final LocalDate equals) {
        super.setGreaterThan(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setGreaterOrEqualThan(final LocalDate equals) {
        super.setGreaterOrEqualThan(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setLessThan(final LocalDate equals) {
        super.setLessThan(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setLessOrEqualThan(final LocalDate equals) {
        super.setLessOrEqualThan(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE)
    public LocalDateFilter setIn(final List<LocalDate> in) {
        super.setIn(in);
        return this;
    }

}
