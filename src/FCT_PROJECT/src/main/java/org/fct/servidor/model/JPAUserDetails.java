package org.fct.servidor.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JPAUserDetails implements UserDetails {

	private String usuario;
	private String password;
	private boolean activo;
	private List<GrantedAuthority> authorities;

	public JPAUserDetails(Usuario usuario) {

		this.usuario = usuario.getUsername();
		this.password = usuario.getPassword();
		this.activo = usuario.isActivo();
		this.authorities = new ArrayList<>();				
		this.authorities.add(new SimpleGrantedAuthority(usuario.getId_role()));
		//this.authorities = new ArrayList<GrantedAuthority>();
		//this.authorities.add(new SimpleGrantedAuthority(usuario.getUsuarioRole().get(0).getRole().getRole_name()));

	}

	public Collection<? extends GrantedAuthority> getAuthorities() {

		return this.authorities;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.usuario;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return this.activo;
	}

}