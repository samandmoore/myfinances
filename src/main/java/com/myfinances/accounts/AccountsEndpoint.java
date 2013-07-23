package com.myfinances.accounts;

import com.myfinances.accounts.inputs.AccountCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        Account account = accountService.getById(accountId);
        return new ResponseEntity<Account>(account, account == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Account> createAccount(@RequestBody final AccountCreateRequest request) {
        Account account = accountService.create(1L, request.getName());

        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
    }
}
