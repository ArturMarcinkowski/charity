package pl.coderslab.charity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileController {


    UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String profile() {
        return "profile";
    }

    @GetMapping("/settings")
    public String settings(Model model, Authentication authentication) {
        if(authentication != null) {
            model.addAttribute("user", userService.findByUserName(authentication.getName()));
            return "settings";
        }
        else{
            return "redirect:/login";
        }

    }

    @PostMapping("/settings")
    public String settingsPost(@Valid User user, BindingResult result, Authentication authentication){
        if (result.hasErrors()) {
            return "settings";
        }
        User oldUser = userService.findByUserName(authentication.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setSurname(user.getSurname());
        userService.saveUser(oldUser);
        return "redirect:/";
    }

}
