package com.myfinances.auth;

import com.myfinances.users.User;
import org.joda.time.base.AbstractInstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthHttpHelpers {
    private AuthHttpHelpers() {
    }

    private static final String AUTH_COOKIE_NAME = "myfinances.auth";

    public static Long getCurrentRequestUserId(HttpServletRequest request) {
        final Object requestUserId = request.getAttribute(AUTH_COOKIE_NAME);

        if (requestUserId == null) {
            return null;
        }

        return (Long) requestUserId;
    }

    public static String getAuthCookieValue(HttpServletRequest request) {
        final Cookie relevantCookie = getAuthCookie(request);

        if (relevantCookie == null) {
            return null;
        }

        return relevantCookie.getValue();
    }

    public static void setAuthCookie(HttpServletRequest request, HttpServletResponse response,
                                     Long userId, AbstractInstant expiresAt,
                                     String encryptedTicket) {
        request.setAttribute(AUTH_COOKIE_NAME, userId);

        // FIXME: create an encrypted ticket and store that in a cookie.
        // make a AuthTicket object, then encrypt it
        // probably needs to include:
        // - version (based on some constant)
        // - user id
        // - issue date (when the ticket was created)
        // - expiration date (when the ticket is no longer valid)
        final Cookie c = new Cookie(AUTH_COOKIE_NAME, encryptedTicket);
        c.setPath("/");
        c.setMaxAge(-1); // lasts until browser close
        c.setHttpOnly(true);
        response.addCookie(c);
    }

    public static void unsetAuthCookie(HttpServletRequest request, HttpServletResponse response) {
        final Cookie relevantCookie = getAuthCookie(request);

        if (relevantCookie == null) {
            return;
        }

        final Cookie c = new Cookie(AUTH_COOKIE_NAME, null);
        c.setPath("/");
        c.setMaxAge(0);
        c.setHttpOnly(true);
        response.addCookie(c);
    }

    // TODO: make this actually return an authticket object
    private static Cookie getAuthCookie(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            return null;
        }

        for (Cookie c : cookies) {
            if (c.getName().equals(AUTH_COOKIE_NAME)) {
                return c;
            }
        }

        return null;
    }
}
