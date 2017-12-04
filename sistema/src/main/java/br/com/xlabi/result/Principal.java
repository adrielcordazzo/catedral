package br.com.xlabi.result;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

import br.com.xlabi.entity.geral.Contratante;
import br.com.xlabi.entity.geral.Usuario;

public class Principal extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 1L;
	// Declare all custom attributes here
	private final Object id;
	private String name;
	private Usuario usuario;
	private Contratante contratante;
	Map<String, String> config = null;

	public Principal(Usuario user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<GrantedAuthority> authorities, Object id, String name,
			Map<String, String> config) {
		super(user.getEmail(), user.getSenha(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);

		// Initialize all the custom attributes here like the following.
		this.id = id;
		this.name = user.getNome();
		this.usuario = user;
		this.config = config;
		Contratante c = new Contratante();
		c.setId(user.getContratante());
		contratante = c;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Object getId() {
		return id;
	}

}
