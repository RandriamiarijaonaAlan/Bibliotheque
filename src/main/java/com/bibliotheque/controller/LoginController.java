package com.bibliotheque.controller;

import com.bibliotheque.dto.LoginDTO;
import com.bibliotheque.entity.Profil;
import com.bibliotheque.repository.ProfilRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private ProfilRepository profilRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute LoginDTO loginDTO, Model model, HttpSession session) {
        Profil profil = profilRepository.findByEmail(loginDTO.getEmail());

        if (profil == null || !profil.getMotDePasse().equals(loginDTO.getMotDePasse())) {
            model.addAttribute("errorMessage", "Email ou mot de passe incorrect");
            return "login";
        }

        // Authentification réussie, on stocke l'utilisateur en session
        session.setAttribute("user", profil);
        return "redirect:/accueil";  // page après connexion
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
