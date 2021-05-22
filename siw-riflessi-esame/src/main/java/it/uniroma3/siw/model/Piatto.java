package it.uniroma3.siw.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Piatto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String descrizione;
	
	@ManyToOne
	private PortataMenu portataMenu;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public PortataMenu getPortataMenu() {
		return portataMenu;
	}

	public void setPortataMenu(PortataMenu portataMenu) {
		this.portataMenu = portataMenu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((portataMenu == null) ? 0 : portataMenu.hashCode());
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
		Piatto other = (Piatto) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (portataMenu == null) {
			if (other.portataMenu != null)
				return false;
		} else if (!portataMenu.equals(other.portataMenu))
			return false;
		return true;
	} 
	
	
}