package com.myfinances.http;

import com.google.common.base.Optional;
import com.myfinances.common.ModelState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * Helper class for generating typed http responses or error models
 *
 * @author sam
 * @since 7/22/13 11:31 PM
 */
public class Responses {
    private Responses() {}

    public static <T> ResponseEntity<T> createResponse(HttpStatus httpStatus, T item) {
        return createResponse(item, Optional.fromNullable(httpStatus));
    }

    private static <T> ResponseEntity<T> createResponse(T item, Optional<HttpStatus> httpStatusOptional) {
        return new ResponseEntity<>(item, httpStatusOptional.or(HttpStatus.OK));
    }

    public static ResponseEntity createErrorResponse(HttpStatus httpStatus) {
        return createErrorResponse(httpStatus, ModelState.EMPTY);
    }
    public static ResponseEntity createErrorResponse(HttpStatus httpStatus, ModelState modelState) {
        return createErrorResponse(httpStatus, modelState.asMap());
    }

    private static ResponseEntity createErrorResponse(HttpStatus httpStatus, Map<String, List<String>> errors) {
        return createErrorResponse(errors, Optional.fromNullable(httpStatus));
    }

    private static ResponseEntity createErrorResponse(Map<String, List<String>> errors, Optional<HttpStatus> httpStatus) {
        return new ResponseEntity<>(new ErrorModel(httpStatus.or(HttpStatus.CONFLICT), errors), httpStatus.or(HttpStatus.CONFLICT));
    }
}
