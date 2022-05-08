package org.fct.servidor.dto;

import java.io.Serializable;
import java.util.Date;

public class EventosDTO implements Serializable{
	
	private Date fecha;
	private String lugar;
	private String tipo;
	private Double coste;

	
	public EventosDTO() {
		// TODO Auto-generated constructor stub
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
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


}
