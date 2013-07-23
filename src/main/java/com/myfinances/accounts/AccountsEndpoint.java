package com.myfinances.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 7/22/13 9:50 PM
 */
@Controller
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsEndpoint {

    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccount(@PathVariable Long accountId) {
        return accountService.getById(accountId);
    }
}
