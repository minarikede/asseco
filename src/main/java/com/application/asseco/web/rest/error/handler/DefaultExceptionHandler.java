package com.application.asseco.web.rest.error.handler;

import com.application.asseco.web.rest.error.EntityCreationException;
import com.application.asseco.web.rest.error.UserNotFoundException;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.annotation.Nonnull;


/**
 * Controller advice to translate the server side exceptions to client-friendly json structures. The
 * error response follows RFC7807 - Problem Details for HTTP APIs (https://tools.ietf.org/html/rfc7807)
 */
@ControllerAdvice
public class DefaultExceptionHandler extends AbstractExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Problem> handleUserNotFoundException(UserNotFoundException ex,
                                                               NativeWebRequest request) {

        final Problem problem = build(ErrorConstants.DEFAULT_TYPE, Status.NOT_FOUND,
                ErrorConstants.ERR_USER_NOT_FOUND);

        return create(ex, problem, request);
    }

    @ExceptionHandler(EntityCreationException.class)
    public ResponseEntity<Problem> handleEntityCreationException(EntityCreationException ex,
                                                               NativeWebRequest request) {

        final Problem problem = build(ErrorConstants.DEFAULT_TYPE, Status.BAD_REQUEST,
                ErrorConstants.ERR_ENTITY_CREATION);

        return create(ex, problem, request);
    }

    @ExceptionHandler(ConcurrencyFailureException.class)
    public ResponseEntity<Problem> handleConcurrencyFailure(ConcurrencyFailureException ex,
                                                            NativeWebRequest request) {

        final Problem problem = build(ErrorConstants.DEFAULT_TYPE, Status.CONFLICT,
                ErrorConstants.ERR_CONCURRENCY_FAILURE);

        return create(ex, problem, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Problem> handleAccessDeniedException(AccessDeniedException ex,
                                                               NativeWebRequest request) {

        final Problem problem = build(ErrorConstants.DEFAULT_TYPE, Status.FORBIDDEN, ex.getMessage());
        return create(ex, problem, request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Problem> handleMethodArgumentNotValid(RuntimeException ex,
                                                                @Nonnull NativeWebRequest request) {
        final Problem problem = build(ErrorConstants.DEFAULT_TYPE, Status.INTERNAL_SERVER_ERROR,
                ErrorConstants.ERR_INTERNAL_SERVER_ERROR);

        return create(ex, problem, request);
    }

}
