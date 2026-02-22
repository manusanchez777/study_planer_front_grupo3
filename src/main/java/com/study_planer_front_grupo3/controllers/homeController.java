package com.study_planer_front_grupo3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class homeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            RedirectAttributes redirectAttrs,
            Model model) {

        // Validación básica
            boolean hasError = false;

            if (name == null || name.trim().length() < 2) {
                model.addAttribute("errorName", "Introduce un nombre válido.");
                hasError = true;
            }

            if (email == null || !email.contains("@") || email.length() < 5) {
                model.addAttribute("errorEmail", "Introduce un email válido.");
                hasError = true;
            }

            if (password == null || password.length() < 6) {
                model.addAttribute("errorPassword", "La contraseña debe tener al menos 6 caracteres.");
                hasError = true;
            }

            if (password != null && !password.equals(confirmPassword)) {
                model.addAttribute("errorConfirmPassword", "Las contraseñas no coinciden.");
                hasError = true;
            }

            if (hasError) {
                // conservar valores introducidos salvo contraseñas
                model.addAttribute("name", name);
                model.addAttribute("email", email);
                return "register";
            }

            // Aquí podrías guardar el usuario en la base de datos.
            // De momento redirigimos al login con un parámetro de éxito.
            redirectAttrs.addFlashAttribute("message", "Registro exitoso. Puedes iniciar sesión.");
            return "redirect:/login";
    }
}
