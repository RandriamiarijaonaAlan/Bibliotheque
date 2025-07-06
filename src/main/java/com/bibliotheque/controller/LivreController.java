package com.bibliotheque.controller;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/livre")
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    @GetMapping("/creation")
    public String formLivre(Model model) {
        model.addAttribute("livre", new Livre());
        return "livre_form";
    }

    @PostMapping("/creation")
    public String saveLivre(@ModelAttribute Livre livre, Model model) {
        livreRepository.save(livre);
        model.addAttribute("message", "Livre enregistré avec succès !");
        return "redirect:/admin/livre/creation";
    }
}

