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
}
