package com.application.asseco.web.rest.error.handler;

import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.AdviceTrait;

import java.net.URI;
import java.util.Collection;
import java.util.List;

public abstract class AbstractExceptionHandler implements AdviceTrait {

    protected Problem build(URI type, Status status, String message) {

        return build(type, status, message, null, null);
    }

    protected Problem build(URI type, Status status, String message,
                            List<Object> params) {

        return build(type, status, message, params, null);
    }

    protected Problem build(URI type, Status status, String message, Collection<Error> errors) {

        return build(type, status, message, null, errors);
    }


    protected Problem build(URI type, Status status, int errorCode) {

        return Problem.builder()
                .withType(type)
                .withStatus(status)
                .with("errorCode", errorCode)
                .build();
    }

    protected Problem build(URI type, Status status, String message,
                            List<Object> params, Collection<Error> errors) {

        return Problem.builder()
                .withType(type)
                .withStatus(status)
                .with("message", message)
                .with("params", params)
                .with("errors", errors)
                .build();
    }
}
