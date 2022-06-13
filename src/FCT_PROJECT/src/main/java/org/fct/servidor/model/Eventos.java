package org.fct.servidor.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "eventos")
public class Eventos implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_evento;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String descripcion;

	@Column(name = "fecha", nullable = false)
	private String fecha;

	@Column(nullable = false)
	private String lugar;

	@Column(nullable = false)
	private String tipo;

	@Column(nullable = false)
	private Double coste;

	@Column()
	private String imagen;
	
	@Column()
	private String url;

	@OneToMany(mappedBy = "eventos", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comentarios> comentariose = new HashSet<>();

	@OneToMany(mappedBy = "eventValor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Valoracion> valoracione = new HashSet<>();

	/*
	 * @OneToMany(mappedBy="eventGuarEv",cascade=CascadeType.ALL,orphanRemoval =
	 * true) private Set<GuardarEvento> guardarEventoe = new HashSet<>();
	 */

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "guardar_evento", joinColumns = { @JoinColumn(name = "id_evento") }, inverseJoinColumns = {
			@JoinColumn(name = "id_usuario") })
	private Set<Usuario> usuarios_guardados;

	public Eventos() {

	}

	public Long getId_evento() {
		return id_evento;
	}

	public void setId_evento(Long id_evento) {
		this.id_evento = id_evento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getCoste() {
		return coste;
	}

	public void setCoste(Double coste) {
		this.coste = coste;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Comentarios> getComentariose() {
		return comentariose;
	}

	public void setComentariose(Set<Comentarios> comentariose) {
		this.comentariose = comentariose;
	}

	public Set<Valoracion> getValoracione() {
		return valoracione;
	}

	public void setValoracione(Set<Valoracion> valoracione) {
		this.valoracione = valoracione;
	}

	public Set<Usuario> getUsuarios_guardados() {
		return usuarios_guardados;
	}

	public void setUsuarios_guardados(Set<Usuario> usuarios_guardados) {
		this.usuarios_guardados = usuarios_guardados;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_evento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Eventos other = (Eventos) obj;
		return Objects.equals(id_evento, other.id_evento);
	}

	@Override
	public String toString() {
		return "Eventos [id=" + id_evento + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", lugar=" + lugar + ", tipo=" + tipo + ",  coste=" + coste + "]";
	}

	// Métodos Helpers
	public void addComment(Usuario usuario, String titulo, String descripcion, LocalDate fecha) {
		Comentarios comentario = new Comentarios(usuario, this, titulo, descripcion, fecha);
		boolean encontrado = false;
		for (Comentarios comentarios : comentariose) {
			if (comentarios.getUsuario().getId_usuario() == comentario.getUsuario().getId_usuario()
					&& comentarios.getEvento().getId_evento() == comentario.getEvento().getId_evento()) {
				comentarios.setTitulo(titulo);
				comentarios.setDescripcion(descripcion);
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			this.comentariose.add(comentario);
			usuario.getComentariosu().add(comentario);
		}
	}

	public void removeComment(Usuario usuario) {
		
		for (Iterator<Comentarios> iterator = comentariose.iterator();
	             iterator.hasNext(); ) {
			Comentarios comentario = iterator.next();
	 
	            if (comentario.getEvento().equals(this) &&
	            		comentario.getUsuario().getId_usuario().equals(usuario.getId_usuario())) {
	                iterator.remove();
	                comentario.getUsuario().getComentariosu().remove(comentario);
	                comentario.setEvento(null);
	                comentario.setUsuario(null);
	            }
	        }
	}

	public void addValoracion(Usuario usuario, LocalDate fecha, int puntuacion) {
		
		Valoracion valoracion = new Valoracion(usuario, this, fecha, puntuacion);
		boolean encontrado = false;
		for (Valoracion valoraciones : valoracione) {
			if (valoraciones.getUsuario().getId_usuario() == valoracion.getUsuario().getId_usuario()
					&& valoraciones.getEvento().getId_evento() == valoracion.getEvento().getId_evento()) {
				valoraciones.setPuntuacion(puntuacion);
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			this.valoracione.add(valoracion);
			usuario.getValoracionu().add(valoracion);
		}
	}

	public void removeValoracion(Usuario usuario) {
		for (Iterator<Valoracion> iterator = valoracione.iterator();
	             iterator.hasNext(); ) {
			Valoracion valoracion = iterator.next();
	 
	            if (valoracion.getEvento().equals(this) &&
	            		valoracion.getUsuario().getId_usuario().equals(usuario.getId_usuario())) {
	                iterator.remove();
	                valoracion.getUsuario().getValoracionu().remove(valoracion);
	                valoracion.setEvento(null);
	                valoracion.setUsuario(null);
	            }
	        }
	}

	
	public void addGuardarEvento(Usuario usuario) {
        this.usuarios_guardados.add(usuario);
        usuario.getEventos_guardados().add(this);
    }
    public void removeGuardarEvento(Usuario usuario) {
        this.getUsuarios_guardados().remove(usuario);
        usuario.getEventos_guardados().remove(this);
    }

}
