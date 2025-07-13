package com.bibliotheque.util;

import com.bibliotheque.entity.Profil;
import jakarta.servlet.http.HttpSession;

public class SecurityUtils {

    public static boolean isBibliothecaire(HttpSession session) {
        Object userObj = session.getAttribute("user");

        if (userObj instanceof Profil user) {
            String code = user.getCodeAdmin();
            // Vérifie que le code n'est pas "0000" (non-bibliothécaire)
            return code != null && code.matches("\\d{4}") && !code.equals("0000");
        }

        return false;
    }
}
