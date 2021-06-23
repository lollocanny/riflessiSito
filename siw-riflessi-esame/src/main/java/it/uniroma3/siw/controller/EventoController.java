package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Evento;
import it.uniroma3.siw.service.CaricaFile;
import it.uniroma3.siw.service.EventoService;
import it.uniroma3.siw.service.MvcConfig;
import it.uniroma3.siw.validator.EventoValidator;

@Controller
public class EventoController {
	
	
	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private EventoValidator eventoValidator;
	
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/evento/{id}")
	public String getEvento(@PathVariable("id") Long id, Model model) {

		logger.debug("getEvento");
		try {
			Evento e = eventoService.eventoPerId(id);
			model.addAttribute("evento", e);


			return "eventi.html";

		} catch (NoSuchElementException e)
		{
			return "error";
		}
	}
	
    @RequestMapping(value = "/evento", method = RequestMethod.GET)
    public String getEventi(Model model) {
    		model.addAttribute("eventi", this.eventoService.getAllEventi());
    		return "eventi.html";
    }
	
	@RequestMapping(value="/admin/aggiungiEvento", method=RequestMethod.GET)
	public String aggiungiEvento(Model model) {
		model.addAttribute("evento", new Evento());
		return "aggiungiEvento.html";
	}
	
	@RequestMapping(value="/admin/aggiungiEvento", method=RequestMethod.POST)
	public String saveEvento(@Valid @ModelAttribute Evento evento,
			@RequestParam("foto") MultipartFile multipartFile,
			BindingResult bindingResult , Model model) throws IOException {
		
		
		this.eventoValidator.validate(evento, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "aggiungiEvento";
		}
		
		else {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			
			evento.setImmagine(fileName);
			
			model.addAttribute(evento);
			eventoService.saveEvento(evento);
			
			CaricaFile.saveFile(MvcConfig.imagesPath, fileName, multipartFile);
			
		}
		
		model.addAttribute("evento",evento);
		return "gestisci";
	}
		
	@RequestMapping(value="/admin/rimuoviEvento", method=RequestMethod.GET)
	public String rimuoviEvento(Model model) {
		model.addAttribute("eventi", this.eventoService.getAllEventi());
		return "rimuoviEvento.html";
	}
	
	@RequestMapping(value = "/admin/rimuoviEvento/{id}/remove", method = RequestMethod.POST)
    public String deleteEvento(@PathVariable long id,
     Model model) {
	 eventoService.removeEvento(id);
	 return "gestisci.html";
    }
	
}
