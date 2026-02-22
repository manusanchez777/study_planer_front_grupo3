package com.study_planer_front_grupo3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
} 
/*comentario: Este código define un controlador de Spring Boot que maneja las solicitudes GET a la raíz ("/") de la aplicación. Cuando se accede a esta ruta, el método `home()` devuelve el nombre de la vista "index", que se espera que sea un archivo HTML o una plantilla que se renderizará para el usuario.*/ 