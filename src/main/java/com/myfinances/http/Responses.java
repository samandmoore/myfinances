package com.myfinances.http;

import com.google.common.base.Optional;
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
 * @author sam
 * @since 7/22/13 11:31 PM
 */
public class Responses {
    private Responses() {}

    public static <T> ResponseEntity<T> createResponse(T item) {
        return createResponse(item, Optional.<HttpStatus>absent(), new HashMap<String, List<String>>());
    }

    public static <T> ResponseEntity<T> createResponse(T item, HttpStatus httpStatus) {
        return createResponse(item, Optional.fromNullable(httpStatus), new HashMap<String, List<String>>());
    }

    public static <T> ResponseEntity<T> createResponse(T item, Map<String, List<String>> errors) {
        return createResponse(item, Optional.<HttpStatus>absent(), errors);
    }

    public static <T> ResponseEntity<T> createResponse(T item, HttpStatus httpStatus, Map<String, List<String>> errors) {
        return createResponse(item, Optional.fromNullable(httpStatus), errors);
    }

    private static <T> ResponseEntity<T> createResponse(T item, Optional<HttpStatus> httpStatusOptional, Map<String, List<String>> errors) {
        if (!errors.isEmpty()) {
            MultiValueMap<String, String> errorMap = new LinkedMultiValueMap<>(errors);
            return new ResponseEntity<T>(item, errorMap, httpStatusOptional.or(HttpStatus.CONFLICT));
        }

        if (item == null) {
            return new ResponseEntity<T>(item, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<T>(item, httpStatusOptional.or(HttpStatus.OK));
    }
}
