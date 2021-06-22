package it.uniroma3.siw.controller;

import java.io.IOException;

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

import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.service.CaricaFile;
import it.uniroma3.siw.service.MvcConfig;
import it.uniroma3.siw.service.PiattoService;
import it.uniroma3.siw.validator.PiattoValidator;

@Controller
public class PiattoController {
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	 @Autowired
	 private PiattoValidator piattoValidator;
	 
	 @Autowired
	 private PiattoService piattoService;
	 
	@RequestMapping(value = {"/portataMenu/visualizzaMenu"}, method= RequestMethod.GET)
	public String visualizzaMenu(Model model) {
		logger.debug("visualizzaMenu");
		return "menu.html";
	}
	
	@RequestMapping(value="/admin/aggiungiPiatto", method=RequestMethod.GET)
	public String aggiungiPiatto(Model model) {
		model.addAttribute("piatto", new Piatto());
		return "aggiungiPiatto.html";
	}
		
	@RequestMapping(value="/admin/aggiungiPiatto", method=RequestMethod.POST)
	public String savePiatto(@Valid @ModelAttribute Piatto piatto,
			@RequestParam("foto") MultipartFile multipartFile,
			BindingResult bindingResult , Model model) throws IOException {
		
		this.piattoValidator.validate(piatto, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "aggiungiPiatto";
		}
		
		else {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			
			piatto.setImmagine(fileName);
			
			model.addAttribute(piatto);
			piattoService.savePiatto(piatto);
			
			CaricaFile.saveFile(MvcConfig.imagesPath, fileName, multipartFile);
			
		}
		
		model.addAttribute("piatto",piatto);
		return "gestisci";
	}
	
	@RequestMapping(value="/admin/rimuoviPiatto", method=RequestMethod.GET)
	public String rimuoviPiatto(Model model) {
		model.addAttribute("piatti", this.piattoService.getAllPiatti());
		return "rimuoviPiatto.html";
	}
	
	
	@RequestMapping(value = "/admin/rimuoviPiatto/{id}/remove", method = RequestMethod.POST)
    public String deletePiatto(@PathVariable long id,
     Model model) {
	 piattoService.removePiatto(id);
	 return "gestisci.html";
    }
}
