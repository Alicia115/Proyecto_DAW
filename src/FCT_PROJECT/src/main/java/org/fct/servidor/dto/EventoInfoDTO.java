package org.fct.servidor.dto;

import java.io.Serializable;

public class EventoInfoDTO implements Serializable{
	
	private String titulo;
	private String descripcion;
	private String fecha;
	private String lugar;
	private String tipo;
	private Double coste;
	private String imagen;
	private String url;
	
	public EventoInfoDTO() {

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


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getLugar() {
		return lugar;
	}


	public void setLugar(String lugar) {
		this.lugar = lugar;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Double getCoste() {
		return coste;
	}


	public void setCoste(Double coste) {
		this.coste = coste;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
