package com.myfinances.home;

import com.myfinances.auth.IAuthService;
import com.myfinances.home.viewmodels.HomeViewModel;
import com.myfinances.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    IAuthService authService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        HomeViewModel viewModel = getHomeViewModel(request);

        return new ModelAndView("home/index", "model", viewModel);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView index(@RequestParam String name, HttpServletRequest request) {


        return new ModelAndView("home/index", "model", getHomeViewModel(request));
    }

    private HomeViewModel getHomeViewModel(HttpServletRequest request) {
        final HomeViewModel viewModel;

        if (authService.isLoggedIn(request)) {
            User user = authService.getCurrentUser(request);
            viewModel = new HomeViewModel(user);
        } else {
            viewModel = new HomeViewModel();
        }

        return viewModel;
    }
}
