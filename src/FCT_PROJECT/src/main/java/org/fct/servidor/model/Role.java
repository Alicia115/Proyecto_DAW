package org.fct.servidor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="role")
public class Role implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_role;
	
	@Column(unique=true, nullable=false)
	private String role_name;
	
	@OneToMany(mappedBy="role", cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Set<Usuario> usuarios = new HashSet<Usuario>();

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_role;
		result = prime * result + ((role_name == null) ? 0 : role_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id_role != other.id_role)
			return false;
		if (role_name == null) {
			if (other.role_name != null)
				return false;
		} else if (!role_name.equals(other.role_name))
			return false;
		return true;
	}

	//Métodos HELPERs	
	public void addUsuario(Usuario usuario)  {
		this.usuarios.add(usuario);
		usuario.setRole(this);
	}
	
	public void removeUsuario(Usuario usuario) {
		this.usuarios.remove(usuario);
		usuario.setRole(null);
	}

	@Override
	public String toString() {
		return "Role [id_role=" + id_role + ", role_name=" + role_name + ", usuarios=" + usuarios + "]";
	}
	
	

}
