package org.fct.servidor.model;

import java.io.Serializable;
import java.util.Objects;

public class GuardarEventoId implements Serializable{

	private Long userGuarEv;
	private Long eventGuarEv;
	
	public GuardarEventoId() {
	
	}

	public Long getUsuario() {
		return userGuarEv;
	}

	public void setUsuario(Long usuario) {
		this.userGuarEv = usuario;
	}

	public Long getEvento() {
		return eventGuarEv;
	}

	public void setEvento(Long evento) {
		this.eventGuarEv = evento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventGuarEv, userGuarEv);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GuardarEventoId other = (GuardarEventoId) obj;
		return Objects.equals(eventGuarEv, other.eventGuarEv) && Objects.equals(userGuarEv, other.userGuarEv);
	}
	
	
	
}
