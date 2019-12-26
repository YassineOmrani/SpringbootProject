package com.yassine.app.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yassine.app.entities.Client;
import com.yassine.app.repository.*;
import com.yassine.app.entities.*;

@Controller
@RequestMapping(value="/agence")
@Scope("session")
public class AgenceController {
	
	@Autowired
	AgenceRepository agenceRepo;
	
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
				return "wellcome";
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

	
}
