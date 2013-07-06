package com.myfinances.auth;

import com.myfinances.users.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthHttpHelpers {
    private AuthHttpHelpers() {}

    private static final String AUTH_COOKIE_NAME = "myfinances.auth";

    public static Long getCurrentRequestUserId(HttpServletRequest request) {
        final Object requestUserId = request.getAttribute(AUTH_COOKIE_NAME);

        if (requestUserId == null) {
            return null;
        }

        return (Long)requestUserId;
    }

    public static String getAuthCookieValue(HttpServletRequest request) {
        final Cookie relevantCookie = getAuthCookie(request);

        if (relevantCookie == null) {
            return null;
        }

        return relevantCookie.getValue();
    }

    public static void setAuthCookie(HttpServletRequest request, HttpServletResponse response, User user) {
        request.setAttribute(AUTH_COOKIE_NAME, user.getId());

        // FIXME: use some auth service to create an encrypted token and store that in a cookie.
        final Cookie c = new Cookie(AUTH_COOKIE_NAME, user.getId().toString());
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
