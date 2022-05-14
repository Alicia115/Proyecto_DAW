package org.fct.servidor.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="usuario_role")
@IdClass(UsuarioRoleId.class)
public class UsuarioRole implements Serializable {
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_usuario",insertable=false,updatable=false)
	private Usuario usuario;
	
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_role",insertable=false,updatable=false)
	private Role role;


	public UsuarioRole() {

	}


	public UsuarioRole(Usuario usuario, Role role) {
		super();
		this.usuario = usuario;
		this.role = role;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
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
		UsuarioRole other = (UsuarioRole) obj;
		return Objects.equals(role, other.role) && Objects.equals(usuario, other.usuario);
	}
	
	

}
