
package br.com.xlabi.entity.geral;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/* autor gerador de renan gomes .2017-01-31 00:52:37  */

@Entity
@Table(name = "arquivo")
public class Arquivo extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	// @Size(min = 3, max = 45, message = "O campo deve ter no mínimo 3 no
	// máximo 45 caracteres")
	@Column(name = "criado")

	private String criado;
	// @Size(min = 3, max = 45, message = "O campo deve ter no mínimo 3 no
	// máximo 45 caracteres")
	@Column(name = "atualizado")

	private String atualizado;
	// @Size(min = 3, max = 45, message = "O campo deve ter no mínimo 3 no
	// máximo 45 caracteres")
	@Column(name = "inativo")

	private String inativo;
	// @Size(min = 3, max = 45, message = "O campo deve ter no mínimo 3 no
	// máximo 45 caracteres")
	@Column(name = "excluido")

	private String excluido;
	// @Size(min = 3, max = 45, message = "O campo deve ter no mínimo 3 no
	// máximo 45 caracteres")
	@Column(name = "idexterno")
	private String idexterno;

	@Column(name = "ordena")
	private Integer ordena;

	// @Size(min = 3, max = 45, message = "O campo deve ter no mínimo 3 no
	// máximo 45 caracteres")
	@Column(name = "nome")

	private String nome;

	@Column(name = "legenda")
	private String legenda;

	// @Size(min = 3, max = 45, message = "O campo deve ter no mínimo 3 no
	// máximo 45 caracteres")
	@Column(name = "extensao")

	private String extensao;
	// @Size(min = 3, max = 45, message = "O campo deve ter no mínimo 3 no
	// máximo 45 caracteres")
	@Column(name = "tipo")

	private String tipo;
	// @Size(min = 3, max = 45, message = "O campo deve ter no mínimo 3 no
	// máximo 45 caracteres")
	@Column(name = "tamanho")

	private String tamanho;
	// @Size(min = 3, max = 45, message = "O campo deve ter no mínimo 3 no
	// máximo 45 caracteres")
	@Column(name = "arquivo")

	private String arquivo;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "contratante_id", updatable = false)
	private Contratante contratante;
	@ManyToOne()
	@JoinColumn(name = "pasta_id", updatable = false)
	private Pasta pasta;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Column(name = "principal")
	private String principal;

	@Column(name = "mascaraalign")
	private String mascaraalign;

	public Integer getOrdena() {
		return ordena;
	}

	public void setOrdena(Integer ordena) {
		this.ordena = ordena;
	}

	public String getMascaraalign() {
		return mascaraalign;
	}

	public void setMascaraalign(String mascaraalign) {
		this.mascaraalign = mascaraalign;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public String getCriado() {
		return criado;
	}

	public void setCriado(String criado) {
		this.criado = criado;
	}

	public String getAtualizado() {
		return atualizado;
	}

	public void setAtualizado(String atualizado) {
		this.atualizado = atualizado;
	}

	public String getInativo() {
		return inativo;
	}

	public void setInativo(String inativo) {
		this.inativo = inativo;
	}

	public String getExcluido() {
		return excluido;
	}

	public void setExcluido(String excluido) {
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

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
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

	public String getDescricaoLog() {
		String item = "Arquivo :" + this.getCriado();
		int maxLength = (item.length() < 200) ? item.length() : 200;
		return item.substring(0, maxLength);
	}

}