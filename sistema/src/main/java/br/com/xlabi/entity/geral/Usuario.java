
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/* autor gerador de renan gomes .2017-01-31 00:52:37  */

@Entity
@Table(name = "usuario")
public class Usuario extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Size(min = 3, max = 45, message = "O nome deve ter no mínimo 3 no máximo 45 caracteres")
	@NotNull(message = "Informe o nome")
	@Column(name = "nome")
	private String nome;

	@Size(min = 6, max = 255, message = "A senha deve ter no mínimo 6 no máximo 50 caracteres")
	@Column(name = "senha")
	private String senha;

	@Transient
	private String novasenha;

	@Size(min = 4, max = 255, message = "O e-mail deve ter no mínimo 20 no máximo 255 caracteres")
	@Column(name = "email")
	@NotNull(message = "Informe o e-mail")
	private String email;
	
	@Column(name = "telefone")
	@NotNull(message = "Informe o telefone")
	private String telefone;

	@Column(name = "inativo")
	private Integer inativo;

	@Column(name = "criado")
	private Date criado;

	@Column(name = "atualizado")
	private Date atualizado;

	@Column(name = "externalid")
	private String externalid;

	@Column(name = "excluido")
	private Integer excluido;

	@JsonIgnore
	@Column(name = "contratante_id", updatable = false)
	private String contratante;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
	@JoinColumn(name = "role_id") })
	private List<Role> roles;

	@ManyToOne()
	@JoinColumn(name = "pasta_id", updatable = false)
	private Pasta pasta;
	
	@Transient
	private List<String> permissoes = new ArrayList<String>();
	
	public List<String> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<String> permissoes) {
		this.permissoes = permissoes;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Pasta getPasta() {
		return pasta;
	}

	public void setPasta(Pasta pasta) {
		this.pasta = pasta;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getInativo() {
		return inativo;
	}

	public void setInativo(Integer inativo) {
		this.inativo = inativo;
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

	public String getExternalid() {
		return externalid;
	}

	public void setExternalid(String externalid) {
		this.externalid = externalid;
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

	public String getNovasenha() {
		return novasenha;
	}

	public void setNovasenha(String novasenha) {
		this.novasenha = novasenha;
	}

	public String getDescricaoLog() {
		String item = "Arquivo :" + this.getNome();
		int maxLength = (item.length() < 200) ? item.length() : 200;
		return item.substring(0, maxLength);
	}

}