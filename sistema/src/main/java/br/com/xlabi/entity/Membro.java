
package br.com.xlabi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
import br.com.xlabi.entity.geral.Pasta;
import br.com.xlabi.entity.geral.Usuario;
import br.com.xlabi.result.JsonBooleanToIntDeserializer;
import br.com.xlabi.result.JsonBooleanToIntSerializer;
import br.com.xlabi.result.JsonDateDeserializer;
import br.com.xlabi.result.JsonDateSerializer;

/* autor gerador de renan gomes .2017-08-29 20:35:29  */

@Entity
@Table(name = "membro")
public class Membro extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@NotNull(message = "nome precisa ser informado")
	@Column(name = "nome")
	@Size(max = 60, message = "O campo deve ter no máximo 60 caracteres")
	private String nome;
	
	@JsonDeserialize(using = JsonBooleanToIntDeserializer.class)
	@JsonSerialize(using = JsonBooleanToIntSerializer.class)
	@Column(name = "ativo")
	private Integer ativo;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "endereco")
	private String endereco;
	
	@Column(name = "datanascimento")
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date datanascimento;
	
	@Column(name = "estadocivil")
	private String estadocivil;
	
	@Column(name = "imagem")
	@Type(type = "text")
	private String imagem;
	
	@ManyToOne()
	@JoinColumn(name = "pasta_id")
	private Pasta pasta;
	
	@ManyToOne()
	@JoinColumn(name = "bairro_id")
	private Bairro bairro;
	
	@ManyToOne()
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	
	@ManyToOne()
	@JoinColumn(name = "estado_id")
	private Estado estado;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "rg")
	private String rg;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "obs")
	@Type(type = "text")
	private String obs;
	
	@Column(name = "numeroficha")
	private String numeroficha;
	
	@Column(name = "databatismo")
	private String databatismo;
	
	@Column(name = "pai")
	private String pai;
	
	@Column(name = "mae")
	private String mae;
	
	@Column(name = "conjuge")
	private String conjuge;
	
	@Column(name = "filhos")
	private String filhos;
	
	@Column(name = "escolaridade")
	private String escolaridade;
	
	@Column(name = "profissao")
	private String profissao;
	
	@Column(name = "recebidopor")
	private String recebidopor;
	
	@Column(name = "batizadoespiritosanto")
	private String batizadoespiritosanto;
	
	@Column(name = "dataentrada")
	private String dataentrada;
	
	@Column(name = "datasaida")
	private String datasaida;
	
	@ManyToOne()
	@JoinColumn(name = "contratante_id", updatable = false)
	@JsonIgnore
	private Contratante contratante;
	
	@ManyToOne()
	@JoinColumn(name = "usuario_id")
	@JsonIgnore
	private Usuario usuario;
	
	@OneToMany(mappedBy = "membro", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private List<Membrocargo> cargos = new ArrayList<Membrocargo>();

	public List<Membrocargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Membrocargo> cargos) {
		this.cargos = cargos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getEstadocivil() {
		return estadocivil;
	}

	public void setEstadocivil(String estadocivil) {
		this.estadocivil = estadocivil;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Pasta getPasta() {
		return pasta;
	}

	public void setPasta(Pasta pasta) {
		this.pasta = pasta;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getNumeroficha() {
		return numeroficha;
	}

	public void setNumeroficha(String numeroficha) {
		this.numeroficha = numeroficha;
	}

	public String getDatabatismo() {
		return databatismo;
	}

	public void setDatabatismo(String databatismo) {
		this.databatismo = databatismo;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getConjuge() {
		return conjuge;
	}

	public void setConjuge(String conjuge) {
		this.conjuge = conjuge;
	}

	public String getFilhos() {
		return filhos;
	}

	public void setFilhos(String filhos) {
		this.filhos = filhos;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRecebidopor() {
		return recebidopor;
	}

	public void setRecebidopor(String recebidopor) {
		this.recebidopor = recebidopor;
	}

	public String getBatizadoespiritosanto() {
		return batizadoespiritosanto;
	}

	public void setBatizadoespiritosanto(String batizadoespiritosanto) {
		this.batizadoespiritosanto = batizadoespiritosanto;
	}

	public String getDataentrada() {
		return dataentrada;
	}

	public void setDataentrada(String dataentrada) {
		this.dataentrada = dataentrada;
	}

	public String getDatasaida() {
		return datasaida;
	}

	public void setDatasaida(String datasaida) {
		this.datasaida = datasaida;
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