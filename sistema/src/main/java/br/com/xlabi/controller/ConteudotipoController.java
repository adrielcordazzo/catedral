
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
import br.com.xlabi.entity.Conteudotipo;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.ConteudoService;
import br.com.xlabi.service.ConteudotipoService;

@Controller

public class ConteudotipoController extends AbstractController {

	@Autowired
				ConteudoService conteudoServ; 

	@Autowired
	private ConteudotipoService conteudotipoService;

	@RequestMapping(value = { "/conteudotipo/save" }, method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody Conteudotipo conteudotipo,
			HttpServletRequest request, BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		if (conteudotipoService.save(conteudotipo, sessao) != null) {
			result.addSuccessMessage(getMessage("sucesso.save", new String[] { "conteudotipo", "" }));
			result.setData(conteudotipo);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addErrorMessage(getMessage("error.save", new String[] { "conteudotipo", "" }));
		}

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/conteudotipo/form" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> form(HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}

		HashMap<String, Object> dados = new HashMap<String, Object>();

		dados.put("modelo", new Conteudotipo());
		
		result.setData(dados);
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/conteudotipo/{id}" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> delete(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Conteudotipo u = conteudotipoService.get(id, sessao);
		result = referenciasDelete(u, result, sessao);
		if (!result.isValid()) {
			returnStatus = HttpStatus.OK;
			return new ResponseEntity<Result>(result, returnStatus);
		}
		if (conteudotipoService.delete(id, sessao)) {
			result.setData(null);
			result.addSuccessMessage(getMessage("sucesso.delete", new String[] { "conteudotipo", "" }));
		} else {
			returnStatus = HttpStatus.NO_CONTENT;

			result.addError("get", getMessage("error.delete", new String[] { "conteudotipo", "" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = {
			"/conteudotipo/deleteAll" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> deleteAll(@RequestBody String[] ids, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		List<String> retorno = new ArrayList<String>();

		Conteudotipo conteudotipo = new Conteudotipo();

		for (int i = 0; i < ids.length; i++) {
			conteudotipo = conteudotipoService.get(ids[i], sessao);
			result = referenciasDelete(conteudotipo, result, sessao);
			if (!result.isValid()) {
				returnStatus = HttpStatus.OK;
				return new ResponseEntity<Result>(result, returnStatus);
			}
			if (conteudotipoService.delete(ids[i], sessao)) {
				retorno.add(ids[i]);
			} else {
				returnStatus = HttpStatus.NO_CONTENT;
				result.addError("get", getMessage("error.delete", new String[] { "conteudotipo", "" }));
			}
		}

		result.setList(retorno);

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/conteudotipo/{id}" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> get(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Conteudotipo conteudotipo = conteudotipoService.get(id, sessao);

		if (conteudotipo != null) {
			destroyProxyClass(conteudotipo);
			result.setData(conteudotipo);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addError("get", getMessage("error.find", new String[] { "conteudotipo" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/conteudotipo/list" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> list(@RequestBody PaginateForm pages, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}
		result = conteudotipoService.list(pages, sessao);
		destroyProxyClassList(result.getList());

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/conteudotipo/listAll" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> listAll(HttpServletRequest request) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		result = conteudotipoService.listAll(sessao);

		destroyProxyClassList(result.getList());
		return new ResponseEntity<Result>(result, returnStatus);
	}

	public Result referenciasDelete(Conteudotipo p, Result result, SessaoUser sessao) {

		Integer r = 0;

		return result;
	}

	public void destroyProxyClass(Conteudotipo p) {

	}

	public void destroyProxyClassList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				destroyProxyClass((Conteudotipo) p);
			}
		}
	}

}