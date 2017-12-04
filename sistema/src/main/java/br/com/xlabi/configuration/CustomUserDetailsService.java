package br.com.xlabi.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.xlabi.entity.Configuracaocampo;
import br.com.xlabi.entity.geral.Contratante;
import br.com.xlabi.entity.geral.Role;
import br.com.xlabi.entity.geral.Usuario;
import br.com.xlabi.result.Principal;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.ConfiguracaocampoService;
import br.com.xlabi.service.geral.UsuarioService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UsuarioService userService;

	@Autowired
	private ConfiguracaocampoService confCampoServ;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {

		Usuario user = userService.getEmail(ssoId);
		Map<String, String> config = null;
		logger.info("Usuario : {}", user.getEmail());
		if (user == null) {
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		} else {
			config = getConfiguracao(confCampoServ.listAllEntityContratante(user.getContratante()));
		}

		return new Principal(user, true, true, true, true, getGrantedAuthorities(user), user.getId(), user.getEmail(),
				config);
	}

	public Map<String, String> getConfiguracao(List<Configuracaocampo> config) {

		Map<String, String> map = new HashMap<String, String>();
		for (Configuracaocampo c : config) {
			map.put(c.getCampo().getVariavel(), c.getValor());
		}

		return map;

	}

	private List<GrantedAuthority> getGrantedAuthorities(Usuario user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role userProfile : user.getRoles()) {
			logger.info("UserProfile : {}", userProfile);
			authorities.add(new SimpleGrantedAuthority(userProfile.getRole()));
		}
		authorities.add(new SimpleGrantedAuthority(user.getContratante()));
		logger.info("authorities : {}", authorities);
		return authorities;
	}

}
