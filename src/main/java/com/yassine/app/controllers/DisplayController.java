package com.yassine.app.controllers;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yassine.app.entities.Logement;
import com.yassine.app.entities.Search;
import com.yassine.app.repository.LogementRepository;

@Controller
public class DisplayController {
	
	@Autowired
	LogementRepository logementRepo;
	
	@RequestMapping("/loginAs")
	public String displayLogin(Model model) {
		return "loginAs";
	}
	

	
	@RequestMapping(value = "/")
	public String Affichage(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			HttpServletRequest request) {
		
			// To display les logements
			Page<Logement> liste = logementRepo.findAll(PageRequest.of(p, 5));
			model.addAttribute("page_logement", liste);
			int nbPages = liste.getTotalPages();
			int pages[] = new int[nbPages];
			for (int i = 0; i < nbPages; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			
			// Adding Model for searchBar
			Logement l = new Logement();
			model.addAttribute("logement",l);
			
			// Display Home 
			return "home";	
	}
	
	@RequestMapping(value = "/searchLogements")
	public String searchResult
	(
			Model model, 
			@RequestParam(name="page",defaultValue = "0") int p,
			Logement l
	) 
	{
		
		System.out.println(l.getAdress());
		//	Get the list of logement with name Search.text 
		Page<Logement> liste = logementRepo.findByAdress(l.getAdress(), PageRequest.of(p, 6));
		
		
		//	Test if list is not empty
		if (!liste.isEmpty()) {
			model.addAttribute("page_logement", liste);
			int nbPages = liste.getTotalPages();
			int pages[] = new int[nbPages];
			for (int i = 0; i < nbPages; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			
			return "searchResult";
			
		}else {
			
			return "redirect:/";
		}
		
	}
	
	
}
