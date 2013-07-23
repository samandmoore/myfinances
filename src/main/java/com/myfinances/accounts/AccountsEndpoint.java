package com.myfinances.accounts;

import com.google.common.collect.Maps;
import com.myfinances.accounts.inputs.AccountCreateRequest;
import com.myfinances.http.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        return Responses.createResponse(account);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Account> createAccount(@RequestBody final AccountCreateRequest request) {
        Account account = accountService.create(1L, request.getName());

        return Responses.createResponse(account, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/errors", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Account> createErrors(@RequestBody final AccountCreateRequest request) {
        Account account = null;
        Map<String, List<String>> errors = Maps.newHashMap();
        errors.put("name", Arrays.asList("too short", "bad choice", "i don't like it"));
        return Responses.createResponse(account, errors);
    }
}
