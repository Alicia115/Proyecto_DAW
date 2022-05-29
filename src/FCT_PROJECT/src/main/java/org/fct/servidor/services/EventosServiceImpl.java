package org.fct.servidor.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.fct.servidor.model.Eventos;
import org.fct.servidor.repository.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EventosServiceImpl implements EventosService {

	@Autowired
	EventosRepository eventosRepo;

	public List<Eventos> getAllEventos() {

		List<Eventos> eventosList = eventosRepo.findAll();

		if (eventosList != null && eventosList.size() > 0) {
			return eventosList;
		}

		return new ArrayList<Eventos>();
	}

	public Eventos findEventosById(Long id) {

		if (id != null) {
			return eventosRepo.getById(id);
		} else {
			return null;
		}
	}

	public Eventos getEventoByTitulo(String titulo) {

		if (titulo != null) {
			Eventos evento = eventosRepo.findByTitulo(titulo);
			return evento;
		} else {
			return null;
		}
	}

	public List<Eventos> getEventosByTipo(String tipo) {
		if (tipo != null) {
			List<Eventos> evento = (List<Eventos>) eventosRepo.findByTipo(tipo);
			return evento;
		} else {
			return null;
		}
	}

	public List<Eventos> getEventosByLugar(String lugar) {

		if (lugar != null) {
			List<Eventos> evento = (List<Eventos>) eventosRepo.findByLugar(lugar);
			return evento;
		} else {
			return null;
		}
	}

	@Override
	public List<Eventos> getEventosByTipoAndLugarAndFecha(String tipo, String lugar, String fecha) {
		System.out.println(fecha);
		List<Eventos> listaEventos = null;

		if (StringUtils.hasLength(tipo) && StringUtils.hasLength(lugar) && StringUtils.hasLength(fecha)) {
			listaEventos = eventosRepo.findDistinctByTipoAndLugarAndFecha(tipo, lugar, fecha);
		} else if (StringUtils.hasLength(tipo) && StringUtils.hasLength(lugar)) {
			listaEventos = eventosRepo.findDistinctByTipoAndLugar(tipo, lugar);

		} else if (StringUtils.hasLength(lugar) && StringUtils.hasLength(fecha)) {
			listaEventos = eventosRepo.findDistinctByLugarAndFecha(lugar, fecha);

		} else if (StringUtils.hasLength(tipo) && StringUtils.hasLength(fecha)) {
			listaEventos = eventosRepo.findDistinctByTipoAndFecha(tipo, fecha);
		} else if (StringUtils.hasLength(tipo)) {
			listaEventos = eventosRepo.findByTipo(tipo);
		} else if (StringUtils.hasLength(lugar)) {
			listaEventos = eventosRepo.findByLugar(lugar);
		} else if (StringUtils.hasLength(fecha)) {
			listaEventos = eventosRepo.findByFecha(fecha);
		} else {
			return eventosRepo.findAll();
		}

		return listaEventos;
	}

	@Override
	public List<Eventos> getAllEventosByTipo() {

		List<Eventos> eventosList = eventosRepo.findAll();
		List<Eventos> eventosByTipo = new ArrayList();

		if (eventosList != null && eventosList.size() > 0) {

			for (int i = 0; i < eventosList.size(); i++) {

				boolean encontrado = false;

				for (int j = 0; j < eventosByTipo.size(); j++) {
					encontrado = false;

					if (eventosByTipo.get(j).getTipo().equals(eventosList.get(i).getTipo())) {
						encontrado = true;
						break;
					}
				}

				if (!encontrado) {
					eventosByTipo.add(eventosList.get(i));
				}

			}

			return eventosByTipo;
		} else {
			return eventosList;
		}
	}

	@Override
	public List<Eventos> getAllEventosByLugar() {
		List<Eventos> eventosList = eventosRepo.findAll();
		List<Eventos> eventosByLugar = new ArrayList();

		if (eventosList != null && eventosList.size() > 0) {

			for (int i = 0; i < eventosList.size(); i++) {

				boolean encontrado = false;

				for (int j = 0; j < eventosByLugar.size(); j++) {
					encontrado = false;

					if (eventosByLugar.get(j).getLugar().equals(eventosList.get(i).getLugar())) {
						encontrado = true;
						break;
					}
				}

				if (!encontrado) {
					eventosByLugar.add(eventosList.get(i));
				}
			}

			return eventosByLugar;
		} else {
			return eventosList;
		}
	}

	@Override
	public Eventos insertarEvento(Eventos evento) {

		if (evento != null) {
			Eventos eventonuevo = eventosRepo.save(evento);
			return eventonuevo;
		}

		return null;
	}

	@Override
	public Eventos actualizarEvento(Eventos evento) {

		if (evento == null && evento.getId_evento() == null) {
			return null;
		}

		return eventosRepo.save(evento);
	}

}
