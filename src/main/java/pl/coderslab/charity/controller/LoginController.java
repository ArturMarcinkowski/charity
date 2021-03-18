package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.SendEmail;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LoginController {


    UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }



    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/";
    }



    @GetMapping("/password-reminder")
    public String passwordReminder(@RequestParam String username, Model model) {
        Optional<User> optionalUser = userService.findByUserName(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.getEmail() != null){
//                model.addAttribute("message", SendEmail.send(user.getEmail()));
                model.addAttribute("message", SendEmail.send("takiotaku@gmail.com"));
            }
            else {
                model.addAttribute("message", "podany użytkonik nie posiada adresu email");
            }
        }
        else {
            model.addAttribute("message", "użytkonik o podanym loginie nie istnieje");
        }
        return "/login/password-send-confirm";
    }

}
