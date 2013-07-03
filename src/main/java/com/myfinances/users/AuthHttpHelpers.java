package com.myfinances.users;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthHttpHelpers {
    private AuthHttpHelpers() {}

    private static final String AUTH_COOKIE_NAME = "myfinances.auth";

    public static String getAuthCookieValue(HttpServletRequest request) {
        Cookie relevantCookie = getAuthCookie(request);

        if (relevantCookie == null) {
            return null;
        }

        String authCookiedUserId = relevantCookie.getValue();

        return authCookiedUserId;
    }

    public static void setAuthCookie(HttpServletRequest request, HttpServletResponse response, User user) {
        request.setAttribute(AUTH_COOKIE_NAME, user.getId());

        // FIXME: use some auth service to create an encrypted token and store that in a cookie.
        Cookie c = new Cookie(AUTH_COOKIE_NAME, user.getId().toString());
        c.setMaxAge(3600);
        response.addCookie(c);
    }

    public static void unsetAuthCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie relevantCookie = getAuthCookie(request);

        if (relevantCookie == null) {
            return;
        }

        relevantCookie.setValue("");
        relevantCookie.setMaxAge(-1);
        response.addCookie(relevantCookie);
    }

    private static Cookie getAuthCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

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
