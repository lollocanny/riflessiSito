package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Evento;
import it.uniroma3.siw.service.EventoService;

@Component
public class EventoValidator implements Validator {
	
	final Integer MAX_NAME_LENGTH = 100;
	final Integer MIN_NAME_LENGTH = 2;
	
	@Autowired
	private EventoService eventoService;

	@Override
	public void validate(Object o, Errors errors) {
		
		Evento evento = (Evento) o;
		String nome = evento.getNome().trim();
		String data = evento.getData().trim();
				
		
		if (nome==null || nome.trim().isEmpty())
			   errors.rejectValue("nome", "required");
		else if(nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
			errors.rejectValue("nome", "size");
		  else if(this.eventoService.alreadyExists(evento))
		   errors.rejectValue("nome", "duplicate");
		
		if (data==null || data.trim().isEmpty())
			   errors.rejectValue("data", "required");
		else if(data.length() < MIN_NAME_LENGTH || data.length() > MAX_NAME_LENGTH)
			errors.rejectValue("data", "size");
		
	}
	

	@Override
	public boolean supports(Class<?> clazz) {
		return Evento.class.equals(clazz);
	}
}
