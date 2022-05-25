package org.fct.servidor.controller;

import org.fct.servidor.dto.UsuarioDTO;
import org.fct.servidor.dto.UsuarioModificarDTO;
import org.fct.servidor.model.ErrorObject;
import org.fct.servidor.model.Usuario;
import org.fct.servidor.services.EventosServiceImpl;
import org.fct.servidor.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@GetMapping("/user/perfil")
	public String userPerfil(@RequestParam(required = false, name = "error") String error, Model model,
			Authentication auth) {

		String username = auth.getName();
		Usuario usuario = new Usuario();

		usuario = userService.getUsuarioByUserName(username);

		UsuarioModificarDTO usuariodto = new UsuarioModificarDTO();
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
	public String userPerfilPost(@ModelAttribute UsuarioModificarDTO usuario, Authentication auth) {

		String error = null;
		String username = auth.getName();
		String newPassword = new BCryptPasswordEncoder(15).encode(usuario.getNewpassword());
		Usuario user = userService.getUsuarioByUserName(username);
		Usuario nuevo_usuario = userService.getUsuarioByUserName(username);
		nuevo_usuario.setNombre(usuario.getNombre());
		nuevo_usuario.setApellidos(usuario.getApellidos());
		nuevo_usuario.setEmail(usuario.getEmail());
		nuevo_usuario.setUsername(usuario.getUsername());
		nuevo_usuario.setPassword(user.getPassword());

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
