//
//	package br.com.xlabi.entity;
//
//	import javax.persistence.Column;
//	import javax.persistence.Entity;
//	import javax.persistence.GeneratedValue;
//	import javax.persistence.Id;
//	import javax.persistence.JoinColumn;
//	import javax.persistence.ManyToOne;
//	import javax.persistence.Table;
//
//	import java.util.Date;
//
//	import org.hibernate.annotations.Type;
//	import javax.validation.constraints.NotNull;
//
//	import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//	import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//
//	import com.fasterxml.jackson.annotation.JsonIgnore;
//	import javax.validation.constraints.Size;
//
//	import br.com.xlabi.result.JsonDateDeserializer;
//	import br.com.xlabi.result.JsonDateSerializer;
//
//	import br.com.xlabi.entity.geral.AbstractEntity;
//	import br.com.xlabi.entity.geral.Contratante;
//	import br.com.xlabi.entity.geral.Pasta;
//	import br.com.xlabi.entity.geral.Usuario;
//
//	import org.hibernate.annotations.GenericGenerator;
//
//	/* autor gerador de renan gomes .2017-09-13 13:14:42  */
//
//	@Entity
//	@Table(name = "contratante_roles")
//	public class Contratante_roles  extends AbstractEntity {
//		private static final long serialVersionUID = 1L;
//
//		@Id
//		@GeneratedValue(generator = "system-uuid")
//		@GenericGenerator(name = "system-uuid", strategy = "uuid")
//		private String id;
//
//		
//
//		@NotNull(message = "usuario_id precisa ser informado")
//		
//		@Column(name = "usuario_id")
//		
//			private String usuario_id;
//		@Column(name = "criado")
//		
//			@JsonDeserialize(using = JsonDateDeserializer.class)
//			@JsonSerialize(using = JsonDateSerializer.class)
//			private Date criado;
//		@Column(name = "externalid")
//		
//			private String externalid;
//		@Column(name = "atualizado")
//		
//			@JsonDeserialize(using = JsonDateDeserializer.class)
//			@JsonSerialize(using = JsonDateSerializer.class)
//			private Date atualizado;
//		@Column(name = "inativo")
//		
//			private Integer inativo;
//		@Column(name = "excluido")
//		
//			private Integer excluido;
//	
//		
//		@ManyToOne()
//			@JoinColumn(name = "contratante_id", updatable = false)@JsonIgnore
//		private Contratante contratante;
//		@ManyToOne()
//			@JoinColumn(name = "role_id")
//		private Role role;
//		public String getId() {
//			return id;
//		}
//
//		public void setId(String id) {
//			this.id = id;
//		}
//		public String getUsuario_id() {
//				return usuario_id;
//			}
//			public void setUsuario_id(String usuario_id) {
//				this.usuario_id = usuario_id;
//			}
//			public Date getCriado() {
//				return criado;
//			}
//			public void setCriado(Date criado) {
//				this.criado = criado;
//			}
//			public String getExternalid() {
//				return externalid;
//			}
//			public void setExternalid(String externalid) {
//				this.externalid = externalid;
//			}
//			public Date getAtualizado() {
//				return atualizado;
//			}
//			public void setAtualizado(Date atualizado) {
//				this.atualizado = atualizado;
//			}
//			public Integer getInativo() {
//				return inativo;
//			}
//			public void setInativo(Integer inativo) {
//				this.inativo = inativo;
//			}
//			public Integer getExcluido() {
//				return excluido;
//			}
//			public void setExcluido(Integer excluido) {
//				this.excluido = excluido;
//			}
//			
//		public Contratante getContratante() {
//			return contratante;
//		}
//		public void setContratante(Contratante contratante) {
//			this.contratante = contratante;
//		}
//		public Role getRole() {
//			return role;
//		}
//		public void setRole(Role role) {
//			this.role = role;
//		}
//		
//
//
//		@JsonIgnore
//		public String getDescricaoLog() {
//			String item = "Arquivo :" ;
//			int maxLength = (item.length() < 200) ? item.length() : 200;
//			return item.substring(0, maxLength);
//		}
//
//	}