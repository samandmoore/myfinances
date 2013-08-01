package com.myfinances.auth;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.base.AbstractInstant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * this is intended to be a package-private helper class for dealing with request / response cookies
 * do not use this outside of the auth package.
 *
 * @author Sam Moore
 */
final class AuthHttpHelpers {
    private AuthHttpHelpers() {
    }

    private static final String AUTH_COOKIE_NAME = "myfinances.auth";

    static Long getCurrentRequestUserId(HttpServletRequest request) {
        final Object requestUserId = request.getAttribute(AUTH_COOKIE_NAME);

        if (requestUserId == null) {
            return null;
        }

        return (Long) requestUserId;
    }

    static String getAuthCookieValue(HttpServletRequest request) {
        final Cookie relevantCookie = getAuthCookie(request);

        if (relevantCookie == null) {
            return null;
        }

        return relevantCookie.getValue();
    }

    static void setAuthCookie(HttpServletRequest request, HttpServletResponse response,
                                     Long userId, AbstractInstant expiresAt,
                                     String encryptedTicket) {
        request.setAttribute(AUTH_COOKIE_NAME, userId);

        long millisTilExpira = expiresAt.getMillis() - DateTime.now(DateTimeZone.UTC).getMillis();
        int secondsTilExpira = (int) (millisTilExpira / 1000);

        final Cookie c = new Cookie(AUTH_COOKIE_NAME, encryptedTicket);
        c.setPath("/");
        c.setMaxAge(secondsTilExpira); // -1 would be until browser close
        c.setHttpOnly(true);
        response.addCookie(c);
    }

    static void unsetAuthCookie(HttpServletRequest request, HttpServletResponse response) {
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
