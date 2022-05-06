package com.pavan.projects.tambola.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	
	@RequestMapping("/homepage")
	public String home() {
		System.out.println("home");
		return "home";
	}
	
}
