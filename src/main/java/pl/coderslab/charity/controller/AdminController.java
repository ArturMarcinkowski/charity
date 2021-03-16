package pl.coderslab.charity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {


    UserService userService;
    UserRepository userRepository;
    RoleRepository roleRepository;

    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    @GetMapping("/list")
    public String settings(Model model) {
        Role role = roleRepository.findByName("ROLE_ADMIN");
        List<User> admins = userService.findAllByRolesContains(role);
        model.addAttribute("admins", admins);
        return "users/admin-list";
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
        user.setRoles(roleRepository.findAllByName("ROLE_ADMIN"));
        userService.saveAdmin(user);
        return "redirect:/";
    }



}
