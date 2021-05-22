package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.model.Evento;
import it.uniroma3.siw.repository.EventoRepository;


public class EventoService {
	@Autowired
	private EventoRepository eventoRepository; 
	
	@Transactional
	public Evento inserisci(Evento evento) {
		return eventoRepository.save(evento);
	}
	
	@Transactional
	public List<Evento> eventoPerNome(String nome) {
		return eventoRepository.findByNome(nome);
	}
	
	@Transactional
	public List<Evento> tutti() {
		return (List<Evento>) eventoRepository.findAll();
	}
	
	@Transactional
	public Evento eventoPerId(Long id) {
		Optional<Evento> optional = eventoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public boolean alreadyExists(Evento evento) {
		List<Evento> eventi = this.eventoRepository.findByNome(evento.getNome());
		if (eventi.size() > 0)
			return true;
		else 
			return false;
	}
}
