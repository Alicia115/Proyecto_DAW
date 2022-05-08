package org.fct.servidor.repository;


import java.util.Date;
import java.util.Optional;

import org.fct.servidor.model.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository<Eventos,Long>{
	
	public Optional<Eventos> findById(Long id_eventos);
	public Eventos findByTitulo (String titulo);
	public Eventos findByTipo(String tipo);
	public Eventos findByFecha(Date fecha);
	public Eventos findByLugar(String lugar);
}
