package com.yassine.app.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yassine.app.entities.Agence;
import com.yassine.app.entities.Logement;
import com.yassine.app.repository.AgenceRepository;
import com.yassine.app.repository.LogementRepository;

@Controller
@RequestMapping(value="/agence")
@Scope("session")
public class AgenceController {
	
	@Autowired
	AgenceRepository agenceRepo;
	@Autowired
	LogementRepository log;
	@RequestMapping(value="/verif")
	public String verif(Model model,HttpServletRequest request, @RequestParam(name="page", defaultValue="0")int p) {
		
		if(request.getSession().getAttribute("userType").equals("Agence")) {
			return "redirect:Affichage";
			
			
		}
		else if (request.getSession().getAttribute("userType").equals("Client")) {
			return "wellcome";
		}
		else {
			return "home";
		}
	}
	@RequestMapping(value="/create")
	public String create(Model model) {
		Logement lovely=new Logement();
		model.addAttribute("Logement",lovely);
		return "create";
		
	}
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Logement l,BindingResult bindingResult,Model model, @RequestParam(name="page", defaultValue="0")int p,HttpServletRequest request) {
		if(bindingResult.hasErrors()) {
			System.out.println("---------- errors = " + bindingResult.getAllErrors());
			model.addAttribute("Logement",l);	
			return "redirect:create";}
			else {
		l.setDispo(true);
		l.setIdAgence((Long)request.getSession().getAttribute("id"));
		log.save(l);
		return "redirect:Affichage";
	}}
	
	@RequestMapping(value="/Affichage")
	public String Affichage(Model model, @RequestParam(name="page", defaultValue="0")int p,HttpServletRequest request) {
		Page<Logement> liste=log.findByIdAgence((Long)(request.getSession().getAttribute("id")),PageRequest.of(p,5));
		model.addAttribute("page_logement", liste);
		model.addAttribute("a",(Long)(request.getSession().getAttribute("id")));
		int nbPages = liste.getTotalPages();
		int pages[] = new int[nbPages];
		for(int i=0;i<nbPages; i++)
			pages[i]=i;
		model.addAttribute("pages", pages);
		
		return "listeLogement";
	}
	@RequestMapping(value="/signup")
	public String signup(Model model) {
		Agence c1 = new Agence();
		model.addAttribute("agence",c1);
		return "signupAgence";
	}
	
	@RequestMapping(value="/signupAgence", method=RequestMethod.POST)
	public String signupAgence(Agence a,Model model) {
		if (a.getEmail() == "" || a.getNom() == "" || a.getPassword() == "") {
			Agence a1 = new Agence();
			model.addAttribute("agence",a1);
			return "signupAgence";
		}else {
			BCryptPasswordEncoder bCryptPasswordEncoderLocal = new BCryptPasswordEncoder();
			String encodedPassword = bCryptPasswordEncoderLocal.encode(a.getPassword());
			a.setPassword(encodedPassword);
			agenceRepo.save(a);
			return "loginAgence";
		}
	}
	
	
	@RequestMapping(value="/login")
	public String agenceClient(Model model) {
		Agence a = new Agence();
		model.addAttribute("agence",a);
		return "loginAgence";
	}
	
	@RequestMapping(value="/authAgence")
	public String authClient(Model model,Agence agence,HttpServletRequest request) {
		Optional<Agence> agence1 = agenceRepo.findByEmail(agence.getEmail());
		
		if (agence1.isPresent()) {
			BCryptPasswordEncoder bCryptPasswordEncoderLocal = new BCryptPasswordEncoder();
			// Testing if passwords match
			if (bCryptPasswordEncoderLocal.matches(agence.getPassword(), agence1.get().getPassword())) {
				model.addAttribute("currentAgence", agence1);
				request.getSession().setAttribute("userType", "Agence");
				request.getSession().setAttribute("id",agence1.get().getId());
				return "redirect:verif";
			}else {
				// Wrong password
				Agence a = new Agence();
				model.addAttribute("agence",a);
				return "loginAgence";
			}
		} else {
			// Not in database
			Agence a = new Agence();
			model.addAttribute("agence",a);
			return "loginAgence";
		}
	}
	@RequestMapping(value="/delete")
	public String delete_logement(Model model, @RequestParam(name="page", defaultValue="0")int p, @RequestParam(name="id_logement", defaultValue="0")int id, RedirectAttributes ra){
		log.deleteById(new Long(id));
		ra.addAttribute("page", p);
		return "redirect:Affichage";
	}
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String ModifierLogement(Model model, @RequestParam(name="page", defaultValue="0")int p,@RequestParam(name="id_logement", defaultValue="1")int id) {
		Optional<Logement> l = log.findById(new Long(id));
		model.addAttribute("Logement", l);	
		model.addAttribute("pg", p);
		return "modify";		
	}
	
	@RequestMapping(value="/saveMod", method=RequestMethod.POST)
	public String modiferLogement(Model model, Logement l, @RequestParam(name="page", defaultValue="0")int p, RedirectAttributes ra, HttpServletRequest request ) {
		l.setDispo(true);
		l.setIdAgence((Long)request.getSession().getAttribute("id"));
		log.save(l);
		ra.addAttribute("page", p);
		return "redirect:Affichage";
	}

	
}
