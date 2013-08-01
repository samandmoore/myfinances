package com.myfinances.http;

import com.google.common.base.Optional;
import com.myfinances.common.ModelState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for generating typed http responses with optional errors instead of body
 *
 * TODO: refactor this into a builder pattern where the builder can decide that a special
 * error model should be used in the unsuccessful case. that will make it look something like
 * public HttpResponse createItem(final ItemInput input) {
 *     Item item = itemService.create(...);
 *     return Responses.for(item)
 *              .hasErrors(listOfErrors)
 *              .statusCode(HttpStatus.CONFLICTED)
 *              .create();
 * }
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

    public static ResponseEntity createErrorResponse(HttpStatus httpStatus, ModelState modelState) {
        return createErrorResponse(httpStatus, modelState.asMap());
    }

    public static ResponseEntity createErrorResponse(HttpStatus httpStatus, Map<String, List<String>> errors) {
        return createErrorResponse(errors, Optional.fromNullable(httpStatus));
    }

    private static ResponseEntity createErrorResponse(Map<String, List<String>> errors, Optional<HttpStatus> httpStatus) {
        return new ResponseEntity<>(new ErrorModel(httpStatus.or(HttpStatus.CONFLICT), errors), httpStatus.or(HttpStatus.CONFLICT));
    }
}
