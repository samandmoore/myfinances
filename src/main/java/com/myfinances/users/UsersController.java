package com.myfinances.users;

import com.myfinances.auth.IAuthService;
import com.myfinances.users.inputs.LoginInput;
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
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("users/login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute LoginInput input, BindingResult result,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (result.hasErrors()) {
            // TODO: show these errors in the view...
            return new ModelAndView("users/login", "", input);
        }

        User user = userService.findByUsernameAndPassword(input.getUsername(), input.getPassword());

        if (user != null) {
            authService.login(request, response, user);
            response.sendRedirect("/");
            return null;
        }

        result.addError(new ObjectError("", "Username/Password invalid."));

        return new ModelAndView("users/login", "", input);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);

        return "redirect:/";
    }
}
