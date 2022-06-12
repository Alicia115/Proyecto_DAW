package org.fct.servidor.dto;

public class UsuarioAdminDTO {
	
	private Long id_usuario;
	private String id_role;
	
	public UsuarioAdminDTO() {
		
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	

	public String getId_role() {
		return id_role;
	}

	public void setId_role(String id_role) {
		this.id_role = id_role;
	}



}
