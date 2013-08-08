package com.myfinances.auth;

import com.myfinances.users.IUserService;
import com.myfinances.users.User;
import com.myfinances.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    private final IAuthService authService;
    private static final boolean ranOnce = false;

    @Autowired
    public AuthInterceptor(IAuthService authService, IUserService userService) {
        this.authService = authService;

        userService.create("sam", "samandmoore@gmail.com", "asdfasdf");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().startsWith(AuthConstants.LOGIN_URL)) {
            return true;
        }

        boolean result = preHandleInternal(request, response);

        if (!result) {
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
