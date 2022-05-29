package org.fct.servidor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
@Table(name="usuario")
public class Usuario implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=true)
	private String apellidos;
	
	@Column(unique=true,nullable=false)
	private String email;
	
	@Column(unique=true, nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String id_role;
	
	@Column(nullable=false,columnDefinition="BOOLEAN")	
	private boolean activo;
	
	@OneToMany(mappedBy="userComen",cascade=CascadeType.MERGE,orphanRemoval = true)
	private Set<Comentarios> comentariosu = new HashSet<>();
	
	@OneToMany(mappedBy="userValor",cascade=CascadeType.ALL,orphanRemoval = true)
	private Set<Valoracion> valoracionu = new HashSet<>();
	
	@OneToMany(mappedBy="userGuarEv",cascade=CascadeType.MERGE,orphanRemoval = true)
	private Set<GuardarEvento> guardarEventou = new HashSet<>();
	
	/*@ManyToOne
	@JoinColumn(name = "id_role")
	private Role role;
	
	@OneToMany(mappedBy="usuario",cascade=CascadeType.ALL,orphanRemoval = true)
	private Set<UsuarioRole> usuarioRole = new HashSet<>();
	
	*/
	public Usuario() {
		// TODO Auto-generated constructor stub
	}



	public Long getId_usuario() {
		return id_usuario;
	}



	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}



	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	public String getId_role() {
		return id_role;
	}


	public void setId_role(String id_role) {
		this.id_role = id_role;
	}


	public Set<Comentarios> getComentariosu() {
		return comentariosu;
	}



	public void setComentariosu(Set<Comentarios> comentariosu) {
		this.comentariosu = comentariosu;
	}


	public Set<Valoracion> getValoracionu() {
		return valoracionu;
	}



	public void setValoracionu(Set<Valoracion> valoracionu) {
		this.valoracionu = valoracionu;
	}


	public Set<GuardarEvento> getGuardarEventou() {
		return guardarEventou;
	}



	public void setGuardarEventou(Set<GuardarEvento> guardarEventou) {
		this.guardarEventou = guardarEventou;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id_usuario, username);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id_usuario, other.id_usuario) && Objects.equals(username, other.username);
	}



	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email="
				+ email + ", username=" + username + ", password=" + password + ", id_role=" + id_role + ", activo="
				+ activo + "]";
	}

/*
	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	
	public List<UsuarioRole> getUsuarioRole() {
		return new ArrayList<>(usuarioRole);
	}

	public void setUsuarioRole(Set<UsuarioRole> usuarioRole) {
		this.usuarioRole = usuarioRole;
	}
}*/



	
	

}
