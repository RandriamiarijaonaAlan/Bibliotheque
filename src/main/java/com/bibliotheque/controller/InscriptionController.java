package com.bibliotheque.controller;

import com.bibliotheque.dto.InscriptionDTO;
import com.bibliotheque.repository.TypeAdherentRepository;
import com.bibliotheque.service.InscriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inscription")
public class InscriptionController {

    @Autowired private TypeAdherentRepository typeRepo;
    @Autowired private InscriptionService inscriptionService;

    /* ----------- Formulaire GET ----------- */
    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("inscriptionDTO", new InscriptionDTO());
        model.addAttribute("types", typeRepo.findAll());
        return "inscription";
    }

    /* ----------- Soumission POST ----------- */
    @PostMapping
    public String submitForm(@Valid @ModelAttribute InscriptionDTO inscriptionDTO,
                             BindingResult br,
                             Model model) {
        if (br.hasErrors()) {
            model.addAttribute("types", typeRepo.findAll());
            return "inscription";
        }

        String code = inscriptionService.inscrire(inscriptionDTO);
        if (code != null) {           // Bibliothécaire
            model.addAttribute("codeAdmin", code);
        }
        return "inscription_succes";
    }
}
