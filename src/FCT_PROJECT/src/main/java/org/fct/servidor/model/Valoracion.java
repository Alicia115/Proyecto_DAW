package org.fct.servidor.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="valoracion")
@IdClass(ValoracionId.class)
public class Valoracion implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_usuario",insertable=false,updatable=false)
	private Usuario userValor;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_evento",insertable=false,updatable=false)
	private Eventos eventValor;
	
	
	private LocalDate fecha;
	private int puntuacion;

	public Valoracion() {
		
	}

	public Valoracion(Usuario usuario, Eventos evento, LocalDate fecha, int puntuacion) {
		super();
		this.userValor = usuario;
		this.eventValor = evento;
		this.fecha = fecha;
		this.puntuacion = puntuacion;
	}

	public Valoracion(Usuario usuario, Eventos evento) {
		super();
		this.userValor = usuario;
		this.eventValor = evento;
	}

	public Usuario getUsuario() {
		return userValor;
	}

	public void setUsuario(Usuario usuario) {
		this.userValor = usuario;
	}

	public Eventos getEvento() {
		return eventValor;
	}

	public void setEvento(Eventos evento) {
		this.eventValor = evento;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventValor, fecha, puntuacion, userValor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Valoracion other = (Valoracion) obj;
		return Objects.equals(eventValor, other.eventValor) && Objects.equals(fecha, other.fecha)
				&& puntuacion == other.puntuacion && Objects.equals(userValor, other.userValor);
	} 
	
	
	

}
