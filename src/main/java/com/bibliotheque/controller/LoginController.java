package com.bibliotheque.controller;

import com.bibliotheque.entity.Profil;
import com.bibliotheque.repository.ProfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController  {

    @Autowired
    private ProfilRepository profilRepository;

    // Affiche le formulaire de login
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // rend le template login.html
    }

    // Traite le formulaire de login
    @PostMapping("/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("motDePasse") String motDePasse,
            Model model
    ) {
        Optional<Profil> optionalProfil = profilRepository.findByEmail(email);

        if (optionalProfil.isPresent()) {
            Profil profil = optionalProfil.get();

            // Vérification simple mot de passe (en clair, à sécuriser en prod)
            if (profil.getMotDePasse().equals(motDePasse)) {
                // Connexion OK
                // TODO : enregistrer en session si besoin

                return "redirect:/accueil";  // redirection vers la page d'accueil
            }
        }

        // En cas d'erreur, on renvoie au formulaire avec message
        model.addAttribute("error", "Email ou mot de passe incorrect");
        return "login";
    }
}
