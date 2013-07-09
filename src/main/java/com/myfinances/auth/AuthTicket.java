package com.myfinances.auth;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.base.AbstractInstant;

import java.util.List;

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
        Iterable<String> parts = Splitter.on("|")
                .limit(4)
                .trimResults()
                .split(source);

        List<String> finalParts = Lists.newArrayList(parts);

        AuthTicket ticket = new AuthTicket();
        ticket.setVersion(Integer.valueOf(finalParts.get(0)).intValue());
        ticket.setUserIdentifier(finalParts.get(1));
        ticket.setIssuedAt(new DateTime(Long.valueOf(finalParts.get(2)), DateTimeZone.UTC));
        ticket.setExpiresAt(new DateTime(Long.valueOf(finalParts.get(3)), DateTimeZone.UTC));

        return ticket;
    }

    public String asString() {
        return Joiner.on('|')
                .useForNull("")
                .join(this.version,
                        this.userIdentifier,
                        this.issuedAt.getMillis(),
                        this.expiresAt.getMillis());
    }
}
