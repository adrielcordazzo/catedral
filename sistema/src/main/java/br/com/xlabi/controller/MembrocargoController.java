
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
import br.com.xlabi.entity.Membrocargo;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.MembrocargoService;
import br.com.xlabi.service.CidadeService;

@Controller

public class MembrocargoController extends AbstractController {


	@Autowired
	private MembrocargoService membrocargoService;

	@Autowired

	CidadeService cidadeServ;

	@RequestMapping(value = { "/membrocargo/save" }, method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody Membrocargo membrocargo, HttpServletRequest request,
			BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		if (membrocargoService.save(membrocargo, sessao) != null) {
			result.addSuccessMessage(getMessage("sucesso.save", new String[] { "membrocargo", "" }));
			result.setData(membrocargo);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addErrorMessage(getMessage("error.save", new String[] { "membrocargo", "" }));
		}

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/membrocargo/form" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> form(HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}

		HashMap<String, Object> dados = new HashMap<String, Object>();

		dados.put("modelo", new Membrocargo());
		dados.put("cidade", cidadeServ.listAllEntity(sessao));

		result.setData(dados);
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/membrocargo/{id}" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> delete(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Membrocargo u = membrocargoService.get(id, sessao);
		result = referenciasDelete(u, result, sessao);
		if (!result.isValid()) {
			returnStatus = HttpStatus.OK;
			return new ResponseEntity<Result>(result, returnStatus);
		}
		if (membrocargoService.delete(id, sessao)) {
			result.setData(null);
			result.addSuccessMessage(getMessage("sucesso.delete", new String[] { "membrocargo", "" }));
		} else {
			returnStatus = HttpStatus.NO_CONTENT;

			result.addError("get", getMessage("error.delete", new String[] { "membrocargo", "" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/membrocargo/deleteAll" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> deleteAll(@RequestBody String[] ids, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		List<String> retorno = new ArrayList<String>();

		Membrocargo membrocargo = new Membrocargo();

		for (int i = 0; i < ids.length; i++) {
			membrocargo = membrocargoService.get(ids[i], sessao);
			result = referenciasDelete(membrocargo, result, sessao);
			if (!result.isValid()) {
				returnStatus = HttpStatus.OK;
				return new ResponseEntity<Result>(result, returnStatus);
			}
			if (membrocargoService.delete(ids[i], sessao)) {
				retorno.add(ids[i]);
			} else {
				returnStatus = HttpStatus.NO_CONTENT;
				result.addError("get", getMessage("error.delete", new String[] { "membrocargo", "" }));
			}
		}

		result.setList(retorno);

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/membrocargo/{id}" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> get(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Membrocargo membrocargo = membrocargoService.get(id, sessao);

		if (membrocargo != null) {
			destroyProxyClass(membrocargo);
			result.setData(membrocargo);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addError("get", getMessage("error.find", new String[] { "membrocargo" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/membrocargo/list", "/api/membrocargo/list" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> list(@RequestBody PaginateForm pages, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}
		result = membrocargoService.list(pages, sessao);
		destroyProxyClassList(result.getList());

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/membrocargo/listAll","/api/membrocargo/listAll" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> listAll(HttpServletRequest request) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		result = membrocargoService.listAll(sessao);

		destroyProxyClassList(result.getList());
		return new ResponseEntity<Result>(result, returnStatus);
	}

	public Result referenciasDelete(Membrocargo p, Result result, SessaoUser sessao) {

		Integer r = 0;

		return result;
	}

	public void destroyProxyClass(Membrocargo p) {

	}

	public void destroyProxyClassList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				destroyProxyClass((Membrocargo) p);
			}
		}
	}

}