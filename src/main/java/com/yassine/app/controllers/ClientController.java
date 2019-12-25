package com.yassine.app.controllers;

import com.yassine.app.entities.Client;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yassine.app.repository.ClientRepository;

@Controller
@RequestMapping(value="/client")
@Scope("session")
public class ClientController {
	@Autowired
	ClientRepository clientRepo;
	
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
			clientRepo.save(c);
			return "loginClient";
		}
	}
	
	
	@RequestMapping(value="/login")
	public String loginClient(Model model) {
		Client c1 = new Client();
		model.addAttribute("client",c1);
		return "loginClient";
	}
	
	@RequestMapping(value="/authClient")
	public String authClient(Model model,Client client) {
		Optional<Client> client1 = clientRepo.findByEmail(client.getEmail());
		if (client1.isPresent() && client1.get().getPassword().compareTo(client.getPassword()) == 0 ) {
			model.addAttribute("currentClient", client1);
			return "wellcome";
		} else {
			Client client2 = new Client();
			model.addAttribute("client",client2);
			return "loginClient";
		}
	}
	
	
}
