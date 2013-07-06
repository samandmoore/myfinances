package com.myfinances.auth;

import com.myfinances.encrypt.IEncryptionService;
import com.myfinances.users.IUserService;
import com.myfinances.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthService implements IAuthService {

    private final IUserService userService;
    private final IEncryptionService encryptionService;

    @Autowired
    public AuthService(IUserService userService, IEncryptionService encryptionService) {
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @Override
    public boolean isLoggedIn(HttpServletRequest request) {
        return AuthHttpHelpers.getCurrentRequestUserId(request) != null;
    }

    @Override
    public boolean isAuthCookiePresent(HttpServletRequest request) {
        return AuthHttpHelpers.getAuthCookieValue(request) != null;
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        final Long userId;

        if (this.isLoggedIn(request)) {
            userId = AuthHttpHelpers.getCurrentRequestUserId(request);
        } else if (this.isAuthCookiePresent(request)) {
            final String val = AuthHttpHelpers.getAuthCookieValue(request);

            if (val == null) {
                return null;
            }

            userId = Long.valueOf(val);
        } else {
            userId = null;
        }

        if (userId == null) {
            return null;
        }

        return userService.findById(userId);
    }

    @Override
    public void login(HttpServletRequest request, HttpServletResponse response, User user) {
        AuthHttpHelpers.setAuthCookie(request, response, user);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        AuthHttpHelpers.unsetAuthCookie(request, response);
    }
}
