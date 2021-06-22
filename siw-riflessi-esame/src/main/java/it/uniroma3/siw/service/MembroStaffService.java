package it.uniroma3.siw.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.MembroStaff;
import it.uniroma3.siw.repository.MembroStaffRepository;

@Service
public class MembroStaffService {
	
	@Autowired
	private MembroStaffRepository membroStaffRepository; 
	
	@Transactional
	public void saveMembroStaff(MembroStaff membroStaff) {
		membroStaffRepository.save(membroStaff);
	}
	
	@Transactional
	public void removeMembroStaff(Long id) {
		membroStaffRepository.deleteById(id);
	}
	
	@Transactional
	public MembroStaff getMembroStaff(Long id) throws NoSuchElementException {
		return membroStaffRepository.findById(id).get();
	}

	
	@Transactional
	public List<MembroStaff> getAllMembriStaff() {
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
		List<MembroStaff> membriStaff = this.membroStaffRepository.findByNome(membroStaff.getNome());
		if (membriStaff.size() > 0)
			return true;
		else 
			return false;
	}
}
