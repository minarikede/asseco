package com.application.asseco.web.rest.error.handler;

import java.net.URI;

public final class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String PROBLEM_BASE_URL = "";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "");

    public static final String ERR_INTERNAL_SERVER_ERROR = "internal.server.error";

    private ErrorConstants() {
    }
}
