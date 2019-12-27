package com.yassine.app.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yassine.app.entities.Client;
import com.yassine.app.entities.Logement;
import com.yassine.app.repository.ClientRepository;
import com.yassine.app.repository.LogementRepository;

@Controller
@RequestMapping(value="/client")
@Scope("session")
public class ClientController {
	
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	LogementRepository log;
	
	
	// I declared this just to display home when using thymeleaf th:href{@home}
	@RequestMapping(value="/home")
	public String displayHome(Model model) {
		return "home";
	}
	
	
	@RequestMapping(value="/signup")
	public String signup(Model model) {
		Client c1 = new Client();
		model.addAttribute("client",c1);
		return "signupClient";
	}
	
	@RequestMapping(value="/signupClient", method=RequestMethod.POST)
	public String signupClient(Client c,Model model) {
		if (c.getEmail() == "" || c.getNom() == "" || c.getPassword() == "") {
			Client c1 = new Client();
			model.addAttribute("client",c1);
			return "signupClient";
		}else {
			BCryptPasswordEncoder bCryptPasswordEncoderLocal = new BCryptPasswordEncoder();
			String encodedPassword = bCryptPasswordEncoderLocal.encode(c.getPassword());
			c.setPassword(encodedPassword);
			clientRepo.save(c);
			return "loginClient";
		}
	}
	@RequestMapping(value="/Affichage")
	public String Affichage(Model model, @RequestParam(name="page", defaultValue="0")int p,HttpServletRequest request) {
		Page<Logement> liste=log.findAll(PageRequest.of(p,5));
		model.addAttribute("page_logement", liste);
		int nbPages = liste.getTotalPages();
		int pages[] = new int[nbPages];
		for(int i=0;i<nbPages; i++)
			pages[i]=i;
		model.addAttribute("pages", pages);
		
		return "Logements";
	}
	
	
	@RequestMapping(value="/login")
	public String loginClient(Model model) {
		Client c1 = new Client();
		model.addAttribute("client",c1);
		return "loginClient";
	}
	
	@RequestMapping(value="/authClient")
	public String authClient(Model model,Client client,HttpServletRequest request) {
		Optional<Client> client1 = clientRepo.findByEmail(client.getEmail());
		
		if (client1.isPresent()) {

			BCryptPasswordEncoder bCryptPasswordEncoderLocal = new BCryptPasswordEncoder();
			String encodedPassword = bCryptPasswordEncoderLocal.encode(client.getPassword());
			
			// Testing if passwords match
			if (bCryptPasswordEncoderLocal.matches(client.getPassword(), client1.get().getPassword())) {
				model.addAttribute("currentClient", client1);
				request.getSession().setAttribute("userType", "Client");
				return "wellcome";
			}else {			
				Client client2 = new Client();
				model.addAttribute("client",client2);
				return "loginClient";
			}
		} else {
			Client client2 = new Client();
			model.addAttribute("client",client2);
			return "loginClient";
		}
	}
	
	
}
