
package br.com.xlabi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Type;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Size;

import br.com.xlabi.result.JsonDateDeserializer;
import br.com.xlabi.result.JsonDateSerializer;

import br.com.xlabi.entity.geral.AbstractEntity;
import br.com.xlabi.entity.geral.Contratante;
import br.com.xlabi.entity.geral.Pasta;
import br.com.xlabi.entity.geral.Role;
import br.com.xlabi.entity.geral.Usuario;

import org.hibernate.annotations.GenericGenerator;

/* autor gerador de renan gomes .2017-08-25 19:43:23  */

@Entity
@Table(name = "eventomembro")
public class Eventomembro extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@ManyToOne()
	@JoinColumn(name = "contratante_id", updatable = false)
	@JsonIgnore
	private Contratante contratante;
	
	@ManyToOne()
	@JoinColumn(name = "usuario_id")
	@JsonIgnore
	private Usuario usuario;
	
	@ManyToOne()
	@JoinColumn(name = "evento_id")
	@JsonIgnore
	private Evento evento;
	
	@ManyToOne()
	@JoinColumn(name = "membro_id")
	@JsonIgnore
	private Membro membro;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Contratante getContratante() {
		return contratante;
	}



	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Evento getEvento() {
		return evento;
	}



	public void setEvento(Evento evento) {
		this.evento = evento;
	}



	public Membro getMembro() {
		return membro;
	}



	public void setMembro(Membro membro) {
		this.membro = membro;
	}



	@JsonIgnore
	public String getDescricaoLog() {
		String item = "Arquivo :";
		int maxLength = (item.length() < 200) ? item.length() : 200;
		return item.substring(0, maxLength);
	}

}