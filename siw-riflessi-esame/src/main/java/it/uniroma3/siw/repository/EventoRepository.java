package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Evento;

public interface EventoRepository extends CrudRepository<Evento,Long>{

	public Optional<List<Evento>> findByNome(String nome);
}
