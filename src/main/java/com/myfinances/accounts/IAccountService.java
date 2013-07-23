package com.myfinances.accounts;

import java.util.List;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 7/22/13 10:26 PM
 */
public interface IAccountService {
    Account getById(final Long id);

    Iterable<Account> getByUserId(final Long userId);

    Account create(final Long creatorUserId, final String name, final Long... usersWithAccess);
}
