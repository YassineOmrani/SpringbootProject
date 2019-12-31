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
	public String displayLogin(Model model, HttpServletRequest request) {
		if ( request.getSession().getAttribute("idClient") != null) {
			return "redirect:client/Affichage";
		}else if (request.getSession().getAttribute("idAgence") != null) {
			return "redirect:agence/Affichage";
		}else
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
		
		
			//	Get the liste of logement with adress like the entered text in search bar 
			Page<Logement> liste = logementRepo.findBy3(l.getAdress(),l.getType(),l.getPrix(), PageRequest.of(p, 6));
		    

		
		//	Test if liste is not empty
		if (!liste.isEmpty()) {
			model.addAttribute("page_logement", liste);
			int nbPages = liste.getTotalPages();
			int pages[] = new int[nbPages];
			for (int i = 0; i < nbPages; i++)
				pages[i] = i;
			model.addAttribute("pages", pages);
			model.addAttribute("searchError","Please fill in all search parameters");
			return "searchResult";
			
		}else {
			
			return "redirect:/";
		}
		
	}
	
	
}
