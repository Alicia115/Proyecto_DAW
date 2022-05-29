package org.fct.servidor.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="guardar_evento")
@IdClass(GuardarEventoId.class)
public class GuardarEvento implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_usuario",insertable=false,updatable=false)
	private Usuario userGuarEv;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_evento",insertable=false,updatable=false)
	private Eventos eventGuarEv;
	
	

	public GuardarEvento() {
	
	}


	public GuardarEvento(Usuario usuario, Eventos evento) {
		super();
		this.userGuarEv = usuario;
		this.eventGuarEv = evento;
	}
	
	


	public Usuario getUserGuarEv() {
		return userGuarEv;
	}


	public void setUserGuarEv(Usuario userGuarEv) {
		this.userGuarEv = userGuarEv;
	}


	public Eventos getEventGuarEv() {
		return eventGuarEv;
	}


	public void setEventGuarEv(Eventos eventGuarEv) {
		this.eventGuarEv = eventGuarEv;
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
		GuardarEvento other = (GuardarEvento) obj;
		return Objects.equals(eventGuarEv, other.eventGuarEv) && Objects.equals(userGuarEv, other.userGuarEv);
	}
	
	
	

}
