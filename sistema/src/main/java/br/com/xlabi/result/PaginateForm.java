package br.com.xlabi.result;

import java.util.ArrayList;
import java.util.List;

public class PaginateForm {

	private int maxResult;
	private int pagina;
	private int order = 0;
	private String ordercampo;
	private List<String> campos = new ArrayList<String>();
	private List<String> values = new ArrayList<String>();
	private String search;
	private String alias = null;
	private String alias2 = null;
	private Integer mes;
	private Integer ano;
	
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public int getMaxResult() {
		return maxResult;
	}
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	public int getPagina() {
		return pagina;
	}
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getOrdercampo() {
		return ordercampo;
	}
	public void setOrdercampo(String ordercampo) {
		this.ordercampo = ordercampo;
	}
	public List<String> getCampos() {
		return campos;
	}
	public void setCampos(List<String> campos) {
		this.campos = campos;
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAlias2() {
		return alias2;
	}
	public void setAlias2(String alias2) {
		this.alias2 = alias2;
	}



	

}
