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
		ErrorObject objeto = setearPassword(usuario, username);
		Usuario nuevo_usuario = null;

		if (objeto.getError() == null) {
			nuevo_usuario = (Usuario) objeto.getObject();
		} else {
			error = objeto.getError();
			return "redirect:/user/perfil?error=" + error;
		}
		System.out.println(nuevo_usuario);

		System.out.println(nuevo_usuario);
		if (userService.actualizarUsuario(nuevo_usuario) == null) {
			error = "error.errorUsername";
			return "redirect:/user/perfil?error=" + error;
		}

		return "redirect:/user/perfil";
	}

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

		Usuario nuevo_usuario = userService.getUsuarioByUserName(username);
		nuevo_usuario.setNombre(usuario.getNombre());
		nuevo_usuario.setApellidos(usuario.getApellidos());
		nuevo_usuario.setEmail(usuario.getEmail());
		nuevo_usuario.setUsername(usuario.getUsername());

		ErrorObject object = new ErrorObject();

		if (!"".equals(usuario.getNewpassword()) && !"".equals(usuario.getConfirmpassword())) {

			if (comprobarPassword(usuario.getPassword(), usuario.getNewpassword(), usuario.getConfirmpassword(),
					username)) {
				nuevo_usuario.setPassword(new BCryptPasswordEncoder(15).encode(usuario.getNewpassword()));
			}
			object.setError("error.errorPassword");

		} else {
			nuevo_usuario.setPassword(usuario.getPassword());
		}

		object.setObject(nuevo_usuario);
		return object;
	}
}
