//
// package br.com.xlabi.entity.geral;
//
// import java.util.Date;
//
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;
//
// import org.hibernate.annotations.GenericGenerator;
//
// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
// import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//
// import br.com.xlabi.result.JsonDateDeserializer;
// import br.com.xlabi.result.JsonDateSerializer;
//
/// * autor gerador de renan gomes .2017-02-14 13:40:09 */
// @Entity
// @Table(name = "configuracao")
// public class Configuracao extends AbstractEntity {
// private static final long serialVersionUID = 1L;
//
// @Id
// @GeneratedValue(generator = "system-uuid")
// @GenericGenerator(name = "system-uuid", strategy = "uuid")
// private String id;
//
// @Column(name = "CONFIGNAME")
// private String CONFIGNAME;
//
// @Column(name = "CONFIGVALUE")
// private String CONFIGVALUE;
//
// @Column(name = "CONFIGDESC")
// private String CONFIGDESC;
//
// @Column(name = "CONFIGTYPE")
// private String CONFIGTYPE;
//
// @Column(name = "criado")
// @JsonDeserialize(using = JsonDateDeserializer.class)
// @JsonSerialize(using = JsonDateSerializer.class)
// private Date criado;
//
// @Column(name = "atualizado")
// @JsonDeserialize(using = JsonDateDeserializer.class)
// @JsonSerialize(using = JsonDateSerializer.class)
// private Date atualizado;
//
// @ManyToOne()
// @JsonIgnore
// @JoinColumn(name = "contratante_id", updatable = false)
// private Contratante contratante;
//
// public String getId() {
// return id;
// }
//
// public void setId(String id) {
// this.id = id;
// }
//
// public String getCONFIGNAME() {
// return CONFIGNAME;
// }
//
// public void setCONFIGNAME(String cONFIGNAME) {
// CONFIGNAME = cONFIGNAME;
// }
//
// public String getCONFIGVALUE() {
// return CONFIGVALUE;
// }
//
// public void setCONFIGVALUE(String cONFIGVALUE) {
// CONFIGVALUE = cONFIGVALUE;
// }
//
// public String getCONFIGTYPE() {
// return CONFIGTYPE;
// }
//
// public String getCONFIGDESC() {
// return CONFIGDESC;
// }
//
// public void setCONFIGDESC(String cONFIGDESC) {
// CONFIGDESC = cONFIGDESC;
// }
//
// public void setCONFIGTYPE(String cONFIGTYPE) {
// CONFIGTYPE = cONFIGTYPE;
// }
//
// public Date getCriado() {
// return criado;
// }
//
// public void setCriado(Date criado) {
// this.criado = criado;
// }
//
// public Contratante getContratante() {
// return contratante;
// }
//
// public Date getAtualizado() {
// return atualizado;
// }
//
// public void setAtualizado(Date atualizado) {
// this.atualizado = atualizado;
// }
//
// public void setContratante(Contratante contratante) {
// this.contratante = contratante;
// }
//
// @JsonIgnore
// public String getDescricaoLog() {
// String item = "Arquivo :" + this.getCriado();
// int maxLength = (item.length() < 200) ? item.length() : 200;
// return item.substring(0, maxLength);
// }
//
// }
