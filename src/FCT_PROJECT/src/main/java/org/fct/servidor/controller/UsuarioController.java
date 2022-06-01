package org.fct.servidor.controller;

import java.time.LocalDate;

import org.fct.servidor.dto.ComentarioDTO;
import org.fct.servidor.dto.UsuarioActualizarDTO;
import org.fct.servidor.dto.ValoracionDTO;
import org.fct.servidor.model.Comentarios;
import org.fct.servidor.model.Eventos;
import org.fct.servidor.model.Usuario;
import org.fct.servidor.model.Valoracion;
import org.fct.servidor.services.EventosServiceImpl;
import org.fct.servidor.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {
	@Autowired
	EventosServiceImpl eventosService;

	@Autowired
	UsuarioServiceImpl userService;
	
	
	@GetMapping("/user/comentarios")
	public String userComentarios(@RequestParam(required = false, name = "error") String error, Model model,
			Authentication auth, @RequestParam(required = false, name = "evento") String evento) {
		
		if (evento == null) {
			return "redirect:/eventos/valoraciones?evento="+evento;
		}
        
		String username = auth.getName();
		Usuario usuario = userService.getUsuarioByUserName(username);
		ComentarioDTO comentariodto = new ComentarioDTO();
		Eventos event = eventosService.findEventosById(Long.parseLong(evento));
		
		for (Comentarios comentario : event.getComentariose()) {
			if(comentario.getUsuario().getId_usuario()==usuario.getId_usuario()) {
				comentariodto.setDescripcion(comentario.getDescripcion());
				comentariodto.setTitulo(comentario.getTitulo());
				break;
			}
		}
		
		comentariodto.setId_evento(Long.toString(event.getId_evento(), 10));
		model.addAttribute("comentario",comentariodto);
		model.addAttribute("event",event);
		model.addAttribute("error", error);
		return "userAddComentarios";
	}
	
	@PostMapping("/user/comentarios")
	public String userComentariosPost( Model model,Authentication auth, 
			@ModelAttribute ComentarioDTO comentario) {

		String username = auth.getName();
		Usuario usuario = userService.getUsuarioByUserName(username);
		System.out.println("comentario id " + comentario.getId_evento());
		Eventos evento = eventosService.findEventosById(Long.parseLong(comentario.getId_evento()));
		
		/*Obtener fecha en String*/
		LocalDate fecha =LocalDate.now();
      
        evento.addComment(usuario, comentario.getTitulo(), comentario.getDescripcion(), fecha);
        eventosService.actualizarEvento(evento);
    		
    	return "redirect:/eventos/comentarios?evento="+comentario.getId_evento();
	}
	

	@GetMapping("/user/valoraciones")
	public String userValoraciones(@RequestParam(required = false, name = "error") String error, Model model,
			Authentication auth, @RequestParam(required = false, name = "evento") String evento) {
		
		if (evento == null) {
			return "redirect:/eventos/comentarios?evento="+evento;
		}
        
		String username = auth.getName();
		Usuario usuario = userService.getUsuarioByUserName(username);
		ValoracionDTO valoraciondto = new ValoracionDTO();
		Eventos event = eventosService.findEventosById(Long.parseLong(evento));
		
		for (Valoracion valoracion : event.getValoracione()) {
			if(valoracion.getUsuario().getId_usuario()==usuario.getId_usuario()) {
				valoraciondto.setPuntuacion(valoracion.getPuntuacion());
				break;
			}
		}
		
		valoraciondto.setId_evento(Long.toString(event.getId_evento(), 10));
		model.addAttribute("valoracion",valoraciondto);
		model.addAttribute("event",event);
		model.addAttribute("error", error);
		return "userAddValoracion";
	}
	
	@PostMapping("/user/valoraciones")
	public String userValoracionesPost( Model model,Authentication auth, 
			@ModelAttribute ValoracionDTO valoracion) {

		String username = auth.getName();
		Usuario usuario = userService.getUsuarioByUserName(username);
		Eventos evento = eventosService.findEventosById(Long.parseLong(valoracion.getId_evento()));
		System.out.println(valoracion.getPuntuacion());
		
		/*Obtener fecha en String*/
		LocalDate fecha =LocalDate.now();
		evento.addValoracion(usuario, fecha, valoracion.getPuntuacion());
        eventosService.actualizarEvento(evento);
    		
    	return "redirect:/eventos/valoraciones?evento="+valoracion.getId_evento();
	}
	
	
	@GetMapping("/user/guardados")
	public String userComentariosGuardados(@RequestParam(required = false, name = "error") String error, Model model,
			Authentication auth) {
		
		String username = auth.getName();
		Usuario usuario = userService.getUsuarioByUserName(username);
		
		/*
		List<Eventos> eventos = new ArrayList<>();
		
		for (GuardarEvento evento : usuario.getGuardarEventou()) {
			eventos.add(evento.getEventGuarEv());
		}*/
		
		model.addAttribute("eventos", usuario.getEventos_guardados());
		
		return "userEventosGuardados";
	
	}
	
	@GetMapping("/user/addGuardados")
	public String userComentariosAddGuardados(@RequestParam(required = false, name = "error") String error, Model model,
			@RequestParam(required = false, name = "evento") String evento, Authentication auth) {
		
		String username = auth.getName();
		Usuario usuario = userService.getUsuarioByUserName(username);
		
		Eventos eventoSelect = eventosService.findEventosById(Long.parseLong(evento));
		eventoSelect.addGuardarEvento(usuario);
		eventosService.actualizarEvento(eventoSelect);
		
		return "redirect:/user/guardados";
	
	}
	
	@GetMapping("/user/deleteGuardados")
	public String userComentariosDeleteGuardados(@RequestParam(required = false, name = "error") String error, Model model,
			@RequestParam(required = false, name = "evento") String evento, Authentication auth) {
		
		String username = auth.getName();
		Usuario usuario = userService.getUsuarioByUserName(username);
		
		Eventos eventoSelect = eventosService.findEventosById(Long.parseLong(evento));
		eventoSelect.removeGuardarEvento(usuario);
		eventosService.actualizarEvento(eventoSelect);
		
		return "redirect:/user/guardados";
	
	}
	
	
	@GetMapping("/user/miscomentarios")
	public String userMisComentarios(@RequestParam(required = false, name = "error") String error, Model model,
			Authentication auth) {
		
		String username = auth.getName();
		Usuario usuario = userService.getUsuarioByUserName(username);
		
		model.addAttribute("eventos", usuario.getComentariosu());
		
		return "userMisComentarios";

	}
	
	@GetMapping("/user/deletemiscomentarios")
	public String userDeleteMisComentarios(@RequestParam(required = false, name = "error") String error, Model model,
			Authentication auth, @RequestParam(required = false, name = "evento") String evento) {
		
		String username = auth.getName();
		Usuario usuario = userService.getUsuarioByUserName(username);
		Eventos event = eventosService.findEventosById(Long.parseLong(evento));
		event.removeComment(usuario);
		eventosService.actualizarEvento(event);
		
		return "redirect:/user/miscomentarios";

	}
	
	@GetMapping("/user/misvaloraciones")
	public String userMisValoraciones(@RequestParam(required = false, name = "error") String error, Model model,
			Authentication auth) {
		
		String username = auth.getName();
		Usuario usuario = userService.getUsuarioByUserName(username);
		
		model.addAttribute("eventos", usuario.getValoracionu());
		
		return "userMisValoraciones";

	}
	
	@GetMapping("/user/deletemisvaloraciones")
	public String userDeleteMisValoraciones(@RequestParam(required = false, name = "error") String error, Model model,
			Authentication auth, @RequestParam(required = false, name = "evento") String evento) {
		
		String username = auth.getName();
		Usuario usuario = userService.getUsuarioByUserName(username);
		Eventos event = eventosService.findEventosById(Long.parseLong(evento));
		event.removeValoracion(usuario);
		eventosService.actualizarEvento(event);
		
		return "redirect:/user/misvaloraciones";

	}
	
	@GetMapping("/user/perfil")
	public String userPerfil(@RequestParam(required = false, name = "error") String error, Model model,
			Authentication auth) {

		String username = auth.getName();
		Usuario usuario = userService.getUsuarioByUserName(username);

		UsuarioActualizarDTO usuariodto = new UsuarioActualizarDTO();
		usuariodto.setNombre(usuario.getNombre());
		usuariodto.setApellidos(usuario.getApellidos());
		usuariodto.setEmail(usuario.getEmail());
		usuariodto.setUsername(usuario.getUsername());
		usuariodto.setPassword(usuario.getPassword());

		System.out.println(usuario);
		// System.out.println(usuario.getNombre());

		model.addAttribute("error", error);
		model.addAttribute("usuario", usuariodto);
		return "perfilUsuario";
	}

	@PostMapping("/user/perfil")
	public String userPerfilPost(@ModelAttribute UsuarioActualizarDTO usuario, Authentication auth) {

		String error = null;
		String username = auth.getName();
		String newPassword = new BCryptPasswordEncoder(15).encode(usuario.getNewpassword());
		Usuario user = userService.getUsuarioByUserName(username);
		Usuario nuevo_usuario = userService.getUsuarioByUserName(username);
		nuevo_usuario.setNombre(usuario.getNombre());
		nuevo_usuario.setApellidos(usuario.getApellidos());
		nuevo_usuario.setEmail(usuario.getEmail());
		nuevo_usuario.setUsername(usuario.getUsername());

		if (!"".equals(usuario.getNewpassword()) && !"".equals(usuario.getPassword())) {

			if (comprobarPassword(usuario.getPassword(), newPassword, username)) {
				nuevo_usuario.setPassword(newPassword);
			}else {
				return "redirect:/user/perfil?error=error";
			}
		} 
		
		userService.actualizarUsuario(nuevo_usuario);
		
		return "redirect:/user/perfil";
		
		
		/*ErrorObject objeto = setearPassword(usuario, username);
		Usuario nuevo_usuario = null;
		System.out.println(objeto);
		if (objeto.getError() == null) {
			nuevo_usuario = (Usuario) objeto.getObject();
			System.out.println(nuevo_usuario);
			userService.actualizarUsuario(nuevo_usuario);
			return "redirect:/user/perfil";
		} else {
			error = objeto.getError();
			return "redirect:/user/perfil?error=" + error;
		}	*/
		
		
	}
	
	private boolean comprobarPassword(String antigua, String nueva, String username) {

		if (!antigua.equals(nueva)) {
			Usuario usuario = userService.getUsuarioByUserName(username);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(15);
			if (encoder.matches(antigua, usuario.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	/*

	private boolean comprobarPassword(String antigua, String nueva, String confirmar, String username) {

		if (nueva.equals(confirmar) && !antigua.equals(nueva)) {
			Usuario usuario = userService.getUsuarioByUserName(username);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(15);
			if (encoder.matches(antigua, usuario.getPassword())) {
				return true;
			}
		}
		return false;
	}

	private ErrorObject setearPassword(UsuarioModificarDTO usuario, String username) {
		Usuario user = userService.getUsuarioByUserName(username);
		Usuario nuevo_usuario = userService.getUsuarioByUserName(username);
		nuevo_usuario.setNombre(usuario.getNombre());
		nuevo_usuario.setApellidos(usuario.getApellidos());
		nuevo_usuario.setEmail(usuario.getEmail());
		nuevo_usuario.setUsername(usuario.getUsername());
		nuevo_usuario.setPassword(user.getPassword());

		ErrorObject object = new ErrorObject();

		if (!"".equals(usuario.getNewpassword()) && !"".equals(usuario.getConfirmpassword()) && !"".equals(usuario.getPassword())) {

			if (comprobarPassword(usuario.getPassword(), usuario.getNewpassword(), usuario.getConfirmpassword(),
					username)) {
				nuevo_usuario.setPassword(new BCryptPasswordEncoder(15).encode(usuario.getNewpassword()));
			}
			object.setError("error.errorPassword");
		} 

		object.setObject(nuevo_usuario);
		return object;
	}*/
}
