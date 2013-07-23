package com.myfinances.accounts;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 7/22/13 9:46 PM
 */
public class Account {
    private Long id;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
