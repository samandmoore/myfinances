package com.myfinances.auth;

import com.myfinances.encrypt.IEncryptionService;
import com.myfinances.users.IUserService;
import com.myfinances.users.User;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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

            AuthTicket decryptedVal = tryDecryptTicket(val);

            if (decryptedVal == null) {
                return null;
            }

            if (decryptedVal.getUserIdentifier() == null) {
                return null;
            }

            userId = Long.valueOf(decryptedVal.getUserIdentifier());
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
        DateTime utcNow = DateTime.now(DateTimeZone.UTC);
        DateTime utcNowPlus1Day = utcNow.plusDays(1);
        AuthTicket ticket = new AuthTicket(1, user.getId().toString(), utcNow, utcNowPlus1Day);

        String encryptedVal = tryEncryptTicket(ticket);

        AuthHttpHelpers.setAuthCookie(request, response, user.getId(), utcNowPlus1Day, encryptedVal);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        AuthHttpHelpers.unsetAuthCookie(request, response);
    }

    private String tryEncryptTicket(AuthTicket authTicket) {
        String encryptedVal;
        try {
            encryptedVal = encryptionService.encrypt(authTicket.asString());
        } catch (Exception e) {
            throw new Error("unable to encrypt auth ticket!", e);
        }

        return encryptedVal;
    }

    private AuthTicket tryDecryptTicket(String toDecrypt) {
        String decryptedVal;
        try {
            decryptedVal = encryptionService.decrypt(toDecrypt);
        } catch (Exception e) {
            // swallow
            decryptedVal = null;
        }

        AuthTicket authTicket;
        if (decryptedVal != null) {
            authTicket = AuthTicket.fromString(decryptedVal);
        } else {
            authTicket = null;
        }

        return authTicket;
    }
}
