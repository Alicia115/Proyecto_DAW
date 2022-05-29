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
@Table(name = "comentarios")
@IdClass(ComentariosId.class)
public class Comentarios implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "id_usuario", insertable = false, updatable = false)
	private Usuario userComen;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_evento", insertable = false, updatable = false)
	private Eventos eventos;

	private String titulo;
	private String descripcion;
	private LocalDate fecha;
	

	public Comentarios() {

	}

	public Comentarios(Usuario usuario, Eventos evento, String titulo, String descripcion, LocalDate fecha) {
		super();
		this.userComen = usuario;
		this.eventos = evento;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}

	public Comentarios(Usuario usuario, Eventos evento) {
		super();
		this.userComen = usuario;
		this.eventos = evento;
	}

	public Usuario getUsuario() {
		return userComen;
	}

	public void setUsuario(Usuario usuario) {
		this.userComen = usuario;
	}

	public Eventos getEvento() {
		return eventos;
	}

	public void setEvento(Eventos evento) {
		this.eventos = evento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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
		Comentarios other = (Comentarios) obj;
		return Objects.equals(eventos, other.eventos) && Objects.equals(userComen, other.userComen);
	}

}
