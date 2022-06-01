package org.fct.servidor.dto;

import java.io.Serializable;

public class ValoracionDTO implements Serializable{
	
	private String id_evento;
	private int puntuacion;
	
	public ValoracionDTO() {
		
	}

	public String getId_evento() {
		return id_evento;
	}

	public void setId_evento(String id_evento) {
		this.id_evento = id_evento;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
}
