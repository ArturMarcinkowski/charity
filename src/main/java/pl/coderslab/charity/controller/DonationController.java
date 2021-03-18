package pl.coderslab.charity.controller;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class DonationController {

    public CategoryRepository categoryRepository;
    public InstitutionRepository institutionRepository;
    public DonationRepository donationRepository;

    public DonationController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @GetMapping("/form")
    public String formDonation(Model model) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "donation/form";
    }

    @PostMapping("/form")
    public String donationForm(@Valid Donation donation, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "donation/form";
        }
        session.setAttribute("donation", donation);
        return "donation/check";
    }

//    @GetMapping("/check")
//    public String formCheck(Model model, HttpSession session){
////        model.addAttribute("donation", session.getAttribute("donation"));
//        return "check";
//    }

    @PostMapping("/check")
    public String checkForm(HttpSession session) {
        donationRepository.save((Donation) session.getAttribute("donation"));
        return "donation/form-confirmation";
    }
}
