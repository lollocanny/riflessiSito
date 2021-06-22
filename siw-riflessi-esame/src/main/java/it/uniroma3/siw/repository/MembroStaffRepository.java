package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.MembroStaff;

public interface MembroStaffRepository extends CrudRepository<MembroStaff,Long>{

	public List<MembroStaff> findByNome(String nome);
}