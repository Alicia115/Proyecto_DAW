package org.fct.servidor.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.fct.servidor.dto.EventosDTO;
import org.fct.servidor.dto.UsuarioDTO;
import org.fct.servidor.dto.UsuarioLoginDTO;
import org.fct.servidor.model.Eventos;
import org.fct.servidor.model.Role;
import org.fct.servidor.model.Usuario;
import org.fct.servidor.model.UsuarioRole;
import org.fct.servidor.services.EventosServiceImpl;
import org.fct.servidor.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Main {

	@Autowired
	UsuarioServiceImpl usuarioService;
	
	@Autowired
	EventosServiceImpl eventosService;

	@GetMapping("/")
	public String home(@RequestParam(required=false,name="error") String error, Model model) {
		
		EventosDTO eventdto = new EventosDTO();
		List<Eventos> eventos = eventosService.getAllEventos();
		List<Eventos> eventoslista = eventosService.getAllEventosByTipo();
		List<Eventos> eventoslistalugar = eventosService.getAllEventosByLugar();
		model.addAttribute("eventoslistalugar",eventoslistalugar);
		model.addAttribute("eventoslista",eventoslista);
		model.addAttribute("eventos",eventos);
		model.addAttribute("eventdto",eventdto);
		model.addAttribute("error",error);
		model.addAttribute("contenido", "INICIO");
		return "index";
	}
	
	@PostMapping("/")
	public String postHome(@ModelAttribute EventosDTO eventdto,Model model) {
		
		String tipo = eventdto.getTipo();
		String fecha = eventdto.getFecha();
		String lugar = eventdto.getLugar();
		System.out.println(lugar);
		System.out.println(tipo);
		System.out.println(fecha);
			
		return "redirect:/eventos/listaEventos?tipo="+tipo+"&lugar="+lugar+"&fecha="+fecha;	

	} 
	
	

	@GetMapping("/login")
	public String loginGet(Model model) {

		model.addAttribute("login", "login");
		return "loginUser";
	}

	@PostMapping("/login")
	public String loginPost(@ModelAttribute UsuarioLoginDTO usuario) {	
		
		if(usuarioService.loginUsuario(usuario)!=null) {
			
			return "redirect:/";
		}

		return "redirect:/login";

	}

	@GetMapping("/register")
	public String registerGet(Model model) {

		UsuarioDTO userDTO = new UsuarioDTO();
		model.addAttribute("usuario", userDTO);
		return "register";
	}

	@PostMapping("/register")
	public String registerPost(@ModelAttribute UsuarioDTO usuario) {
		
		Usuario userBD = new Usuario();
		userBD.setActivo(true);
		userBD.setNombre(usuario.getNombre());
		userBD.setApellidos(usuario.getApellidos());
		userBD.setUsername(usuario.getUsername());
		userBD.setEmail(usuario.getEmail());
		userBD.setPassword(new BCryptPasswordEncoder(15).encode(usuario.getPassword()));
		userBD = usuarioService.insertUsuario(userBD);

		if (userBD == null) {
			return "redirect:/register";
		}

		return "redirect:/";
	}
	
}