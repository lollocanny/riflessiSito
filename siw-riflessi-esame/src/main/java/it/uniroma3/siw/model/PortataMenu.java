package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PortataMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	
	@Column(nullable=false)
	private String nomePortata;
	
	
	@OneToMany(mappedBy = "portataMenu", cascade = CascadeType.ALL)
	private List<Piatto> piatti;
	
	public PortataMenu() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePortata() {
		return nomePortata;
	}

	public void setTitolo(String nomePortata) {
		this.nomePortata = nomePortata;
	}

	public List<Piatto> getPiatti() {
		return piatti;
	}

	public void setPiatti(List<Piatto> piatti) {
		this.piatti = piatti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((piatti == null) ? 0 : piatti.hashCode());
		result = prime * result + ((nomePortata == null) ? 0 : nomePortata.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PortataMenu other = (PortataMenu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (piatti == null) {
			if (other.piatti != null)
				return false;
		} else if (!piatti.equals(other.piatti))
			return false;
		if (nomePortata == null) {
			if (other.nomePortata != null)
				return false;
		} else if (!nomePortata.equals(other.nomePortata))
			return false;
		return true;
	}
	
	
}
