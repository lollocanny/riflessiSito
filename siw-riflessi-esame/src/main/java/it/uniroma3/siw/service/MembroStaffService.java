package it.uniroma3.siw.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.model.MembroStaff;
import it.uniroma3.siw.repository.MembroStaffRepository;


public class MembroStaffService {
	@Autowired
	private MembroStaffRepository membroStaffRepository; 
	
	@Transactional
	public MembroStaff inserisci(MembroStaff membroStaff) {
		return membroStaffRepository.save(membroStaff);
	}
	
	@Transactional
	public List<MembroStaff> membroStaffPerCognome(String cognome) {
		return membroStaffRepository.findByCognome(cognome);
	}
	
	@Transactional
	public List<MembroStaff> tutti() {
		return (List<MembroStaff>) membroStaffRepository.findAll();
	}
	
	@Transactional
	public MembroStaff membroStaffPerId(Long id) {
		Optional<MembroStaff> optional = membroStaffRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public boolean alreadyExists(MembroStaff membroStaff) {
		List<MembroStaff> membriStaff = this.membroStaffRepository.findByCognome(membroStaff.getCognome());
		if (membriStaff.size() > 0)
			return true;
		else 
			return false;
	}
}
