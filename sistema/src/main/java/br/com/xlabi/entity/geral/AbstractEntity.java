package br.com.xlabi.entity.geral;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	public AbstractEntity(){
		
	}
	
	
	public abstract String getId();
	public abstract String  getDescricaoLog();

}