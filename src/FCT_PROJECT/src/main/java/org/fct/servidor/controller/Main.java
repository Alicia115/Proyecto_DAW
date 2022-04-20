package org.fct.servidor.controller;

import org.fct.servidor.dto.UsuarioDTO;
import org.fct.servidor.dto.UsuarioLoginDTO;
import org.fct.servidor.model.Role;
import org.fct.servidor.model.Usuario;
import org.fct.servidor.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Main {

	@Autowired
	UsuarioServiceImpl usuarioService;

	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("contenido", "INICIO");
		return "index";
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
		
		Role role = new Role();
		role.setId_role(2);
		role.setRole_name("ROLE_USER");

		Usuario userBD = new Usuario();
		userBD.setActivo(true);
		userBD.setNombre(usuario.getNombre());
		userBD.setApellidos(usuario.getApellidos());
		userBD.setUsername(usuario.getUsername());
		userBD.setEmail(usuario.getEmail());
		userBD.setPassword(new BCryptPasswordEncoder(15).encode(usuario.getPassword()));
		userBD.setRole(role);

		userBD = usuarioService.insertUsuario(userBD);

		if (userBD == null) {
			return "redirect:/register";
		}

		return "redirect:/";

	}

}