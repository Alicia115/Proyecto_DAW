package org.fct.servidor.dto;

import java.io.Serializable;
import java.util.Date;

public class EventosDTO implements Serializable{
	
	private String fecha;
	private String lugar;
	private String tipo;

	
	public EventosDTO() {
		// TODO Auto-generated constructor stub
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



}
