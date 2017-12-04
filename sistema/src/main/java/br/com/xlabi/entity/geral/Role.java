
package br.com.xlabi.entity.geral;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

/* autor gerador de renan gomes .2017-01-31 00:52:37  */

@Entity
@Table(name = "role")
public class Role extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Size(min = 3, max = 45, message = "O nome deve ter no mínimo 3 no máximo 45 caracteres")
	@Column(name = "role")

	private String role;
	// @Size(min = 3, max = 0, message = "O campo deve ter no mínimo 3 no máximo
	// 0 caracteres")
	@Column(name = "visivel")
	private Integer visivel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getVisivel() {
		return visivel;
	}

	public void setVisivel(Integer visivel) {
		this.visivel = visivel;
	}

	public String getDescricaoLog() {
		String item = "Arquivo :" + this.getRole();
		int maxLength = (item.length() < 200) ? item.length() : 200;
		return item.substring(0, maxLength);
	}

}