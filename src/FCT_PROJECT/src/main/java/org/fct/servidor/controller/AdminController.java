package org.fct.servidor.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.fct.servidor.dto.EventoInfoDTO;
import org.fct.servidor.model.Eventos;
import org.fct.servidor.services.EventosServiceImpl;
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

	@GetMapping("/eventos/addEventos")
	public String addEventoGet(@RequestParam(required = false, name = "error") String error,
			@RequestParam(required = false, name = "evento") String nombre, Model model) {

		EventoInfoDTO eventodto = new EventoInfoDTO();
		List<Eventos> eventoslistatipo = eventosService.getAllEventosByTipo();
		model.addAttribute("eventoslistatipo", eventoslistatipo);
		model.addAttribute("eventodto", eventodto);
		model.addAttribute("error", error);
		model.addAttribute("previo", nombre);
		return "addEvento";
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

		if (!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static/img");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			// String ruta = "C://Temp//uploads";

			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				// Path rutaCompleta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
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

		return "redirect:/";
	}

	@GetMapping("/eventos/editEvento")
	public String editEventoGet(@RequestParam(name = "evento") String evento, Model model) {

		Eventos eventoedit = eventosService.findEventosById(Long.parseLong(evento));
		List<Eventos> eventoslistatipo = eventosService.getAllEventosByTipo();
		model.addAttribute("eventoslistatipo", eventoslistatipo);
		model.addAttribute("eventoedit", eventoedit);
		return "editEvento";
	}

	@PostMapping("/eventos/editEvento")
	public String editEventoPost(@ModelAttribute Eventos evento, 
			@RequestParam(required = false, name = "file") MultipartFile imagen) {
		
	
		if (!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static/img");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			// String ruta = "C://Temp//uploads";

			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				// Path rutaCompleta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
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

}
