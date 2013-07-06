package com.myfinances.auth;

import com.myfinances.users.User;
import com.myfinances.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthService implements IAuthService {

    private final UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isLoggedIn(HttpServletRequest request) {
        return AuthHttpHelpers.getCurrentRequestUserId(request) != null;
    }

    @Override
    public User getCurrentUser(HttpServletRequest request) {
        Long userId = AuthHttpHelpers.getCurrentRequestUserId(request);

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
