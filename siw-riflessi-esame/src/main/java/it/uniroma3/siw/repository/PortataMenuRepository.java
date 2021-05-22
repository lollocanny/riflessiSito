package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.PortataMenu;

public interface PortataMenuRepository extends CrudRepository<PortataMenu,Long>{

	public Optional<List<PortataMenu>> findByNomePortata(String nomePortata);
}
