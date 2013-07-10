package com.myfinances.users;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "users")
public class User {

    User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.hashedPassword = password;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    // super-hashed version of password, used for comparisons
    private String hashedPassword;

    // will only be populated before the account is confirmed
    private String unconfirmedEmailAddress;

    // only populated after account is confirmed
    private String emailAddress;

    // auditing fields
    private Date createdAt;
    private Date lastLoggedInAt;

    // password reset fields
    private String passwordResetToken;
    private Date passwordResetTokenExpiration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastLoggedInAt() {
        return lastLoggedInAt;
    }

    public void setLastLoggedInAt(Date lastLoggedInAt) {
        this.lastLoggedInAt = lastLoggedInAt;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public Date getPasswordResetTokenExpiration() {
        return passwordResetTokenExpiration;
    }

    public void setPasswordResetTokenExpiration(Date passwordResetTokenExpiration) {
        this.passwordResetTokenExpiration = passwordResetTokenExpiration;
    }

    public void clearPasswordReset() {
        this.passwordResetToken = null;
        this.passwordResetTokenExpiration = null;
    }
}
