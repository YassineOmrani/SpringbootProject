package com.yassine.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yassine.app.entities.Logement;
import com.yassine.app.repository.LogementRepository;

@Controller
public class LogementController {
	
	@Autowired
	LogementRepository logementRepo;
		
	@RequestMapping(value = "/details")
	public String delete_logement(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "id_logement", defaultValue = "0") int id, RedirectAttributes ra) {
		Logement l = logementRepo.getOne( (long) id );
		model.addAttribute("logement",l);
		ra.addAttribute("page", p);
		return "logementDetailPage";
	}
	
	
}
