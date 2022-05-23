package org.fct.servidor.services;

import java.util.Optional;

import org.fct.servidor.dto.UsuarioLoginDTO;
import org.fct.servidor.model.Usuario;
import org.fct.servidor.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioRepository userRepo;
	
	BCryptPasswordEncoder encriptador;

	public UsuarioServiceImpl() {
		encriptador = new BCryptPasswordEncoder(14);

	}

	public Usuario insertUsuario(Usuario usuario) {
		
		if (usuario!=null && userRepo.findByUsername(usuario.getUsername())==null) {
			
			return userRepo.save(usuario);
		}
		
		return null;
	}

	public Usuario loginUsuario(UsuarioLoginDTO usuario) {
		
		Optional<Usuario> user = userRepo.findByUsername(usuario.getUsername());
		
		if(user.isPresent() && encriptador.matches(usuario.getPassword(), user.get().getPassword())) {
			
			return user.get();
			
		}
		
		return null;
	}

	@Override
	public Usuario getUsuarioByUserName(String username) {
		
		if(username!=null) {
			Usuario user =  userRepo.findByUsername(username).get();
			return user;
		}
		
		return null;
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		
		if (usuario == null || usuario.getUsername()==null ) {
			return null;
		}

		return userRepo.save(usuario);
	}

}
