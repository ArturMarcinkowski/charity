package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.utils.SendEmail;
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

//    @PostMapping("/register")
//    public String addUser(@Valid User user, BindingResult result) {
//        if (result.hasErrors()) {
//            return "register";
//        }
//        userService.saveUser(user);
//        return "redirect:/";
//    }

    @PostMapping("/register")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.registerUser(user);
        String key = userService.generateEmailChangeKey(user);
        model.addAttribute("message", SendEmail.registerConfirm(user.getEmail(), key, user.getUsername()));
        return "/login/password-send-confirm";
    }

    @GetMapping("/account-activate")
    public String activate(@RequestParam String key, @RequestParam String name, Model model) {
        Optional<User> optionalUser = userService.findByUserName(name);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(userService.tryActivateAccount(user.getId(), key)){
                model.addAttribute("message", "Konto zostało aktywowane");
                return "/login/password-send-confirm";
            }
            model.addAttribute("message", "link wygasł");
        }else {
            model.addAttribute("message", "użytkownika nie znaleziono");
        }
        return "/login/password-send-confirm";
    }


    @GetMapping("/password-reminder")
    public String passwordReminderGet() {
        return "/login/password-send";
    }

    @PostMapping("/password-reminder")
    public String passwordReminder(@RequestParam String email, Model model) {
        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isPresent()) {
            String key = userService.generateEmailChangeKey(optionalUser.get());
            model.addAttribute("message", SendEmail.passwordChange(email, key, optionalUser.get().getUsername()));
        } else {
            model.addAttribute("message", " podany email nie istnieje w bazie danych");
        }
        return "/login/password-send-confirm";
    }

    @GetMapping("/password-reset")
    public String passwordReset(@RequestParam String key, @RequestParam String name, Model model) {
        Optional<User> optionalUser = userService.findByUserName(name);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(userService.isEmailKeyValid(user, key)){
                model.addAttribute("id", user.getId());
                return "/login/password-reset";
            }
            model.addAttribute("message", "link wygasł");
        }else {
            model.addAttribute("message", "użytkownika nie znaleziono");
        }
        return "/login/password-send-confirm";
    }

    @PostMapping("/password-reset")
    public String passwordReset(@RequestParam String password, @RequestParam String password2, @RequestParam int id, Model model) {
        Optional<User> optionalUser = userService.findById(id);
        if(optionalUser.isPresent()) {
            if (password.equals(password2)) {
                userService.changePassword(password, optionalUser.get());
                return "redirect:/login#password-form";
            }
            model.addAttribute("message", "hasła się nie pokrywają");
            return "password-reset";
        }
        model.addAttribute("message", "użytkownika nie znaleziono");
        return "/login/password-send-confirm";
    }
}
