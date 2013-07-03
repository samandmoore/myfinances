package com.myfinances.home;

import com.myfinances.home.viewmodels.HomeViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        HomeViewModel viewModel = new HomeViewModel();

        return new ModelAndView("home/index", "model", viewModel);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView index(@RequestParam String name) {
        HomeViewModel viewModel = new HomeViewModel();

        viewModel.setMessage(String.format("Hello %s, from the server side.", name));

        return new ModelAndView("home/index", "model", viewModel);
    }
}
