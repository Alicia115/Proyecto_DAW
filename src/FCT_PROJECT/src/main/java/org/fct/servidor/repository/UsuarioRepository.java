package org.fct.servidor.repository;

import java.util.Optional;

import org.fct.servidor.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

	public Optional<Usuario> findByUsername(String username);
}
