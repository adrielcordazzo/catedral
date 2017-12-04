package br.com.xlabi.entity.geral;
//
// package br.com.xlabi.entity;
//
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;
//
// import java.util.Date;
//
// import org.hibernate.annotations.Type;
//
// import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
// import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//
// import br.com.xlabi.result.JsonDateDeserializer;
// import br.com.xlabi.result.JsonDateSerializer;
//
// import org.codehaus.jackson.annotate.JsonIgnore;
// import org.hibernate.annotations.GenericGenerator;
//
/// * autor gerador de renan gomes .2017-01-31 00:52:37 */
//
// @Entity
// @Table(name = "users_roles")
// public class Users_roles extends AbstractEntity {
// private static final long serialVersionUID = 1L;
//
// @Id
// @GeneratedValue(generator = "system-uuid")
// @GenericGenerator(name = "system-uuid", strategy = "uuid")
// private String id;
//
//
// // @Size(min = 3, max = 0, message = "O campo deve ter no mínimo 3 no máximo
// 0 caracteres")
// @Column(name = "id")
// private Integer id;
// // @Size(min = 3, max = 255, message = "O campo deve ter no mínimo 3 no
// máximo 255 caracteres")
// @Column(name = "role_id")
//
// private String role_id;
// // @Size(min = 3, max = 255, message = "O campo deve ter no mínimo 3 no
// máximo 255 caracteres")
// @Column(name = "usuario_id")
//
// private String usuario_id;
//
// public String getId() {
// return id;
// }
//
// public void setId(String id) {
// this.id = id;
// }
// public Integer getId() {
// return id;
// }public void setId(Integer id) {
// this.id = id;
// }public String getRole_id() {
// return role_id;
// }public void setRole_id(String role_id) {
// this.role_id = role_id;
// }public String getUsuario_id() {
// return usuario_id;
// }public void setUsuario_id(String usuario_id) {
// this.usuario_id = usuario_id;
// }
//
//
//
// public String getDescricaoLog() {
// String item = "Arquivo :" + this.getRole_id();
// int maxLength = (item.length() < 200) ? item.length() : 200;
// return item.substring(0, maxLength);
// }
//
// }