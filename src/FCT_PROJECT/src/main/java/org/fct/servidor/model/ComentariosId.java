package org.fct.servidor.model;

import java.io.Serializable;
import java.util.Objects;

public class ComentariosId implements Serializable{
	
	private Long userComen;
	private Long eventos;
	
	public ComentariosId() {
		
	}

	public Long getUsuario() {
		return userComen;
	}

	public void setUsuario(Long usuario) {
		this.userComen = usuario;
	}

	public Long getEvento() {
		return eventos;
	}

	public void setEvento(Long evento) {
		this.eventos = evento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventos, userComen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComentariosId other = (ComentariosId) obj;
		return Objects.equals(eventos, other.eventos) && Objects.equals(userComen, other.userComen);
	}

	
	
}
