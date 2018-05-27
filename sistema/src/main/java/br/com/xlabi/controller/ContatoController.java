
package br.com.xlabi.controller;

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

import br.com.xlabi.controller.geral.AbstractController;
import br.com.xlabi.entity.Contato;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.ContatoService;
import br.com.xlabi.service.geral.EmailSend;

@Controller

public class ContatoController extends AbstractController {

	@Autowired
	private ContatoService contatoService;
	
	
	@Autowired
	EmailSend emailServ;

	@RequestMapping(value = { "/contato/save", "/api/contato/save" }, method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody Contato contato, HttpServletRequest request,
			BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		if (contatoService.save(contato, sessao) != null) {
			
			String email = sessao.getContratante().getEmail();
			String assunto = sessao.getContratante().getNome() + " | Mensagem do Site";
			String mensagem = "";
			
			mensagem += "<strong>Nome:</strong> " + contato.getNome() + "<br>" +
					  "<strong>E-mail:</strong>" + contato.getEmail() + "<br>" +
					  "<strong>Telefone:</strong>" + contato.getTelefone() + "<br>" +
					  "<strong>Mensagem:</strong>" + contato.getTexto() + "<br>";
			emailServ.createEmail(email, null, assunto, mensagem);
			
			result.addSuccessMessage(getMessage("sucesso.save", new String[] { "contato", "" }));
			result.setData(contato);
		} else {
			returnStatus = HttpStatus.BAD_REQUEST;
			result.addErrorMessage(getMessage("error.save", new String[] { "contato", "" }));
		}

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/contato/form" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> form(HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}

		HashMap<String, Object> dados = new HashMap<String, Object>();

		dados.put("modelo", new Contato());

		result.setData(dados);
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/contato/{id}" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> delete(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Contato u = contatoService.get(id, sessao);
		result = referenciasDelete(u, result, sessao);
		if (!result.isValid()) {
			returnStatus = HttpStatus.OK;
			return new ResponseEntity<Result>(result, returnStatus);
		}
		if (contatoService.delete(id, sessao)) {
			result.setData(null);
			result.addSuccessMessage(getMessage("sucesso.delete", new String[] { "contato", "" }));
		} else {
			returnStatus = HttpStatus.NO_CONTENT;

			result.addError("get", getMessage("error.delete", new String[] { "contato", "" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/contato/deleteAll" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> deleteAll(@RequestBody String[] ids, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		List<String> retorno = new ArrayList<String>();

		Contato contato = new Contato();

		for (int i = 0; i < ids.length; i++) {
			contato = contatoService.get(ids[i], sessao);
			result = referenciasDelete(contato, result, sessao);
			if (!result.isValid()) {
				returnStatus = HttpStatus.OK;
				return new ResponseEntity<Result>(result, returnStatus);
			}
			if (contatoService.delete(ids[i], sessao)) {
				retorno.add(ids[i]);
			} else {
				returnStatus = HttpStatus.NO_CONTENT;
				result.addError("get", getMessage("error.delete", new String[] { "contato", "" }));
			}
		}

		result.setList(retorno);

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/contato/{id}" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> get(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Contato contato = contatoService.get(id, sessao);

		if (contato != null) {
			destroyProxyClass(contato);
			result.setData(contato);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addError("get", getMessage("error.find", new String[] { "contato" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/contato/list" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> list(@RequestBody PaginateForm pages, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}
		result = contatoService.list(pages, sessao);
		destroyProxyClassList(result.getList());

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/contato/listAll" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> listAll(HttpServletRequest request) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		result = contatoService.listAll(sessao);

		destroyProxyClassList(result.getList());
		return new ResponseEntity<Result>(result, returnStatus);
	}

	public Result referenciasDelete(Contato p, Result result, SessaoUser sessao) {

		Integer r = 0;

		return result;
	}

	public void destroyProxyClass(Contato p) {

	}

	public void destroyProxyClassList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				destroyProxyClass((Contato) p);
			}
		}
	}

}