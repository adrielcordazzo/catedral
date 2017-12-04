package br.com.xlabi.controller.geral;
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
//import br.com.xlabi.entity.Users_roles;
//import br.com.xlabi.result.PaginateForm;
//import br.com.xlabi.result.Result;
//import br.com.xlabi.result.SessaoUser;
//import br.com.xlabi.service.Users_rolesService;
//
//@Controller
//
//public class Users_rolesController extends AbstractController {
//
//	@Autowired
//	private Users_rolesService users_rolesService;
//
//	@RequestMapping(value = { "/users_roles/save" }, method = { RequestMethod.POST,
//			RequestMethod.PUT }, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody Users_roles users_roles,
//			HttpServletRequest request, BindingResult bindingResult) {
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, bindingResult) != null) {
//			return super.acceptRequest(sessao, bindingResult);
//		}
//
//		if (users_rolesService.save(users_roles) != null) {
//			result.addSuccessMessage(getMessage("sucesso.save", new String[] { "users_roles", users_roles.getRole_id() }));
//			result.setData(users_roles);
//		} else {
//			returnStatus = HttpStatus.NO_CONTENT;
//			result.addErrorMessage(getMessage("error.save", new String[] { "users_roles", users_roles.getRole_id() }));
//		}
//
//		return new ResponseEntity<Result>(result, returnStatus);
//
//	}
//
//	@RequestMapping(value = { "/users_roles/{id}" }, method = RequestMethod.DELETE, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> delete(@PathVariable("id") String id, HttpServletRequest request) {
//
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, null) != null) {
//			return super.acceptRequest(sessao, null);
//		}
//		Users_roles u = users_rolesService.get(id, sessao);
//		result = referenciasDelete(u, result);
//		if (users_rolesService.delete(id, sessao)) {
//			result.setData(null);
//			result.addSuccessMessage(getMessage("sucesso.delete", new String[] { "users_roles", u.getRole_id() }));
//		} else {
//			returnStatus = HttpStatus.NO_CONTENT;
//
//			result.addError("get", getMessage("error.delete", new String[] { "users_roles", u.getRole_id() }));
//		}
//		return new ResponseEntity<Result>(result, returnStatus);
//	}
//
//	@RequestMapping(value = { "/users_roles/deleteAll" }, method = RequestMethod.DELETE, consumes = "application/json")
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
//		Users_roles users_roles = new Users_roles();
//
//		for (int i = 0; i < ids.length; i++) {
//			users_roles = users_rolesService.get(ids[i], sessao);
//			result = referenciasDelete(users_roles, result);
//
//			if (users_rolesService.delete(ids[i], sessao)) {
//				retorno.add(ids[i]);
//			} else {
//				returnStatus = HttpStatus.NO_CONTENT;
//				result.addError("get", getMessage("error.delete", new String[] { "usuário", users_roles.getRole_id() }));
//			}
//		}
//
//		result.setList(retorno);
//
//		return new ResponseEntity<Result>(result, returnStatus);
//	}
//
//	@RequestMapping(value = { "/users_roles/{id}" }, method = RequestMethod.GET, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> get(@PathVariable("id") String id, HttpServletRequest request) {
//
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, null) != null) {
//			return super.acceptRequest(sessao, null);
//		}
//		Users_roles users_roles = users_rolesService.get(id, sessao);
//
//		if (users_roles != null) {
//			destroyProxyClass(users_roles);
//			result.setData(users_roles);
//		} else {
//			returnStatus = HttpStatus.NO_CONTENT;
//			result.addError("get", getMessage("error.find", new String[] { "users_roles" }));
//		}
//		return new ResponseEntity<Result>(result, returnStatus);
//	}
//
//	@RequestMapping(value = { "/users_roles/list" }, method = RequestMethod.POST, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> list(@RequestBody PaginateForm pages, HttpServletRequest request,
//			BindingResult bindingResult) {
//
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, bindingResult) != null) {
//			return super.acceptRequest(sessao, bindingResult);
//		}
//		result = users_rolesService.list(pages, sessao);
//		// destroyProxyClassList(result.getList());
//
//		return new ResponseEntity<Result>(result, returnStatus);
//
//	}
//
//	@RequestMapping(value = { "/users_roles/listAll" }, method = RequestMethod.POST, consumes = "application/json")
//	public @ResponseBody ResponseEntity<Result> listAll(@RequestBody PaginateForm pages, HttpServletRequest request,
//			BindingResult bindingResult) {
//		Result result = new Result();
//		HttpStatus returnStatus = HttpStatus.OK;
//		SessaoUser sessao = this.verificaSessao(request);
//		if (super.acceptRequest(sessao, bindingResult) != null) {
//			return super.acceptRequest(sessao, bindingResult);
//		}
//
//		result = users_rolesService.listAll(sessao);
//		// destroyProxyClassList(result.getList());
//		return new ResponseEntity<Result>(result, returnStatus);
//	}
//
//	public Result referenciasDelete(Users_roles p, Result result) {
//
//		return result;
//	}
//
//	public void destroyProxyClass(Users_roles p) {
//
//	}
//
//	public void destroyProxyClassList(List<?> list) {
//		for (Object p : list) {
//			destroyProxyClass((Users_roles) p);
//		}
//	}
//
//}