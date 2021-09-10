package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.model.User;
import spring.service.IRoleService;
import spring.service.IUserService;

@Controller
public class HomeController {
    @Autowired
    IUserService userService;
    @Autowired
    IRoleService roleService;
    @GetMapping("/")
    public ModelAndView getHome() {
        return new ModelAndView("/home", "username", userService.getPrincipal().getUsername());
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return new ModelAndView("/login", "user", new User());
    }

    @PostMapping("/signup")
    public ModelAndView createUser(@ModelAttribute("user") User user, @RequestParam("repass") String repass) {
        if (!user.getPassword().equals(repass)) {
            return new ModelAndView("redirect:/login");
        }
        ModelAndView modelAndView = new ModelAndView("/login");
        user.setRole(roleService.findById(2L).get());
        userService.save(user);
        return modelAndView;
    }
}
