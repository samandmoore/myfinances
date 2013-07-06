package com.myfinances.auth;

import com.myfinances.users.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAuthService {

    boolean isLoggedIn(HttpServletRequest request);

    User getCurrentUser(HttpServletRequest request);

    void login(HttpServletRequest request, HttpServletResponse response, User user);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
