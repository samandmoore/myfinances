package com.myfinances.auth;

import com.myfinances.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    private final IAuthService authService;

    @Autowired
    public AuthInterceptor(IAuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().startsWith(AuthConstants.LOGIN_URL)) {
            return true;
        }

        boolean result = preHandleInternal(request, response);

        if (result == false) {
            response.sendRedirect(AuthConstants.LOGIN_URL);
        }

        return result;
    }

    private boolean preHandleInternal(HttpServletRequest request, HttpServletResponse response) {
        if (!authService.isAuthCookiePresent(request)) {
            return false;
        }

        User user = authService.getCurrentUser(request);

        if (user == null) {
            return false;
        }

        updateRequest(request, response, user);

        return true;
    }

    private void updateRequest(HttpServletRequest request, HttpServletResponse response, User user) {
        Assert.notNull(user, "user must be logged in, cannot be null");

        authService.login(request, response, user);
    }
}
