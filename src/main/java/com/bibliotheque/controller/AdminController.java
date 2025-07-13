package com.bibliotheque.controller;

import com.bibliotheque.entity.Profil;
import com.bibliotheque.repository.ProfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProfilRepository profilRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "admin_login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("code") String code, Model model) {
        Optional<Profil> optProfil = profilRepository.findByCodeAdmin(code);

        if (optProfil.isPresent()) {
            Profil profil = optProfil.get();
            // Vérifier que le profil correspond à un bibliothécaire
            // Par exemple, si tu as un type ou rôle dans Profil (à adapter)
            // Sinon on considère ici que la présence du code_admin suffit
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("erreur", "Code admin incorrect.");
            return "admin_login";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin_dashboard";
    }
}
