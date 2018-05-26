
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.xlabi.entity.geral.AbstractEntity;
import br.com.xlabi.entity.geral.Contratante;
import br.com.xlabi.entity.geral.Pasta;
import br.com.xlabi.entity.geral.Usuario;
import br.com.xlabi.result.JsonDateDeserializer;
import br.com.xlabi.result.JsonDateSerializer;

/* autor gerador de renan gomes .2018-05-25 19:48:58  */

@Entity
@Table(name = "celula")
public class Celula extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@NotNull(message = "nome precisa ser informado")

	@Column(name = "nome")

	@Size(max = 255, message = "O campo deve ter no máximo 255 caracteres")
	private String nome;
	@Column(name = "descricao")

	@Size(max = 255, message = "O campo deve ter no máximo 255 caracteres")
	private String descricao;

	@NotNull(message = "dia precisa ser informado")

	@Column(name = "dia")

	@Size(max = 45, message = "O campo deve ter no máximo 45 caracteres")
	private String dia;

	@NotNull(message = "hora precisa ser informado")

	@Column(name = "hora")

	@Size(max = 45, message = "O campo deve ter no máximo 45 caracteres")
	private String hora;

	@ManyToOne()
	@JoinColumn(name = "pasta_id", updatable = false)
	private Pasta pasta;
	
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
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "latitude")
	private String latitude;
	
	@Column(name = "longitude")
	private String longitude;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Pasta getPasta() {
		return pasta;
	}

	public void setPasta(Pasta pasta) {
		this.pasta = pasta;
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
			String item = "Arquivo :"  ;
			int maxLength = (item.length() < 200) ? item.length() : 200;
			return item.substring(0, maxLength);
		}

}