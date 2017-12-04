package br.com.xlabi.controller.geral;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.xlabi.entity.Configuracaocampo;
import br.com.xlabi.entity.geral.AbstractEntity;
import br.com.xlabi.entity.geral.Contratante;
import br.com.xlabi.entity.geral.Usuario;
import br.com.xlabi.result.Principal;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.ConfiguracaocampoService;
import br.com.xlabi.service.geral.ContratanteService;
import br.com.xlabi.service.geral.UsuarioService;

public class AbstractController {

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	MessageSource messageSource;

	@Autowired
	UsuarioService userService;

	@Autowired
	ContratanteService contraServ;

	@Autowired
	private ConfiguracaocampoService confCampoServ;

	private WebDataBinder dataBinder;

	public Map<String, String> getConfiguracao(List<Configuracaocampo> config) {

		Map<String, String> map = new HashMap<String, String>();
		for (Configuracaocampo c : config) {
			map.put(c.getCampo().getVariavel(), c.getValor());
		}

		return map;

	}

	protected String getMessage(String caminho, String[] items) {
		return messageSource.getMessage(caminho, items, Locale.getDefault());
	}

	protected boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	protected String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof Principal) {
			userName = ((Principal) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	private Usuario getUsuarioSession() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof Principal) {
			return ((Principal) principal).getUsuario();
		}
		return null;
	}

	private Map<String, String> getConfigSession() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof Principal) {
			return ((Principal) principal).getConfig();
		}
		return null;
	}

	private Contratante getContratanteSession() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof Principal) {
			return ((Principal) principal).getContratante();
		}
		return null;
	}

	protected Result extractErrors(BindingResult validation) {
		Result response = new Result();
		if (validation.hasErrors()) {
			response.addErrorMessage("Operação não pode ser completada");
			for (FieldError error : validation.getFieldErrors()) {
				response.addError(error.getField(), error.getDefaultMessage());
			}
		}
		return response;
	}

	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		this.dataBinder = dataBinder;
	}

	protected BindingResult bindAndValidate(AbstractEntity model, WebRequest request) {
		WebRequestDataBinder binder = new WebRequestDataBinder(model);
		binder.addValidators((Validator[]) dataBinder.getValidators().toArray());
		binder.bind(request);
		binder.validate();
		BindingResult bindingResult = binder.getBindingResult();
		return bindingResult;
	}

	protected SessaoUser verificaSessao(HttpServletRequest request) {
		Usuario usuario = getUsuarioSession();
		Contratante contratante = getContratanteSession();
		SessaoUser sessao = new SessaoUser();
		// if (usuario != null && contratante !=null) {
		if (usuario != null) {
			sessao.setUsuario(usuario);
			sessao.setContratante(contratante);
			sessao.setConfig(getConfigSession());
			return sessao;
		}

		String tokenapp = request.getHeader("tokenapp");
		String tokenuser = request.getHeader("tokenuser");
		System.out.println(tokenuser);
		System.out.println(tokenapp);
		System.out.println("verifica token");

		if ((tokenapp != null && tokenuser != null)) {
			if (tokenapp != null) {
				usuario = userService.getUser(tokenuser);
			}

			if (usuario != null) {
				sessao.setUsuario(usuario);
				sessao.setContratante(contraServ.get(usuario.getContratante(), sessao));
				sessao.setConfig(getConfiguracao(confCampoServ.listAllEntityContratante(usuario.getContratante())));
				return sessao;
			}
		}

		return null;
	}

	protected ResponseEntity<Result> acceptRequest(SessaoUser sessao, BindingResult bindingResult) {
		Result result = null;
		if (sessao == null) {
			return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
		}
		if (bindingResult != null) {
			result = extractErrors(bindingResult);
			if (!result.isValid()) {
				return new ResponseEntity<Result>(result, HttpStatus.CONFLICT);
			}
		}

		return null;
	}

	protected String objectToJson(Object o) {

		ObjectMapper ow = new ObjectMapper();
		String json = "";
		try {
			json = ow.writeValueAsString(o);
			json = ow.writeValueAsString(json);
			return json;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	protected byte[] returnFile(String url, String type, String filename, HttpServletResponse response) {
		byte[] bytes = null;

		try {
			InputStream input = new URL(url).openStream();
			bytes = IOUtils.toByteArray(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("filename=\"%s\"", filename);
		response.setHeader(headerKey, headerValue);

		// para funcionar o play do video
		// response.addHeader("Content-Range", "bytes " + 0 + "-" + bytes.length
		// + "/" + bytes.length);
		// response.addHeader("Accept-Ranges", "bytes");
		// response.addHeader("Connection", "Keep-Alive");
		// response.addHeader("Keep-Alive", "timeout=5, max=98");

		response.setContentLength(bytes.length);
		response.setContentType(type);
		try {
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bytes;
	}

}
