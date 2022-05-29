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
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="eventos")
public class Eventos implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_evento;
	
	@Column(nullable=false)
	private String titulo;
	
	@Column(nullable=false)
	private String descripcion;
	
	@Column(name="fecha",nullable=false)
	private String fecha;
	
	@Column(nullable=false)
	private String lugar;
	
	@Column(nullable=false)
	private String tipo;
	
	@Column(nullable=false)
	private String hora;
	
	@Column(nullable=false)
	private Double coste;
	
	@Column()
	private String imagen;
	
	@OneToMany(mappedBy="eventos",cascade=CascadeType.ALL,orphanRemoval = true)
	private Set<Comentarios> comentariose = new HashSet<>();
	
	
	@OneToMany(mappedBy="eventValor",cascade=CascadeType.ALL,orphanRemoval = true)
	private Set<Valoracion> valoracione = new HashSet<>();
	
	@OneToMany(mappedBy="eventGuarEv",cascade=CascadeType.ALL,orphanRemoval = true)
	private Set<GuardarEvento> guardarEventoe = new HashSet<>();
	

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

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
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


	public Set<GuardarEvento> getGuardarEventoe() {
		return guardarEventoe;
	}


	public void setGuardarEventoe(Set<GuardarEvento> guardarEventoe) {
		this.guardarEventoe = guardarEventoe;
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
				+ ", lugar=" + lugar + ", tipo=" + tipo + ", hora=" + hora + ", coste=" + coste + "]";
	}
	
	
	
	//Métodos Helpers
		public void addComment(Usuario usuario, String titulo, String descripcion,LocalDate fecha) {
			Comentarios comentario = new Comentarios(usuario,this, titulo, descripcion, fecha);
			boolean encontrado=false;
			for (Comentarios comentarios : comentariose) {
				if(comentarios.getUsuario().getId_usuario() == comentario.getUsuario().getId_usuario() && 
						comentarios.getEvento().getId_evento()== comentario.getEvento().getId_evento()) {
					comentarios.setTitulo(titulo);
					comentarios.setDescripcion(descripcion);
					encontrado = true;
					break;
				}
			}
			if(!encontrado) {
				this.comentariose.add(comentario);
				usuario.getComentariosu().add(comentario);
			}
		}
		
		public void removeComment(Usuario usuario) {
			Comentarios comentario  = new Comentarios(usuario,this);
			usuario.getComentariosu().remove(comentario);
			this.comentariose.remove(comentario);		
		}
		
		public void addValoracion(Usuario usuario, String fecha, int puntuacion) {
			Valoracion valoracion = new Valoracion(usuario,this, fecha, puntuacion);
			this.valoracione.add(valoracion);
			usuario.getValoracionu().add(valoracion);
		}
		
		public void removeValoracion(Usuario usuario) {
			Valoracion valoracion  = new Valoracion(usuario,this);
			usuario.getComentariosu().remove(valoracion);
			this.valoracione.remove(valoracion);		
		}
		
		public void addGuardarEvento(Usuario usuario) {
			GuardarEvento guardaEvento = new GuardarEvento(usuario, this);
			boolean encontrado = false;
			for (GuardarEvento eventos : guardarEventoe) {
				if (eventos.getUserGuarEv().getId_usuario() == guardaEvento.getUserGuarEv().getId_usuario()
						&& eventos.getEventGuarEv().getId_evento() == guardaEvento.getEventGuarEv().getId_evento()) {
					encontrado = true;
					break;
				}
			}
			if (!encontrado) {
				this.guardarEventoe.add(guardaEvento);
				usuario.getGuardarEventou().add(guardaEvento);
			}
		}
		
		public void removeGuardarEvento(Usuario usuario) {
			GuardarEvento guardaEvento  = new GuardarEvento(usuario,this);
			boolean encontrado = false;
			for (GuardarEvento eventos : guardarEventoe) {
				if (eventos.getUserGuarEv().getId_usuario() == guardaEvento.getUserGuarEv().getId_usuario()
						&& eventos.getEventGuarEv().getId_evento() == guardaEvento.getEventGuarEv().getId_evento()) {
					encontrado = true;
					usuario.getGuardarEventou().remove(guardaEvento);
					this.guardarEventoe.remove(guardaEvento);	
					break;
				}
			}
				
		}
	
	

}
