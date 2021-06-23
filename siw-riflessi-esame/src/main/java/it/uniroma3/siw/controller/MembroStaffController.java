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

import it.uniroma3.siw.model.MembroStaff;
import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.service.CaricaFile;
import it.uniroma3.siw.service.MembroStaffService;
import it.uniroma3.siw.service.MvcConfig;
import it.uniroma3.siw.validator.MembroStaffValidator;

@Controller
public class MembroStaffController {
	
	 @Autowired
	 private MembroStaffValidator membroStaffValidator;
	 
	 @Autowired
	 private MembroStaffService membroStaffService;
	 
	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/membroStaff/{id}")
	public String getMembro(@PathVariable("id") Long id, Model model) {

		logger.debug("getMembro");
		try {
			 MembroStaff ms = membroStaffService.getMembroStaff(id);
			model.addAttribute("membroStaff", ms);


			return "staff.html";

		} catch (NoSuchElementException e)
		{
			return "error";
		}
	}
	
    @RequestMapping(value = "/membroStaff", method = RequestMethod.GET)
    public String getMembriStaff(Model model) {
    		model.addAttribute("membriStaff", this.membroStaffService.getAllMembriStaff());
    		return "staff.html";
    }
	
	
	@RequestMapping(value="/admin/aggiungiMembroStaff", method=RequestMethod.GET)
	public String aggiungiMembroStaff(Model model) {
		model.addAttribute("membroStaff", new MembroStaff());
		return "aggiungiMembroStaff.html";
	}
		
	@RequestMapping(value="/admin/aggiungiMembroStaff", method=RequestMethod.POST)
	public String saveMembroStaff(@Valid @ModelAttribute MembroStaff membroStaff,
			@RequestParam("foto") MultipartFile multipartFile,
			BindingResult bindingResult , Model model) throws IOException {
		
		this.membroStaffValidator.validate(membroStaff, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "aggiungiMembroStaff";
		}
		
		else {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			
			membroStaff.setImmagine(fileName);
			
			model.addAttribute(membroStaff);
			membroStaffService.saveMembroStaff(membroStaff);
			
			CaricaFile.saveFile(MvcConfig.imagesPath, fileName, multipartFile);
			
		}
		
		model.addAttribute("membroStaff",membroStaff);
		return "gestisci";
	}
	
	@RequestMapping(value="/admin/rimuoviMembroStaff", method=RequestMethod.GET)
	public String rimuoviMembroStaff(Model model) {
		model.addAttribute("membriStaff", this.membroStaffService.getAllMembriStaff());
		return "rimuoviMembroStaff.html";
	}
	
	
	@RequestMapping(value = "/admin/rimuoviMembroStaff/{id}/remove", method = RequestMethod.POST)
    public String deleteMembroStaff(@PathVariable long id,
     Model model) {
	 membroStaffService.removeMembroStaff(id);
	 return "gestisci.html";
    }
		
	
}
