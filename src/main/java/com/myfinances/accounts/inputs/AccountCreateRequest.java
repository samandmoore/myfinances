package com.myfinances.accounts.inputs;

import java.math.BigDecimal;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 7/22/13 10:50 PM
 */
public class AccountCreateRequest {
    private String title;
    private BigDecimal balance;
    private Long createdByUserId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Long userId) {
        this.createdByUserId = userId;
    }
}
