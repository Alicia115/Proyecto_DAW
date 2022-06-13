package org.fct.servidor.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.fct.servidor.dto.EventoInfoDTO;
import org.fct.servidor.dto.EventosDTO;
import org.fct.servidor.dto.UsuarioAdminDTO;
import org.fct.servidor.model.Eventos;
import org.fct.servidor.model.Usuario;
import org.fct.servidor.services.EventosServiceImpl;
import org.fct.servidor.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {

	@Autowired
	EventosServiceImpl eventosService;
	
	@Autowired
	UsuarioServiceImpl userService;

	@GetMapping("/eventos/addEventos")
	public String addEventoGet(@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "evento") String nombre, Model model) {

		EventoInfoDTO eventodto = new EventoInfoDTO();
		List<Eventos> eventoslistatipo = eventosService.getAllEventosByTipo();
		model.addAttribute("eventoslistatipo", eventoslistatipo);
		model.addAttribute("eventodto", eventodto);
		model.addAttribute("error", error);
		model.addAttribute("previo", nombre);
		return "addEventoAdmin";
	}

	@PostMapping("/eventos/addEventos")
	public String addEventoPost(@ModelAttribute EventoInfoDTO eventodto,
			@RequestParam(required = false, name = "file") MultipartFile imagen, Model model) {

		Eventos eventoNuevo = new Eventos();
		eventoNuevo.setTitulo(eventodto.getTitulo());
		eventoNuevo.setDescripcion(eventodto.getDescripcion());
		eventoNuevo.setTipo(eventodto.getTipo());
		eventoNuevo.setLugar(eventodto.getLugar());
		eventoNuevo.setFecha(eventodto.getFecha());
		eventoNuevo.setCoste(eventodto.getCoste());
		eventoNuevo.setUrl(eventodto.getUrl());

		if (!imagen.isEmpty()) {
			//Path directorioImagenes = Paths.get("src//main//resources//static/img");
			//String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			// String ruta = "C://Temp//uploads";
			String ruta = "/home/alicia/Temporal";

			try {
				byte[] bytesImg = imagen.getBytes();
				//Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Path rutaCompleta = Paths.get(ruta + "/" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);

				eventoNuevo.setImagen(imagen.getOriginalFilename());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (eventosService.insertarEvento(eventoNuevo) == null) {
			// return "redirect:/grados/add?error=Existe&grado=" + grado.getNombre();
		}

		return "redirect:/eventos/listaEventos";
	}

	@GetMapping("/eventos/editEvento")
	public String editEventoGet(@RequestParam(name = "evento") String evento, Model model) {

		Eventos eventoedit = eventosService.findEventosById(Long.parseLong(evento));
		List<Eventos> eventoslistatipo = eventosService.getAllEventosByTipo();
		model.addAttribute("eventoslistatipo", eventoslistatipo);
		model.addAttribute("eventoedit", eventoedit);
		return "editEventoAdmin";
	}

	@PostMapping("/eventos/editEvento")
	public String editEventoPost(@ModelAttribute Eventos evento, 
			@RequestParam(required = false, name = "file") MultipartFile imagen) {
		
	
		if (!imagen.isEmpty()) {
			//Path directorioImagenes = Paths.get("src//main//resources//static/img");
			//String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			// String ruta = "C://Temp//uploads";
			String ruta = "/home/alicia/Temporal";
			
			try {
				byte[] bytesImg = imagen.getBytes();
				//Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Path rutaCompleta = Paths.get(ruta + "/" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);

				evento.setImagen(imagen.getOriginalFilename());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (eventosService.actualizarEvento(evento) == null) {
			return "redirect:/eventos/editEvento?error=error&evento" + evento.getId_evento();
		}
		return "redirect:/eventos/listaEventos";
	}
	
	@GetMapping("/admin/listaUsuarios")
	public String usuarioListado( Model model) {
		
		List<Usuario> usuarios = userService.getAllUsuarios();
		
		model.addAttribute("usuarios",usuarios);
		return "usuariosLista";
	}

	@GetMapping("/admin/deleteUser")
	public String deleteUsuario( Model model, @RequestParam(required = false, name = "usuario") String usuario) {
		
		Usuario user = userService.findUsuarioById(Long.parseLong(usuario));
		user.setActivo(false);
		userService.actualizarUsuario(user);
		
		return "redirect:/admin/listaUsuarios";
	}
	
	@GetMapping("/admin/editUser")
	public String editUsuario( @RequestParam(required = false, name = "usuario") String usuario, Model model) {
		
		Usuario user = userService.findUsuarioById(Long.parseLong(usuario));
		UsuarioAdminDTO userdto = new UsuarioAdminDTO();
		userdto.setId_usuario(user.getId_usuario());
		userdto.setId_role(user.getId_role());
		
		model.addAttribute("userdto",userdto);
		return "editUsuarioAdmin";
	}
	
	@PostMapping("/admin/editUser")
	public String editUsuarioPost(@ModelAttribute UsuarioAdminDTO user) {
		
		Usuario usuario = userService.findUsuarioById(user.getId_usuario());
		usuario.setId_role(user.getId_role());
		
		System.out.println(usuario);
		
		userService.actualizarUsuario(usuario);
		
		return "redirect:/admin/listaUsuarios";
	}
	
	@GetMapping("/admin/deletecomentarios")
	public String userDeleteComentarios(@RequestParam(required = false, name = "error") String error, Model model,
			 @RequestParam(required = false, name = "evento") Long evento, @RequestParam(required = false, name = "usuario") Long usuario) {
		
		Usuario user = userService.findUsuarioById(usuario);
		System.out.println("Usuario "+user);
		Eventos event = eventosService.findEventosById(evento);
		System.out.println("Evento "+event);
		event.removeComment(user);
		System.out.println(eventosService.actualizarEvento(event));
		eventosService.actualizarEvento(event);
		
		return "redirect:/eventos/comentarios?evento="+evento;

	}
	
	@GetMapping("/admin/deletevaloraciones")
	public String userDeleteValoraciones(@RequestParam(required = false, name = "error") String error, Model model,
			 @RequestParam(required = false, name = "evento") String evento, @RequestParam(required = false, name = "usuario") String usuario) {
		
		Usuario user = userService.findUsuarioById(Long.parseLong(usuario));
		Eventos event = eventosService.findEventosById(Long.parseLong(evento));
		event.removeValoracion(user);
		eventosService.actualizarEvento(event);
		
		return "redirect:/eventos/valoraciones?evento="+evento;

	}
	

}
