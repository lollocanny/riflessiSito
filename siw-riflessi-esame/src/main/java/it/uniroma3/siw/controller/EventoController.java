package it.uniroma3.siw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.model.Evento;
import it.uniroma3.siw.service.EventoService;
import it.uniroma3.siw.service.PortataMenuService;

@Controller
public class EventoController {
	
	
	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private PortataMenuService portataMenuService;
	
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@RequestMapping(value = {"evento/visualizzaEventi"}, method= RequestMethod.GET)
	public String visualizzaEventi(Model model) {
		logger.debug("visualizzaEventi");
		return "eventi.html";
	}
	
	@RequestMapping(value="/admin/aggiungiEvento", method=RequestMethod.GET)
	public String aggiungiEvento(Model model) {
		model.addAttribute("evento", new Evento());
		model.addAttribute("portate", portataMenuService.getAllPortataMenu());
		return "aggiungiEvento.html";
	}
		
	
}
