package com.myfinances.accounts;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 8/13/13 9:25 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTests {

    @Mock
    private AccountRepo accountRepo;

    @InjectMocks
    private final IAccountService accountService = new AccountService();

    @Test
    public void create_returns_properly_built_model() {

        final Long userId = 1L;
        final String title = "name";
        Account account = accountService.create(userId, title);

        Assert.assertNotNull(account);
        Assert.assertNotNull(account.getCreatedAt());
        Assert.assertEquals(BigDecimal.ZERO, account.getBalance());
        Assert.assertEquals(userId, account.getCreatedByUserId());
        Assert.assertEquals(title, account.getTitle());

        Mockito.verify(accountRepo).add(account);
    }
}
