package org.fct.servidor.model;

import java.io.Serializable;
import java.util.Objects;

public class ValoracionId implements Serializable{
	
	private Long userValor;
	private Long eventValor;
	
	public ValoracionId() {

	}

	public Long getUsuario() {
		return userValor;
	}

	public void setUsuario(Long usuario) {
		this.userValor = usuario;
	}

	public Long getEvento() {
		return eventValor;
	}

	public void setEvento(Long evento) {
		this.eventValor = evento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventValor, userValor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValoracionId other = (ValoracionId) obj;
		return Objects.equals(eventValor, other.eventValor) && Objects.equals(userValor, other.userValor);
	}
	

}
