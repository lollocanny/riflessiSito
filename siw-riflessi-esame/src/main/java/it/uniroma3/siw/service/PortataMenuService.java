package it.uniroma3.siw.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.PortataMenu;
import it.uniroma3.siw.repository.PortataMenuRepository;

@Service
public class PortataMenuService {
	
	@Autowired
	private PortataMenuRepository portataMenuRepository; 
	
	@Transactional
	public PortataMenu inserisci(PortataMenu portataMenu) {
		return portataMenuRepository.save(portataMenu);
	}
	
	@Transactional
	public List<PortataMenu> portataMenuPerNome(String nomePortata) {
		return portataMenuRepository.findByNomePortata(nomePortata);
	}

	@Transactional
	public List<PortataMenu> getAllPortataMenu() {
		return (List<PortataMenu>) portataMenuRepository.findAll();
	}
	
	@Transactional
	public boolean alreadyExists(PortataMenu portataMenu) {
		List<PortataMenu> portateMenu = this.portataMenuRepository.findByNomePortata(portataMenu.getNomePortata());
		if (portateMenu.size() > 0)
			return true;
		else 
			return false;
	}
}
