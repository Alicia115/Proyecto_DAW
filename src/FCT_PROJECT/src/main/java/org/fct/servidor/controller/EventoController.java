package org.fct.servidor.controller;

import java.util.List;

import org.fct.servidor.dto.EventosDTO;
import org.fct.servidor.dto.UsuarioActualizarDTO;
import org.fct.servidor.model.Eventos;
import org.fct.servidor.model.Usuario;
import org.fct.servidor.services.EventosServiceImpl;
import org.fct.servidor.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventoController {
	
	@Autowired
	UsuarioServiceImpl usuarioService;
	
	@Autowired
	EventosServiceImpl eventosService;


	@GetMapping("/eventos/listaEventos")
	public String eventosListado(@RequestParam(required = false, name = "tipo") String tipo, @RequestParam(required = false, name = "lugar") String lugar,
			@RequestParam(required = false, name = "fecha") String fecha, @RequestParam(required=false,name="error") String error, Model model) {
		
		List<Eventos> eventosFiltro = eventosService.getEventosByTipoAndLugarAndFecha(tipo, lugar, fecha);
		List<Eventos> eventoslista = eventosService.getAllEventos();
		List<Eventos> eventoslistatipo = eventosService.getAllEventosByTipo();
		List<Eventos> eventoslistalugar = eventosService.getAllEventosByLugar();
		
		System.out.println(fecha);
		
		EventosDTO eventodto = new EventosDTO();
	
		/**
		Eventos eventoTipo = eventosService.getEventoByTipo(eventdto.getTipo());
		Eventos eventoLugar = eventosService.getEventoByLugar(eventdto.getLugar());
		Eventos eventoFecha = eventosService.getEventoByFecha(eventdto.getFecha());*/
		model.addAttribute("eventoslistalugar",eventoslistalugar);
		model.addAttribute("eventoslistatipo",eventoslistatipo);
		model.addAttribute("eventoslista",eventoslista);
		model.addAttribute("eventodto",eventodto);
		model.addAttribute("eventosFiltro",eventosFiltro);
		model.addAttribute("error",error);
		return "eventosLista";
	}

	@PostMapping("/eventos/listaEventos")
	public String postEventosListado(@ModelAttribute EventosDTO eventodto,Model model) {
		
		String tipo = eventodto.getTipo();
		String fecha = eventodto.getFecha();
		String lugar = eventodto.getLugar();
			
		return "redirect:/eventos/listaEventos?tipo="+tipo+"&lugar="+lugar+"&fecha="+fecha;	

	} 
	
	@GetMapping("/eventos/infoEvento")
	public String eventosInfoGet(@RequestParam(required = false, name = "evento") String evento,
			@RequestParam(required=false,name="error") String error, Model model) {
		
		Eventos event = eventosService.findEventosById(Long.parseLong(evento));
		model.addAttribute("event",event);
		model.addAttribute("error",error);
		return "eventoInfo";
	}
	
	@GetMapping("/eventos/comentarios")
	public String eventosComentarios(@RequestParam(required = false, name = "error") String error, Model model,
			 @RequestParam(required = false, name = "evento") String evento) {

		Eventos event = eventosService.findEventosById(Long.parseLong(evento));
		
		model.addAttribute("event",event);
		model.addAttribute("error", error);
		return "eventoComentarios";
	}

	@GetMapping("/eventos/valoraciones")
	public String eventosValoraciones(@RequestParam(required = false, name = "error") String error, Model model,
			@RequestParam(required = false, name = "evento") String evento) {

		Eventos event = eventosService.findEventosById(Long.parseLong(evento));
		
		model.addAttribute("event",event);
		model.addAttribute("error", error);
		return "eventoValoraciones";
	}
	
}
