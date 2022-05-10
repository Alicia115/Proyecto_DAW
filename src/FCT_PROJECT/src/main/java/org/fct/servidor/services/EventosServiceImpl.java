package org.fct.servidor.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fct.servidor.model.Eventos;
import org.fct.servidor.repository.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventosServiceImpl implements EventosService{
	
	@Autowired
	EventosRepository eventosRepo;

	public List<Eventos> getAllEventos() {
		
		List<Eventos> eventosList = eventosRepo.findAll();
		
		if(eventosList!=null && eventosList.size()>0) {
			return eventosList;
		}
		
		return new ArrayList<Eventos>();
	}

	public Eventos findEventosById(Long id) {
		
		if(id!=null) {
			return eventosRepo.getById(id);
		}else {
			return null;
		}
	}


	public Eventos getEventoByFecha(Date fecha) {
		
		if(fecha!=null) {
			Eventos evento = eventosRepo.findByFecha(fecha);
			return evento;
		}else {
			return null;
		}
	}


	public Eventos getEventoByTitulo(String titulo) {
		
		if(titulo!=null) {
			Eventos evento = eventosRepo.findByTitulo(titulo);
			return evento;
		}else {
			return null;
		}
	}

	public List<Eventos> getEventosByTipo(String tipo) {
		if(tipo!=null) {
			List<Eventos> evento = (List<Eventos>) eventosRepo.findByTipo(tipo);
			return evento;
		}else {
			return null;
		}
	}

	public List<Eventos> getEventosByLugar(String lugar) {

		if(lugar!=null) {
			List<Eventos> evento = (List<Eventos>) eventosRepo.findByLugar(lugar);
			return evento;
		}else {
			return null;
		}
	}

	public List<Eventos> getEventosByFecha(Date fecha) {
		
		if(fecha!=null) {
			List<Eventos>  eventos = (List<Eventos>) eventosRepo.findByFecha(fecha);
			return eventos;
		}else {
			return null;
		}
	}

	@Override
	public List<Eventos> getEventosByHora(String hora) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Eventos> getEventosByCoste(Double coste) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Eventos> getEventosByTipoAndLugar(String tipo, String lugar) {
		
		if(tipo!= null && lugar !=null) {
			return eventosRepo.findDistinctByTipoAndLugar(tipo, lugar);
		} else if(tipo!=null ){
			return eventosRepo.findByTipo(tipo);
		}else if(lugar!=null) {
			return eventosRepo.findByLugar(lugar);
		}
		else {
			return eventosRepo.findAll();
		}
		
	}

	

}
