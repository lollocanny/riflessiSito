package it.uniroma3.siw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventiController {
	
	
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@RequestMapping(value = {"/visualizzaEventi"}, method= RequestMethod.GET)
	public String visualizzaEventi(Model model) {
		logger.debug("visualizzaEventi");
		return "eventi.html";
	}
	
	
		
	
}
