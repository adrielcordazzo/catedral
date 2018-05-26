
package br.com.xlabi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.xlabi.entity.geral.AbstractEntity;
import br.com.xlabi.entity.geral.Contratante;
import br.com.xlabi.entity.geral.Usuario;
import br.com.xlabi.result.JsonDateDeserializer;
import br.com.xlabi.result.JsonDateSerializer;

/* autor gerador de renan gomes .2018-05-26 03:13:20  */

@Entity
@Table(name = "comentario")
public class Comentario extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@NotNull(message = "nome precisa ser informado")

	@Column(name = "nome")

	@Size(max = 255, message = "O campo deve ter no máximo 255 caracteres")
	private String nome;
	@Column(name = "email")

	@Size(max = 255, message = "O campo deve ter no máximo 255 caracteres")
	private String email;

	@NotNull(message = "mensagem precisa ser informado")

	@Column(name = "mensagem")

	@Type(type = "text")
	private String mensagem;
	
	@ManyToOne()
	@JoinColumn(name = "conteudo_id")
	private Conteudo conteudo;
	
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
	@JoinColumn(name = "usuario_id")
	@JsonIgnore
	private Usuario usuario;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
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