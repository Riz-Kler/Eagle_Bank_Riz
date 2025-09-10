// src/main/java/org/example/web/RootController.java
package org.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping("/")
    public String index() {
        // springdoc default path
        return "redirect:/swagger-ui.html";
    }
}
