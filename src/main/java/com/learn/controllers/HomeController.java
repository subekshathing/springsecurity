package com.learn.controllers;

import com.learn.models.User;
import com.learn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class HomeController {
    @GetMapping("/")
    String home(){
        return "login";
    }

    @GetMapping("/userPage")
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER') ")
    String userPage(){
        return "home";
    }

    @GetMapping("/logout")
    String logoutPage(){
        return "redirect:/";
    }
}
