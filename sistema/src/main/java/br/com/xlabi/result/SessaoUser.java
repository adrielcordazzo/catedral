package br.com.xlabi.result;

import java.util.Map;

import br.com.xlabi.entity.geral.Contratante;
import br.com.xlabi.entity.geral.Usuario;

public class SessaoUser {

	Usuario usuario = null;
	Contratante contratante = null;
	Map<String, String> config = null;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public Map<String, String> getConfig() {
		return config;
	}

	public void setConfig(Map<String, String> config) {
		this.config = config;
	}

}
