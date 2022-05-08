package org.fct.servidor.services;

import java.util.Date;
import java.util.List;

import org.fct.servidor.model.Eventos;


public interface EventosService {
	
	public List<Eventos> getAllEventos();
	public Eventos findEventosById(Long id);
	public Eventos getEventoByTitulo(String titulo);
	public List <Eventos> getEventosByTipo(String tipo);
	public List <Eventos>  getEventosByLugar(String lugar);
	public List <Eventos>  getEventosByFecha(Date fecha);
	public List <Eventos>  getEventosByHora(String hora);
	public List <Eventos>  getEventosByCoste(Double coste);

}
