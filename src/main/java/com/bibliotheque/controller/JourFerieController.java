package com.bibliotheque.controller;

import com.bibliotheque.entity.JourFerie;
import com.bibliotheque.repository.JourFerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/jours-feries")
public class JourFerieController {

    @Autowired
    private JourFerieRepository jourFerieRepository;

    @GetMapping
    public String liste(Model model) {
        model.addAttribute("joursFeries", jourFerieRepository.findAll());
        model.addAttribute("jourFerie", new JourFerie());
        return "jours_feries";
    }

    @PostMapping
    public String ajouter(@ModelAttribute JourFerie jourFerie) {
        jourFerieRepository.save(jourFerie);
        return "redirect:/admin/jours-feries";
    }

    @PostMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        jourFerieRepository.deleteById(id);
        return "redirect:/admin/jours-feries";
    }
}
