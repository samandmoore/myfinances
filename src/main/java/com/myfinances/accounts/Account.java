package com.myfinances.accounts;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 7/22/13 9:46 PM
 */
public class Account {
    private Long id;
    private String title;
    private BigDecimal balance;

    // relationships
    private Long createdByUserId;
    private List<Long> memberUserIds;

    public Account() {
        memberUserIds = Lists.newArrayList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public List<Long> getMemberUserIds() {
        return memberUserIds;
    }

    public void setMemberUserIds(List<Long> memberUserIds) {
        this.memberUserIds = memberUserIds;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
