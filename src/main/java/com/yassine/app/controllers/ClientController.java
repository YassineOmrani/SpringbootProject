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

import com.yassine.app.entities.Agence;
import com.yassine.app.entities.Client;
import com.yassine.app.entities.Contrat;
import com.yassine.app.entities.Logement;
import com.yassine.app.repository.AgenceRepository;
import com.yassine.app.repository.ClientRepository;
import com.yassine.app.repository.ContratRepository;
import com.yassine.app.repository.LogementRepository;

@Controller
@RequestMapping(value="/client")
@Scope("session")
public class ClientController {
	
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	LogementRepository log;
	@Autowired
	ContratRepository con;
	@Autowired
	AgenceRepository age;
	
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
		Page<Logement> liste=log.findByDispo(true,PageRequest.of(p,5));
		model.addAttribute("page_logement", liste);
		int nbPages = liste.getTotalPages();
		int pages[] = new int[nbPages];
		for(int i=0;i<nbPages; i++)
			pages[i]=i;
		model.addAttribute("pages", pages);
		model.addAttribute("id",request.getSession().getAttribute("id"));
		
		return "Logements";
	}
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "home";
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
				request.getSession().setAttribute("id", client1.get().getId());
				return "redirect:Affichage";
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
	@RequestMapping(value="/location")
	public String pageLocation(Model model,HttpServletRequest request,@RequestParam(name="id_logement",defaultValue="0")Long id) {
		Contrat c1=new Contrat();
		Logement l1=log.findById(id).get();
		c1.setLogement(l1);
		c1.setClient(clientRepo.findById((Long)request.getSession().getAttribute("id")).get());
		Agence a1=age.findById(l1.getIdAgence()).get();
		c1.setAgence(a1);
		model.addAttribute("contrat",c1);
		return "location";
	}
	@RequestMapping(value="/contrat", method=RequestMethod.POST)
	public String contrat(Model model,Contrat c1) {
		if(String.valueOf(c1.getDuree())=="") {
			return "redirect:location";
		}
		else {
			c1.getLogement().setDispo(false);
			c1.getLogement().setIdClient(c1.getClient().getId());
			con.save(c1);
			return "redirect:Affichage";
		}
	}
	
	
}
