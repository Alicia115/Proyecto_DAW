package org.fct.servidor.dto;

import java.io.Serializable;

import javax.persistence.Column;

public class ComentarioDTO implements Serializable{
	
	private String id_evento;
	private String titulo;
	private String descripcion;
	
	public ComentarioDTO() {

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

	public String getId_evento() {
		return id_evento;
	}

	public void setId_evento(String id_evento) {
		this.id_evento = id_evento;
	}

	
	
	
	
	

}
