//
//package br.com.xlabi.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import br.com.xlabi.controller.geral.AbstractController;
//
//import br.com.xlabi.entity.Contratante_roles;
//import br.com.xlabi.result.PaginateForm;
//import br.com.xlabi.result.Result;
//import br.com.xlabi.result.SessaoUser;
//import br.com.xlabi.service.Contratante_rolesService;
//		
//
//
//@Controller
//
//public class Contratante_rolesController extends AbstractController {
//		
//	
//
//	@Autowired
//	private Contratante_rolesService contratante_rolesService;
//
//	@RequestMapping(value = { "/contratante_roles/save" }, method = { RequestMethod.POST,
//			RequestMethod.PUT }, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody Contratante_roles contratante_roles,
//			HttpServletRequest request, BindingResult bindingResult) {
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, bindingResult) != null) {
//			return super.acceptRequest(sessao, bindingResult);
//		}
//
//		if (contratante_rolesService.save(contratante_roles, sessao) != null) {
//			result.addSuccessMessage(
//					getMessage("sucesso.save", new String[] { "contratante_roles", contratante_roles.get() }));
//			result.setData(contratante_roles);
//		} else {
//			returnStatus = HttpStatus.NO_CONTENT;
//			result.addErrorMessage(
//					getMessage("error.save", new String[] { "contratante_roles", contratante_roles.get() }));
//		}
//
//		return new ResponseEntity<Result>(result, returnStatus);
//
//	}
//
//	@RequestMapping(value = { "/contratante_roles/{id}" }, method = RequestMethod.DELETE, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> delete(@PathVariable("id") String id, HttpServletRequest request) {
//
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, null) != null) {
//			return super.acceptRequest(sessao, null);
//		}
//		Contratante_roles u = contratante_rolesService.get(id, sessao);
//		result = referenciasDelete(u, result, sessao);
//		if(!result.isValid()){
//			returnStatus = HttpStatus.OK;
//			return new ResponseEntity<Result>(result, returnStatus);
//		}
//		if (contratante_rolesService.delete(id, sessao)) {
//			result.setData(null);
//			result.addSuccessMessage(getMessage("sucesso.delete", new String[] { "contratante_roles", u.get() }));
//		} else {
//			returnStatus = HttpStatus.NO_CONTENT;
//
//			result.addError("get", getMessage("error.delete", new String[] { "contratante_roles", u.get() }));
//		}
//		return new ResponseEntity<Result>(result, returnStatus);
//	}
//
//	@RequestMapping(value = { "/contratante_roles/deleteAll" }, method = RequestMethod.DELETE, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> deleteAll(@RequestBody String[] ids, HttpServletRequest request,
//			BindingResult bindingResult) {
//
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, bindingResult) != null) {
//			return super.acceptRequest(sessao, bindingResult);
//		}
//
//		List<String> retorno = new ArrayList<String>();
//
//		Contratante_roles contratante_roles = new Contratante_roles();
//
//		for (int i = 0; i < ids.length; i++) {
//			contratante_roles = contratante_rolesService.get(ids[i], sessao);
//			result = referenciasDelete(contratante_roles, result, sessao);
//			if(!result.isValid()){
//				returnStatus = HttpStatus.OK;
//				return new ResponseEntity<Result>(result, returnStatus);
//			}
//			if (contratante_rolesService.delete(ids[i], sessao)) {
//				retorno.add(ids[i]);
//			} else {
//				returnStatus = HttpStatus.NO_CONTENT;
//				result.addError("get",
//						getMessage("error.delete", new String[] { "contratante_roles", contratante_roles.get() }));
//			}
//		}
//
//		result.setList(retorno);
//
//		return new ResponseEntity<Result>(result, returnStatus);
//	}
//
//	@RequestMapping(value = { "/contratante_roles/{id}" }, method = RequestMethod.GET, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> get(@PathVariable("id") String id, HttpServletRequest request) {
//
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, null) != null) {
//			return super.acceptRequest(sessao, null);
//		}
//		Contratante_roles contratante_roles = contratante_rolesService.get(id, sessao);
//
//		if (contratante_roles != null) {
//			destroyProxyClass(contratante_roles);
//			result.setData(contratante_roles);
//		} else {
//			returnStatus = HttpStatus.NO_CONTENT;
//			result.addError("get", getMessage("error.find", new String[] { "contratante_roles" }));
//		}
//		return new ResponseEntity<Result>(result, returnStatus);
//	}
//
//	@RequestMapping(value = { "/contratante_roles/list" }, method = RequestMethod.POST, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> list(@RequestBody PaginateForm pages, HttpServletRequest request,
//			BindingResult bindingResult) {
//
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, bindingResult) != null) {
//			return super.acceptRequest(sessao, bindingResult);
//		}
//		result = contratante_rolesService.list(pages, sessao);
//		destroyProxyClassList(result.getList());
//
//		return new ResponseEntity<Result>(result, returnStatus);
//
//	}
//
//	@RequestMapping(value = { "/contratante_roles/listAll" }, method = RequestMethod.GET, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> listAll(HttpServletRequest request) {
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, null) != null) {
//			return super.acceptRequest(sessao, null);
//		}
//		result = contratante_rolesService.listAll(sessao);
//
//		destroyProxyClassList(result.getList());
//		return new ResponseEntity<Result>(result, returnStatus);
//	}
//
//	public Result referenciasDelete(Contratante_roles p, Result result, SessaoUser sessao) {
//			
//		Integer r = 0;
//
//		return result;
//	}
//
//	public void destroyProxyClass(Contratante_roles p) {
//
//	}
//
//	public void destroyProxyClassList(List<?> list) {
//		if(list != null){
//			for (Object p : list) {
//				destroyProxyClass((Contratante_roles) p);
//			}
//		}
//	}
//
//}
//
//package br.com.xlabi.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import br.com.xlabi.controller.geral.AbstractController;
//import br.com.xlabi.entity.Contratante_roles;
//import br.com.xlabi.result.PaginateForm;
//import br.com.xlabi.result.Result;
//import br.com.xlabi.result.SessaoUser;
//import br.com.xlabi.service.Contratante_rolesService;
//
//@Controller
//
//public class Contratante_rolesController extends AbstractController {
//
//	
//
//	@Autowired
//	private Contratante_rolesService contratante_rolesService;
//
//	@RequestMapping(value = { "/contratante_roles/save" }, method = { RequestMethod.POST,
//			RequestMethod.PUT }, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody Contratante_roles contratante_roles,
//			HttpServletRequest request, BindingResult bindingResult) {
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, bindingResult) != null) {
//			return super.acceptRequest(sessao, bindingResult);
//		}
//
//		if (contratante_rolesService.save(contratante_roles, sessao) != null) {
//			result.addSuccessMessage(getMessage("sucesso.save", new String[] { "contratante_roles", "" }));
//			result.setData(contratante_roles);
//		} else {
//			returnStatus = HttpStatus.NO_CONTENT;
//			result.addErrorMessage(getMessage("error.save", new String[] { "contratante_roles", "" }));
//		}
//
//		return new ResponseEntity<Result>(result, returnStatus);
//
//	}
//
//	@RequestMapping(value = { "/contratante_roles/form" }, method = RequestMethod.GET, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> form(HttpServletRequest request) {
//
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, null) != null) {
//			return super.acceptRequest(sessao, null);
//		}
//
//		HashMap<String, Object> dados = new HashMap<String, Object>();
//
//		dados.put("modelo", new Contratante_roles());
//		
//		result.setData(dados);
//		return new ResponseEntity<Result>(result, returnStatus);
//	}
//
//	@RequestMapping(value = { "/contratante_roles/{id}" }, method = RequestMethod.DELETE, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> delete(@PathVariable("id") String id, HttpServletRequest request) {
//
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, null) != null) {
//			return super.acceptRequest(sessao, null);
//		}
//		Contratante_roles u = contratante_rolesService.get(id, sessao);
//		result = referenciasDelete(u, result, sessao);
//		if (!result.isValid()) {
//			returnStatus = HttpStatus.OK;
//			return new ResponseEntity<Result>(result, returnStatus);
//		}
//		if (contratante_rolesService.delete(id, sessao)) {
//			result.setData(null);
//			result.addSuccessMessage(getMessage("sucesso.delete", new String[] { "contratante_roles", "" }));
//		} else {
//			returnStatus = HttpStatus.NO_CONTENT;
//
//			result.addError("get", getMessage("error.delete", new String[] { "contratante_roles", "" }));
//		}
//		return new ResponseEntity<Result>(result, returnStatus);
//	}
//
//	@RequestMapping(value = {
//			"/contratante_roles/deleteAll" }, method = RequestMethod.DELETE, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> deleteAll(@RequestBody String[] ids, HttpServletRequest request,
//			BindingResult bindingResult) {
//
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, bindingResult) != null) {
//			return super.acceptRequest(sessao, bindingResult);
//		}
//
//		List<String> retorno = new ArrayList<String>();
//
//		Contratante_roles contratante_roles = new Contratante_roles();
//
//		for (int i = 0; i < ids.length; i++) {
//			contratante_roles = contratante_rolesService.get(ids[i], sessao);
//			result = referenciasDelete(contratante_roles, result, sessao);
//			if (!result.isValid()) {
//				returnStatus = HttpStatus.OK;
//				return new ResponseEntity<Result>(result, returnStatus);
//			}
//			if (contratante_rolesService.delete(ids[i], sessao)) {
//				retorno.add(ids[i]);
//			} else {
//				returnStatus = HttpStatus.NO_CONTENT;
//				result.addError("get", getMessage("error.delete", new String[] { "contratante_roles", "" }));
//			}
//		}
//
//		result.setList(retorno);
//
//		return new ResponseEntity<Result>(result, returnStatus);
//	}
//
//	@RequestMapping(value = { "/contratante_roles/{id}" }, method = RequestMethod.GET, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> get(@PathVariable("id") String id, HttpServletRequest request) {
//
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, null) != null) {
//			return super.acceptRequest(sessao, null);
//		}
//		Contratante_roles contratante_roles = contratante_rolesService.get(id, sessao);
//
//		if (contratante_roles != null) {
//			destroyProxyClass(contratante_roles);
//			result.setData(contratante_roles);
//		} else {
//			returnStatus = HttpStatus.NO_CONTENT;
//			result.addError("get", getMessage("error.find", new String[] { "contratante_roles" }));
//		}
//		return new ResponseEntity<Result>(result, returnStatus);
//	}
//
//	@RequestMapping(value = { "/contratante_roles/list" }, method = RequestMethod.POST, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> list(@RequestBody PaginateForm pages, HttpServletRequest request,
//			BindingResult bindingResult) {
//
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, bindingResult) != null) {
//			return super.acceptRequest(sessao, bindingResult);
//		}
//		result = contratante_rolesService.list(pages, sessao);
//		destroyProxyClassList(result.getList());
//
//		return new ResponseEntity<Result>(result, returnStatus);
//
//	}
//
//	@RequestMapping(value = { "/contratante_roles/listAll" }, method = RequestMethod.GET, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> listAll(HttpServletRequest request) {
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, null) != null) {
//			return super.acceptRequest(sessao, null);
//		}
//		result = contratante_rolesService.listAll(sessao);
//
//		destroyProxyClassList(result.getList());
//		return new ResponseEntity<Result>(result, returnStatus);
//	}
//
//	public Result referenciasDelete(Contratante_roles p, Result result, SessaoUser sessao) {
//
//		Integer r = 0;
//
//		return result;
//	}
//
//	public void destroyProxyClass(Contratante_roles p) {
//
//	}
//
//	public void destroyProxyClassList(List<?> list) {
//		if (list != null) {
//			for (Object p : list) {
//				destroyProxyClass((Contratante_roles) p);
//			}
//		}
//	}
//
//}