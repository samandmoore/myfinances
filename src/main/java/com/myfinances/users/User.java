package com.myfinances.users;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
import org.jadira.usertype.dateandtime.joda.PersistentDateTime;
import org.joda.time.DateTime;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private Long id;

    private String username;

    // super-hashed version of password, used for comparisons
    private String hashedPassword;

    // will only be populated before the account is confirmed
    private String unconfirmedEmailAddress;

    // only populated after account is confirmed
    private String emailAddress;

    // auditing fields
    private DateTime createdAt;

    private DateTime lastLoggedInAt;

    // password reset fields
    private String passwordResetToken;

    private DateTime passwordResetTokenExpiration;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getUnconfirmedEmailAddress() {
        return unconfirmedEmailAddress;
    }

    public void setUnconfirmedEmailAddress(String unconfirmedEmailAddress) {
        this.unconfirmedEmailAddress = unconfirmedEmailAddress;
    }

    @Column(nullable = false)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Column(nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Column
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getLastLoggedInAt() {
        return lastLoggedInAt;
    }

    public void setLastLoggedInAt(DateTime lastLoggedInAt) {
        this.lastLoggedInAt = lastLoggedInAt;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    @Column
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getPasswordResetTokenExpiration() {
        return passwordResetTokenExpiration;
    }

    public void setPasswordResetTokenExpiration(DateTime passwordResetTokenExpiration) {
        this.passwordResetTokenExpiration = passwordResetTokenExpiration;
    }

    public void clearPasswordReset() {
        this.passwordResetToken = null;
        this.passwordResetTokenExpiration = null;
    }
}
