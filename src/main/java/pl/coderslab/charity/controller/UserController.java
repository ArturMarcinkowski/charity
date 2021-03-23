package pl.coderslab.charity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {


    UserService userService;
    RoleRepository roleRepository;
    UserRepository userRepository;

    public UserController(UserService userService, RoleRepository roleRepository, UserRepository userRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/list")
    public String settings(Model model) {
        Role role = roleRepository.findByName("ROLE_ADMIN");
        List<User> users = userService.findAllByRolesContainsNot(role);
        model.addAttribute("users", users);
        return "users/user-list";
    }

    @PostMapping("/edit")
    public String settingsPost(@Valid User user, BindingResult result){
        if (result.hasErrors()) {
            return "users/edit";
        }
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String settings(Model model, @RequestParam int id) {
        Optional<User> user = userService.findById(id);
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
            return "users/edit";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam int id){
        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            model.addAttribute("user", user.get());
            return "users/delete";
        }
        return "redirect:/";
    }

    @GetMapping("/delete/confirm")
    public String deletePost(@RequestParam int id){
        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            userService.deleteUser(id);
            return "users/delete-confirm";
        }
        return "redirect:/";
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "users/form";
    }

    @PostMapping("/add")
    public String addForm(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "users/form";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/block")
    public String block(@RequestParam int id) {
        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            userService.blockUser(user.get());
        }
        return "redirect:/user/list#user-list";
    }

    @GetMapping("/unblock")
    public String unblock(@RequestParam int id) {
        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            userService.unblockUser(user.get());
        }
        return "redirect:/user/list#user-list";
    }


}
