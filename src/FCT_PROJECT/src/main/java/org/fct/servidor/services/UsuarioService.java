package org.fct.servidor.services;

import org.fct.servidor.dto.UsuarioLoginDTO;
import org.fct.servidor.model.Usuario;

public interface UsuarioService {

	public Usuario insertUsuario(Usuario usuario);
	public Usuario loginUsuario(UsuarioLoginDTO usuario);
}
