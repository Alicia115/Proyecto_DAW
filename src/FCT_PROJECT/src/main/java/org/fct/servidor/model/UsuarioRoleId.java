package org.fct.servidor.model;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioRoleId implements Serializable {
	
	private int usuario;
	private int role;
	
	public UsuarioRoleId() {
		
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(role, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioRoleId other = (UsuarioRoleId) obj;
		return role == other.role && usuario == other.usuario;
	}
	
	
	

}
