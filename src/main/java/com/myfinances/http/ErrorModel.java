package com.myfinances.http;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

/**
 * an http response wrapper for error results
 *
 * @author Sam Moore
 * @since 7/23/13 9:36 PM
 */
final class ErrorModel {
    private final HttpStatus httpStatus;
    private final Map<String, List<String>> errors;

    public ErrorModel(HttpStatus httpStatus, Map<String, List<String>> errors) {

        this.httpStatus = httpStatus;
        this.errors = errors;
    }

    public int getStatusCode() {
        return httpStatus.value();
    }

    public String getStatusText() {
        return httpStatus.getReasonPhrase();
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }
}
