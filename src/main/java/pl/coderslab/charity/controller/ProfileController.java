package pl.coderslab.charity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    UserService userService;
    DonationRepository donationRepository;
    RoleRepository roleRepository;
    InstitutionRepository institutionRepository;

    public ProfileController(UserService userService, DonationRepository donationRepository, RoleRepository roleRepository, InstitutionRepository institutionRepository) {
        this.userService = userService;
        this.donationRepository = donationRepository;
        this.roleRepository = roleRepository;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/")
    public String profile(Model model, Authentication authentication) {
        if (authentication != null) {
            Optional<User> optionalUser = userService.findByUserName(authentication.getName());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                model.addAttribute("user", user);
                List<Donation> donations = donationRepository.findAllByUserId(user.getId());
                model.addAttribute("bagsCount", donations.stream().mapToInt(Donation::getQuantity).sum());
                model.addAttribute("donationsCount", donations.size());
                model.addAttribute("donations", donations);
                model.addAttribute("userCount", userService.countUsers());
                model.addAttribute("adminCount", userService.countAdmins());
                model.addAttribute("institutionCount", institutionRepository.count());
                return "users/profile";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String settings(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("user", userService.findByUserName(authentication.getName()));
            return "users/edit";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/edit")
    public String settingsPost(@Valid User user, BindingResult result, Authentication authentication) {
        if (result.hasErrors()) {
            return "settings";
        }
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/password-change")
    public String password(Authentication authentication) {
        if (authentication != null) {
            return "users/password-change";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/password-change")
    public String passwordPost(@RequestParam String oldPassword, @RequestParam String newPassword1, @RequestParam String newPassword2, Authentication authentication, Model model) {
        if (authentication != null) {
            User user = userService.findByUserName(authentication.getName()).get();

            if(!userService.testPassword(oldPassword, user)){
                model.addAttribute("message", "has??o jest nieprawd??owe");
                return "users/password-change";
            }
            if(!newPassword1.equals(newPassword2)){
                model.addAttribute("message", "has??a nie s?? identyczne");
                return "users/password-change";
            }
            userService.changePassword(newPassword1, user);
            return "redirect:/profile/";
        }
        return "redirect:/login";
    }

}
