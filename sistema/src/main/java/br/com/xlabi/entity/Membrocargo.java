
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
import br.com.xlabi.entity.geral.Usuario;
import br.com.xlabi.result.JsonDateDeserializer;
import br.com.xlabi.result.JsonDateSerializer;

/* autor gerador de renan gomes .2017-08-29 20:35:29  */

@Entity 
@Table(name = "membrocargo")
public class Membrocargo extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "membro_id")
	private Membro membro;
	
	@ManyToOne()
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	@JsonIgnore
	public String getDescricaoLog() {
		String item = "Arquivo :";
		int maxLength = (item.length() < 200) ? item.length() : 200;
		return item.substring(0, maxLength);
	}

}