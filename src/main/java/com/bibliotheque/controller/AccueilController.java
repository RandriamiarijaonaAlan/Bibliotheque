package com.bibliotheque.controller;

import com.bibliotheque.entity.Profil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

    @GetMapping("/accueil")
    public String accueil(HttpSession session, Model model) {
        Profil user = (Profil) session.getAttribute("user");
        model.addAttribute("user", user);
        return "accueil";
    }
}
