
package br.com.xlabi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.xlabi.entity.geral.AbstractEntity;
import br.com.xlabi.entity.geral.Contratante;
import br.com.xlabi.entity.geral.Pasta;
import br.com.xlabi.entity.geral.Usuario;
import br.com.xlabi.result.JsonDateDeserializer;
import br.com.xlabi.result.JsonDateSerializer;

/* autor gerador de renan gomes .2017-08-29 20:35:29  */

@Entity
@Table(name = "banner")
public class Banner extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Column(name = "titulo")
	@Size(max = 255, message = "O campo deve ter no máximo 255 caracteres")
	private String titulo;
	
	@Column(name = "link")
	@Size(max = 255, message = "O campo deve ter no máximo 255 caracteres")
	private String link;
	
	@Column(name = "criado")
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date criado;
	
	@Column(name = "externalid")
	private String externalid;
	
	@Column(name = "atualizado")
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date atualizado;
	
	@Column(name = "inativo")
	private Integer inativo;
	
	@Column(name = "excluido")
	private Integer excluido;

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
	
	@Column(name = "texto1")
	@Size(max = 255, message = "O campo deve ter no máximo 255 caracteres")
	private String texto1;
	
	@Column(name = "texto2")
	@Size(max = 255, message = "O campo deve ter no máximo 255 caracteres")
	private String texto2;
	
	@Column(name = "ladotexto")
	private String ladotexto;

	public String getLadotexto() {
		return ladotexto;
	}

	public void setLadotexto(String ladotexto) {
		this.ladotexto = ladotexto;
	}

	public String getTexto1() {
		return texto1;
	}

	public void setTexto1(String texto1) {
		this.texto1 = texto1;
	}

	public String getTexto2() {
		return texto2;
	}

	public void setTexto2(String texto2) {
		this.texto2 = texto2;
	}

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getCriado() {
		return criado;
	}

	public void setCriado(Date criado) {
		this.criado = criado;
	}

	public String getExternalid() {
		return externalid;
	}

	public void setExternalid(String externalid) {
		this.externalid = externalid;
	}

	public Date getAtualizado() {
		return atualizado;
	}

	public void setAtualizado(Date atualizado) {
		this.atualizado = atualizado;
	}

	public Integer getInativo() {
		return inativo;
	}

	public void setInativo(Integer inativo) {
		this.inativo = inativo;
	}

	public Integer getExcluido() {
		return excluido;
	}

	public void setExcluido(Integer excluido) {
		this.excluido = excluido;
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

	@JsonIgnore
	public String getDescricaoLog() {
		String item = "Arquivo :";
		int maxLength = (item.length() < 200) ? item.length() : 200;
		return item.substring(0, maxLength);
	}

}