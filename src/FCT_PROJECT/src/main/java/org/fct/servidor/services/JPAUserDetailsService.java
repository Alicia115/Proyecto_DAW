package org.fct.servidor.services;

import java.util.Optional;

import org.fct.servidor.model.JPAUserDetails;
import org.fct.servidor.model.Usuario;
import org.fct.servidor.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JPAUserDetailsService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//Obtengo el usuario
		Optional<Usuario> user = usuarioRepository.findByUsername(username);
		
		
		//Si el usuario no existe debo devolver una excepción
		if (user == null) {
			throw new UsernameNotFoundException("Not found:"+ username);
		}
		
		//Adapto la información del usuario al UserDetails que es lo que debe devolver el método
		JPAUserDetails userDetails = new JPAUserDetails(user.get());
		return userDetails;
		
	}


}