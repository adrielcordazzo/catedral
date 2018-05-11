
package br.com.xlabi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
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
@Table(name = "evento")
public class Evento extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@NotNull(message = "titulo precisa ser informado")
	@Column(name = "titulo")
	@Size(max = 255, message = "O campo deve ter no máximo 255 caracteres")
	private String titulo;
	
	@Column(name = "descricao")
	@Type(type = "text")
	private String descricao;
	
	@Column(name = "data")
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date data;
	
	@ManyToOne()
	@JoinColumn(name = "contratante_id", updatable = false)
	@JsonIgnore
	private Contratante contratante;
	
	@ManyToOne()
	@JoinColumn(name = "pasta_id", updatable = false)
	private Pasta pasta;
	
	@ManyToOne()
	@JoinColumn(name = "usuario_id")
	@JsonIgnore
	private Usuario usuario;
	
	@OneToMany(mappedBy = "evento", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<Eventomembro> membros = new ArrayList<Eventomembro>();

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public Date getData() {
		return data;
	}



	public void setData(Date data) {
		this.data = data;
	}



	public Contratante getContratante() {
		return contratante;
	}



	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}



	public Pasta getPasta() {
		return pasta;
	}



	public void setPasta(Pasta pasta) {
		this.pasta = pasta;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public List<Eventomembro> getMembros() {
		return membros;
	}



	public void setMembros(List<Eventomembro> membros) {
		this.membros = membros;
	}



	@JsonIgnore
	public String getDescricaoLog() {
		String item = "Arquivo :";
		int maxLength = (item.length() < 200) ? item.length() : 200;
		return item.substring(0, maxLength);
	}

}