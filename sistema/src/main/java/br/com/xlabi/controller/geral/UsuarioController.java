
package br.com.xlabi.controller.geral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.xlabi.configuration.SecurityUtil;
import br.com.xlabi.entity.Configuracaocampo;
import br.com.xlabi.entity.geral.Contratante;
import br.com.xlabi.entity.geral.Role;
import br.com.xlabi.entity.geral.Usuario;
import br.com.xlabi.form.FormLogin;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.ConfiguracaocampoService;
import br.com.xlabi.service.geral.ContratanteService;
import br.com.xlabi.service.geral.RoleService;
import br.com.xlabi.service.geral.UsuarioService;

@Controller

public class UsuarioController extends AbstractController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RoleService rolSer;

	@Autowired
	ContratanteService contraServ;

	@RequestMapping(value = { "/usuario/save" }, method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody Usuario usuario, HttpServletRequest request,
			BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		if (usuario.getNovasenha() != null) {
			String pass = SecurityUtil.getHash(usuario.getNovasenha(), "SHA-256");
			usuario.setSenha(pass);
		} else {
			if (usuario.getId() != null) {
				Usuario us = usuarioService.get(usuario.getId(), sessao);
				usuario.setSenha(us.getSenha());
			}
		}

		if (usuarioService.save(usuario, sessao) != null) {
			result.addSuccessMessage(getMessage("sucesso.save", new String[] { "usuario", usuario.getNome() }));
			result.setData(usuario);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addErrorMessage(getMessage("error.save", new String[] { "usuario", usuario.getNome() }));
		}

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/usuario/{id}" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> delete(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Usuario u = usuarioService.get(id, sessao);
		result = referenciasDelete(u, result);
		if (usuarioService.delete(id, sessao)) {
			result.setData(null);
			result.addSuccessMessage(getMessage("sucesso.delete", new String[] { "usuario", u.getNome() }));
		} else {
			returnStatus = HttpStatus.NO_CONTENT;

			result.addError("get", getMessage("error.delete", new String[] { "usuario", u.getNome() }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/usuario/deleteAll" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> deleteAll(@RequestBody String[] ids, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		List<String> retorno = new ArrayList<String>();

		Usuario usuario = new Usuario();

		for (int i = 0; i < ids.length; i++) {
			usuario = usuarioService.get(ids[i], sessao);
			result = referenciasDelete(usuario, result);

			if (usuarioService.delete(ids[i], sessao)) {
				retorno.add(ids[i]);
			} else {
				returnStatus = HttpStatus.NO_CONTENT;
				result.addError("get", getMessage("error.delete", new String[] { "usuario", usuario.getNome() }));
			}
		}

		result.setList(retorno);

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/usuario/form" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> form(HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}

		Contratante c = contraServ.get(sessao.getContratante().getId(), sessao);
		HashMap<String, Object> dados = new HashMap<String, Object>();
		// Result r = rolSer.listAll(sessao);
		List<Role> roles = c.getRoles();
		dados.put("modelo", new Usuario());
		dados.put("roles", rolSer.listAllEntity(sessao));

		result.setData(dados);
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/usuario/{id}" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Result> get(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Usuario usuario;

		usuario = usuarioService.get(id, sessao);

		if (usuario != null) {
			// destroyProxyClass(usuario);
			result.setData(usuario);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addError("get", getMessage("error.find", new String[] { "usuario" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@Autowired
	ConfiguracaocampoService configServ;

	@RequestMapping(value = { "/config/list" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> configlist(@RequestBody PaginateForm pages, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}
		result = configServ.list(pages, sessao);
		// destroyProxyClassList(result.getList());

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/config/save" }, method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> configsave(@Valid @RequestBody List<Configuracaocampo> conf,
			HttpServletRequest request, BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		configServ.deleteCampos(sessao.getContratante().getId());
		for (Configuracaocampo c : conf) {
			configServ.save(c, sessao);
		}

		result.addSuccessMessage("Dados atualizados");

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/usuario/list" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> list(@RequestBody PaginateForm pages, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}
		result = usuarioService.list(pages, sessao);
		// destroyProxyClassList(result.getList());

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/usuario/listAll" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> listAll(HttpServletRequest request) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		result = usuarioService.listAll(sessao);

		// destroyProxyClassList(result.getList());
		return new ResponseEntity<Result>(result, returnStatus);
	}

	public Result referenciasDelete(Usuario p, Result result) {

		return result;
	}

	public void destroyProxyClass(Usuario p) {

	}

	public void destroyProxyClassList(List<?> list) {
		for (Object p : list) {
			destroyProxyClass((Usuario) p);
		}
	}

	@RequestMapping(value = { "/usuario/login",
			"/api/usuario/login" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Result login(@RequestBody FormLogin formLogin) throws Exception {

		Result result = new Result();

		Usuario usuario = usuarioService.getEmail(formLogin.getEmail());

		String senha = SecurityUtil.getHash(formLogin.getSenha(), SecurityUtil.SHA_256);

		if (usuario.getSenha().equals(senha)) {
			usuario.setNovasenha(usuario.getContratante());
			result.setData(usuario);
		} else {
			result.addError("", "Usuário ou senha incorretos");
		}

		return result;

	}

}