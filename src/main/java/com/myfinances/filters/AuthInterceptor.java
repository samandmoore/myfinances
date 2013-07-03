package com.myfinances.filters;

import com.myfinances.users.AuthHttpHelpers;
import com.myfinances.users.IUserService;
import com.myfinances.users.User;
import com.myfinances.users.UserService;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    private final IUserService userService = new UserService();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().startsWith("/users/login")) {
            return true;
        }

        boolean result = preHandleInternal(request, response);

        if (result == false) {
            response.sendRedirect("/users/login");
        }

        return result;
    }

    private boolean preHandleInternal(HttpServletRequest request, HttpServletResponse response) {
        String authCookiedUserId = AuthHttpHelpers.getAuthCookieValue(request);

        if (authCookiedUserId == null) {
            return false;
        }

        Long userId = Long.valueOf(authCookiedUserId);

        if (userId == null) {
            return false;
        }

        User foundUser = userService.findById(userId);

        updateRequest(request, response, foundUser);

        return foundUser != null;
    }

    private void updateRequest(HttpServletRequest request, HttpServletResponse response, User user) {
        if (user == null) {
            return;
        }

        AuthHttpHelpers.setAuthCookie(request, response, user);
    }
}