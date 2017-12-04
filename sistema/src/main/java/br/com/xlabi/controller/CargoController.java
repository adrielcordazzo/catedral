
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
import br.com.xlabi.entity.Cargo;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.CargoService;
import br.com.xlabi.service.CidadeService;

@Controller

public class CargoController extends AbstractController {


	@Autowired
	private CargoService cargoService;

	@Autowired

	CidadeService cidadeServ;

	@RequestMapping(value = { "/cargo/save" }, method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody Cargo cargo, HttpServletRequest request,
			BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		if (cargoService.save(cargo, sessao) != null) {
			result.addSuccessMessage(getMessage("sucesso.save", new String[] { "cargo", "" }));
			result.setData(cargo);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addErrorMessage(getMessage("error.save", new String[] { "cargo", "" }));
		}

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/cargo/form" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> form(HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}

		HashMap<String, Object> dados = new HashMap<String, Object>();

		dados.put("modelo", new Cargo());
		dados.put("cidade", cidadeServ.listAllEntity(sessao));

		result.setData(dados);
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/cargo/{id}" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> delete(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Cargo u = cargoService.get(id, sessao);
		result = referenciasDelete(u, result, sessao);
		if (!result.isValid()) {
			returnStatus = HttpStatus.OK;
			return new ResponseEntity<Result>(result, returnStatus);
		}
		if (cargoService.delete(id, sessao)) {
			result.setData(null);
			result.addSuccessMessage(getMessage("sucesso.delete", new String[] { "cargo", "" }));
		} else {
			returnStatus = HttpStatus.NO_CONTENT;

			result.addError("get", getMessage("error.delete", new String[] { "cargo", "" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/cargo/deleteAll" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> deleteAll(@RequestBody String[] ids, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		List<String> retorno = new ArrayList<String>();

		Cargo cargo = new Cargo();

		for (int i = 0; i < ids.length; i++) {
			cargo = cargoService.get(ids[i], sessao);
			result = referenciasDelete(cargo, result, sessao);
			if (!result.isValid()) {
				returnStatus = HttpStatus.OK;
				return new ResponseEntity<Result>(result, returnStatus);
			}
			if (cargoService.delete(ids[i], sessao)) {
				retorno.add(ids[i]);
			} else {
				returnStatus = HttpStatus.NO_CONTENT;
				result.addError("get", getMessage("error.delete", new String[] { "cargo", "" }));
			}
		}

		result.setList(retorno);

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/cargo/{id}" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> get(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Cargo cargo = cargoService.get(id, sessao);

		if (cargo != null) {
			destroyProxyClass(cargo);
			result.setData(cargo);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addError("get", getMessage("error.find", new String[] { "cargo" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/cargo/list", "/api/cargo/list" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> list(@RequestBody PaginateForm pages, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}
		result = cargoService.list(pages, sessao);
		destroyProxyClassList(result.getList());

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/cargo/listAll","/api/cargo/listAll" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> listAll(HttpServletRequest request) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		result = cargoService.listAll(sessao);

		destroyProxyClassList(result.getList());
		return new ResponseEntity<Result>(result, returnStatus);
	}

	public Result referenciasDelete(Cargo p, Result result, SessaoUser sessao) {

		Integer r = 0;

		return result;
	}

	public void destroyProxyClass(Cargo p) {

	}

	public void destroyProxyClassList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				destroyProxyClass((Cargo) p);
			}
		}
	}

}