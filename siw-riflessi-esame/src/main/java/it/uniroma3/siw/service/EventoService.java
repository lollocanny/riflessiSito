package it.uniroma3.siw.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Evento;
import it.uniroma3.siw.repository.EventoRepository;

@Service
public class EventoService {
	
	@Autowired
	private EventoRepository eventoRepository; 
	
	@Transactional
	public void saveArtista(Evento a) {
		eventoRepository.save(a);
	}
	
	@Transactional
	public void removeEvento(Long id) {
		eventoRepository.deleteById(id);
	}
	
	@Transactional
	public Evento getEvento(Long id) throws NoSuchElementException {
		return eventoRepository.findById(id).get();
	}
	
	
	@Transactional
	public List<Evento> getAllEventi() {
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
