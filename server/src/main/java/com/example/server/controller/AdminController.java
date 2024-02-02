package com.example.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller handling requests related to the admin panel. Only for testing purpose.
 */
@RestController
@RequestMapping("/admin")

public class AdminController {

    /**
     * Handles GET requests to "/admin".
     *
     * @return A message indicating that this is the admin panel.
     */
    @GetMapping
    public String hello() {
        return "This is admin panel!";
    }

}