package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Piatto;
import it.uniroma3.siw.repository.PiattoRepository;

@Service
public class PiattoService {
	
	@Autowired
	private PiattoRepository piattoRepository; 
	
	
	@Transactional
	public void removePiatto(Long id) {
		piattoRepository.deleteById(id);
	}
	
	@Transactional
	public Piatto savePiatto(Piatto piatto) {
		return piattoRepository.save(piatto);
	}
	
	@Transactional
	public List<Piatto> piattiPerNome(String nome) {
		return piattoRepository.findByNome(nome);
	}
	
	@Transactional
	public List<Piatto> getAllPiatti() {
		return (List<Piatto>) piattoRepository.findAll();
	}
	
	@Transactional
	public Piatto piattoPerId(Long id) {
		Optional<Piatto> optional = piattoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public boolean alreadyExists(Piatto piatto) {
		List<Piatto> piatti = this.piattoRepository.findByNome(piatto.getNome());
		if (piatti.size() > 0)
			return true;
		else 
			return false;
	}
}
