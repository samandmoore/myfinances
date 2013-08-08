package com.myfinances.sessions;

import com.myfinances.auth.IAuthService;
import com.myfinances.users.IUserService;
import com.myfinances.users.User;
import com.myfinances.sessions.inputs.LoginInput;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/sessions")
public class SessionsController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("sessions/login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute LoginInput input, BindingResult result,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (result.hasErrors()) {
            // TODO: show these errors in the view...
            return new ModelAndView("session/login", "", input);
        }

        User user = userService.findByUsernameAndPassword(input.getUsername(), input.getPassword());

        if (user != null) {
            userService.recordLogin(user);
            authService.login(request, response, user);
            response.sendRedirect("/");
            return null;
        }

        result.addError(new ObjectError("", "Username/Password invalid."));

        return new ModelAndView("sessions/login", "", input);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);

        return "redirect:/";
    }
}
