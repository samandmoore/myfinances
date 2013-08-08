package com.myfinances.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 7/22/13 10:29 PM
 */
@Component
@Transactional
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Account getById(final Long id) {
        return accountRepo.find(id);
    }

    @Override
    public Iterable<Account> getByUserId(final Long userId) {
        return accountRepo.list();
    }

    @Override
    public Account create(Long creatorUserId, String name, Long... usersWithAccess) {
        Account a = new Account();

        a.setTitle(name);

        accountRepo.add(a);

        return a;
    }
}
