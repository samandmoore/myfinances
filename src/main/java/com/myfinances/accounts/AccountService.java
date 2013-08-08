package com.myfinances.accounts;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 7/22/13 10:29 PM
 */
@Component
public class AccountService implements IAccountService {
    private static final List<Account> accounts;

    static {
        accounts = new ArrayList<>();
        accounts.add(getFakeAccount(1L, 1L));
        accounts.add(getFakeAccount(2L, 1L));
        accounts.add(getFakeAccount(3L, 1L));
        accounts.add(getFakeAccount(4L, 1L));
        accounts.add(getFakeAccount(5L, 1L));
        accounts.add(getFakeAccount(6L, 1L));
    }

    private static Long counter = 9L;
    private static Account getFakeAccount(final Long id, final Long userId, final Long... accessors) {
        Account a = new Account();
        a.setId(id);
        a.setTitle("Account " + id.toString());
        a.setCreatedByUserId(userId);
        a.setMemberUserIds(Lists.newArrayList(accessors));

        return a;
    }

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Account getById(final Long id) {
        return Iterables.find(accounts, new Predicate<Account>() {
            @Override
            public boolean apply(@Nullable Account input) {
                return input.getId().equals(id);
            }
        }, null);
    }

    @Override
    public Iterable<Account> getByUserId(final Long userId) {
        return Iterables.filter(accounts, new Predicate<Account>() {
            @Override
            public boolean apply(@Nullable Account input) {
                return input.getCreatedByUserId().equals(userId);
            }
        });
    }

    @Override
    public Account create(Long creatorUserId, String name, Long... usersWithAccess) {
        Account a = getFakeAccount(++counter, creatorUserId);

        a.setTitle(name);

        accounts.add(a);

        return a;
    }
}
