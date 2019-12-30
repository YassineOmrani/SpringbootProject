package com.yassine.app.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DisplayController {
	
	@RequestMapping("/loginAs")
	public String displayHome(Model model) {
		return "loginAs";
	}
	
	
}
