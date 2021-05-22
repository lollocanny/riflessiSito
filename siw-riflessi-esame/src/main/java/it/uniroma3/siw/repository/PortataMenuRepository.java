package it.uniroma3.siw.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.PortataMenu;

public interface PortataMenuRepository extends CrudRepository<PortataMenu,Long>{

	public List<PortataMenu> findByNomePortata(String nomePortata);
}
