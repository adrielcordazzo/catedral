
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
import br.com.xlabi.entity.geral.Usuario;
import br.com.xlabi.result.JsonBooleanToIntDeserializer;
import br.com.xlabi.result.JsonBooleanToIntSerializer;
import br.com.xlabi.result.JsonDateDeserializer;
import br.com.xlabi.result.JsonDateSerializer;

/* autor gerador de renan gomes .2017-09-18 22:03:27  */

@Entity
@Table(name = "campoconfiguracao")
public class Campoconfiguracao extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Column(name = "campo")

	@Size(max = 45, message = "O campo deve ter no máximo 45 caracteres")
	private String campo;
	@Column(name = "tipocampo")
	@JsonDeserialize(using = JsonBooleanToIntDeserializer.class)
	@JsonSerialize(using = JsonBooleanToIntSerializer.class)
	private Integer tipocampo;

	@Column(name = "obrigatorio")
	@JsonDeserialize(using = JsonBooleanToIntDeserializer.class)
	@JsonSerialize(using = JsonBooleanToIntSerializer.class)
	private Integer obrigatorio;
	@Column(name = "variavel")

	@Size(max = 200, message = "O campo deve ter no máximo 200 caracteres")
	private String variavel;
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

	@Column(name = "contratante_id")
	private String contratante;

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

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public Integer getTipocampo() {
		return tipocampo;
	}

	public void setTipocampo(Integer tipocampo) {
		this.tipocampo = tipocampo;
	}

	public Integer getObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(Integer obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public String getVariavel() {
		return variavel;
	}

	public void setVariavel(String variavel) {
		this.variavel = variavel;
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

	public String getContratante() {
		return contratante;
	}

	public void setContratante(String contratante) {
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