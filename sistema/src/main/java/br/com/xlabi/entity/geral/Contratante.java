
package br.com.xlabi.entity.geral;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.xlabi.result.CpfCnpj;
import br.com.xlabi.result.JsonDateDeserializer;
import br.com.xlabi.result.JsonDateSerializer;

/* autor gerador de renan gomes .2017-02-14 13:40:09  */

@Entity
@Table(name = "contratante")
public class Contratante extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Column(name = "criado")
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date criado;

	@Column(name = "atualizado")
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date atualizado;

	@Column(name = "inativo")
	private Integer inativo;

	@Column(name = "excluido")
	private Integer excluido;

	@Column(name = "idexterno")
	private String idexterno;

	@Size(min = 3, max = 45, message = "O nome deve ter no mínimo 3 no máximo 45 caracteres")
	@NotNull(message = "O nome campo deve ser preenchido")
	@Column(name = "nome")
	private String nome;

	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "datavendimento")
	private Date datavencimento;

	@Size(min = 3, max = 45, message = "A razão social deve ter no mínimo 3 no máximo 45 caracteres")
	@Column(name = "razaosocial")
	private String razaosocial;

	@Column(name = "responsavel")
	@NotNull(message = "O responsavel deve ser preenchido")
	private String responsavel;

	@Column(name = "fone")
	@NotNull(message = "O Telefone deve ser preenchido")
	private String fone;

	@CpfCnpj(message = "CNPJ inválido")
	@Column(name = "cnpj")
	private String cnpj;

	@NotNull(message = "O E-mail deve ser preenchido")
	@Email(message = "E-mail incorreto")
	@Column(name = "email")
	private String email;

	@Column(name = "observacao")
	private String observacao;
	
	@Column(name = "marcadagua")
	private String marcadagua;
	
	@Column(name = "semfoto")
	private String semfoto;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "contratante_roles", joinColumns = {
			@JoinColumn(name = "contratante_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private List<Role> roles = new ArrayList<Role>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSemfoto() {
		return semfoto;
	}

	public void setSemfoto(String semfoto) {
		this.semfoto = semfoto;
	}

	public String getMarcadagua() {
		return marcadagua;
	}

	public void setMarcadagua(String marcadagua) {
		this.marcadagua = marcadagua;
	}

	public Date getCriado() {
		return criado;
	}

	public void setCriado(Date criado) {
		this.criado = criado;
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

	public String getIdexterno() {
		return idexterno;
	}

	public void setIdexterno(String idexterno) {
		this.idexterno = idexterno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDatavencimento() {
		return datavencimento;
	}

	public void setDatavencimento(Date datavencimento) {
		this.datavencimento = datavencimento;
	}

	public String getRazaosocial() {
		return razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@JsonIgnore
	public String getDescricaoLog() {
		String item = "Arquivo :" + this.getCriado();
		int maxLength = (item.length() < 200) ? item.length() : 200;
		return item.substring(0, maxLength);
	}

}