package com.myfinances.accounts;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.myfinances.accounts.inputs.AccountCreateRequest;
import com.myfinances.accounts.inputs.AccountFetchRequest;
import com.myfinances.common.ModelState;
import com.myfinances.http.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAccounts(@ModelAttribute final AccountFetchRequest request) {

        if (request.getForUserId() != null) {
            Long userId = request.getForUserId();
            List<Account> accountsForUser = Lists.newArrayList(accountService.getByUserId(userId));
            return Responses.createResponse(HttpStatus.OK, accountsForUser);
        }

        ModelState modelState = new ModelState();
        modelState.add("request", "You must provide a user to get accounts.");

        return Responses.createErrorResponse(HttpStatus.BAD_REQUEST, modelState);
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        Account account = accountService.getById(accountId);
        return Responses.createResponse(HttpStatus.OK, account);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createAccount(@RequestBody final AccountCreateRequest request) {
        Account account = accountService.create(1L, request.getName());

        return Responses.createResponse(HttpStatus.CREATED, account);
    }

    @RequestMapping(value = "/errors", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createErrors(@RequestBody final AccountCreateRequest request) {
        ModelState modelState = new ModelState();
        modelState.add("name", "too short", "bad choice", "i don't like it");

        return Responses.createErrorResponse(HttpStatus.CONFLICT, modelState);
    }
}
