package com.myfinances.users;

import com.google.common.collect.Maps;
import com.myfinances.auth.IAuthService;
import com.myfinances.http.Responses;
import com.myfinances.users.outputs.UserOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * TODO: documentation
 *
 * @author sam
 * @since 7/27/13 4:19 PM
 */
@Controller
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersEndpoint {

    @Autowired
    private IAuthService authService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCurrentUser(HttpServletRequest request) {
        User user = authService.getCurrentUser(request);

        if (user == null) {
            return Responses.createErrorResponse(HttpStatus.UNAUTHORIZED, new HashMap<String, List<String>>());
        }

        return Responses.createResponse(HttpStatus.OK, UserOutput.fromUser(user));
    }
}
