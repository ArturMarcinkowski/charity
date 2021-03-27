package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/institution")
public class InstitutionController {

    private final InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/settings")
    public String formInstitution(Model model, @RequestParam int id) {
        Optional<Institution> institution = institutionRepository.findById(id);
        if (institution.isPresent()) {
            model.addAttribute("institution", institution.get());
            model.addAttribute("inscription", "EDIT");
            return "institution/form";
        }
        return "redirect:/#section4";
    }

    @PostMapping("/settings")
    public String institutionForm(@Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return "institution/form";
        }
        institutionRepository.save(institution);
        return "redirect:/#section4";
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam int id) {
        Optional<Institution> institution = institutionRepository.findById(id);
        if (institution.isPresent()) {
            model.addAttribute("institution", institution.get());
            return "institution/delete";
        }
        return "redirect:/#section4";
    }

    @GetMapping("/delete/confirm")
    public String deletePost(@RequestParam int id) {
        Optional<Institution> institution = institutionRepository.findById(id);
        if (institution.isPresent()) {
            institutionRepository.delete(institution.get());
            return "institution/delete-confirm";
        }
        return "redirect:/#section4";
    }

    @GetMapping("/add")
    public String addInstitution(Model model) {
        model.addAttribute("institution", new Institution());
        model.addAttribute("inscription", "ADD");
        return "institution/form";
    }

    @PostMapping("/add")
    public String institutionAdd(@Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return "institution/form";
        }
        institutionRepository.save(institution);
        return "redirect:/#section4";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        return "institution/list";
    }

}
