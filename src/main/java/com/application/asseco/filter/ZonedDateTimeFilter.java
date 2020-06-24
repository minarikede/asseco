package com.application.asseco.filter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.ZonedDateTime;
import java.util.List;

public class ZonedDateTimeFilter extends RangeFilter<ZonedDateTime> {

    private static final long serialVersionUID = 1L;

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public ZonedDateTimeFilter setEquals(final ZonedDateTime equals) {
        super.setEquals(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public ZonedDateTimeFilter setGreaterThan(final ZonedDateTime equals) {
        super.setGreaterThan(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public ZonedDateTimeFilter setGreaterOrEqualThan(final ZonedDateTime equals) {
        super.setGreaterOrEqualThan(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public ZonedDateTimeFilter setLessThan(final ZonedDateTime equals) {
        super.setLessThan(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public ZonedDateTimeFilter setLessOrEqualThan(final ZonedDateTime equals) {
        super.setLessOrEqualThan(equals);
        return this;
    }

    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public ZonedDateTimeFilter setIn(final List<ZonedDateTime> in) {
        super.setIn(in);
        return this;
    }
}
