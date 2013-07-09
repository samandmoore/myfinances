package com.myfinances.auth;

import org.joda.time.base.AbstractInstant;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 7/6/13 1:21 PM
 */
public class AuthTicket {
    // make a AuthTicket object, then encrypt it
    // probably needs to include:
    // - version (based on some constant)
    // - user id
    // - issue date (when the ticket was created)
    // - expiration date (when the ticket is no longer valid)

    private int version;
    private String userIdentifier;
    private AbstractInstant issuedAt;
    private AbstractInstant expiresAt;

    public AuthTicket() {
    }

    public AuthTicket(int version, String userIdentifier, AbstractInstant issuedAt, AbstractInstant expiresAt) {
        this.version = version;
        this.userIdentifier = userIdentifier;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public AbstractInstant getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(AbstractInstant issuedAt) {
        this.issuedAt = issuedAt;
    }

    public AbstractInstant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(AbstractInstant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public static AuthTicket fromString(String source) {
        // FIXME: this needs to really use the other props too
        AuthTicket ticket = new AuthTicket();
        ticket.setUserIdentifier(source);

        return ticket;
    }

    public String asString() {
        // FIXME: finish this, it should probably be json
        return this.userIdentifier;
    }
}
