
package br.com.xlabi.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
import br.com.xlabi.entity.Evento;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.EventoService;

@Controller

public class EventoController extends AbstractController {

	@Autowired
	private EventoService eventoService;

	@RequestMapping(value = { "/evento/criarEventos" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> criarEventos(HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}

	    Calendar calendar = Calendar.getInstance();
	    while(calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)){
	        // The switch checks the day of the week for Saturdays and Sundays
	        Evento evento = new Evento();
	    	switch(calendar.get(Calendar.DAY_OF_WEEK)){
		    	case Calendar.FRIDAY:
	        		evento.setData(calendar.getTime());
	        		evento.setHora("20:00");
	        		evento.setTitulo("Culto de Sexta-feira");
	        		evento.setDescricao("Culto de sexta-feira com o propósito da Corrente dos 7 elos.");
	        		eventoService.save(evento, sessao);
	        		System.out.println("SABADO " + calendar.getTime().toString());
	        		break;
	    		case Calendar.SATURDAY:
	        		evento.setData(calendar.getTime());
	        		evento.setHora("19:00");
	        		evento.setTitulo("Rede Jovem");
	        		evento.setDescricao("Encontro de Jovens");
	        		eventoService.save(evento, sessao);
	        		System.out.println("SABADO " + calendar.getTime().toString());
	        		break;
	        	case Calendar.SUNDAY:
	        		evento.setData(calendar.getTime());
	        		evento.setHora("19:00");
	        		evento.setTitulo("Culto de Domingo");
	        		evento.setDescricao("Culto de Domingo");
	        		eventoService.save(evento, sessao);
	        		System.out.println("DOMINGO " + calendar.getTime().toString());
	        		break;
	            
	        }
	        // Increment the day of the year for the next iteration of the while loop
	        calendar.add(Calendar.DAY_OF_YEAR, 1);
	    }

		return new ResponseEntity<Result>(result, returnStatus);

	}
	
	@RequestMapping(value = { "/evento/save" }, method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody Evento evento, HttpServletRequest request,
			BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		if (eventoService.save(evento, sessao) != null) {
			result.addSuccessMessage(getMessage("sucesso.save", new String[] { "evento", "" }));
			result.setData(evento);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addErrorMessage(getMessage("error.save", new String[] { "evento", "" }));
		}

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/evento/form" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> form(HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}

		HashMap<String, Object> dados = new HashMap<String, Object>();

		dados.put("modelo", new Evento());

		result.setData(dados);
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/evento/{id}" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> delete(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Evento u = eventoService.get(id, sessao);
		result = referenciasDelete(u, result, sessao);
		if (!result.isValid()) {
			returnStatus = HttpStatus.OK;
			return new ResponseEntity<Result>(result, returnStatus);
		}
		if (eventoService.delete(id, sessao)) {
			result.setData(null);
			result.addSuccessMessage(getMessage("sucesso.delete", new String[] { "evento", "" }));
		} else {
			returnStatus = HttpStatus.NO_CONTENT;

			result.addError("get", getMessage("error.delete", new String[] { "evento", "" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/evento/deleteAll" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> deleteAll(@RequestBody String[] ids, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		List<String> retorno = new ArrayList<String>();

		Evento evento = new Evento();

		for (int i = 0; i < ids.length; i++) {
			evento = eventoService.get(ids[i], sessao);
			result = referenciasDelete(evento, result, sessao);
			if (!result.isValid()) {
				returnStatus = HttpStatus.OK;
				return new ResponseEntity<Result>(result, returnStatus);
			}
			if (eventoService.delete(ids[i], sessao)) {
				retorno.add(ids[i]);
			} else {
				returnStatus = HttpStatus.NO_CONTENT;
				result.addError("get", getMessage("error.delete", new String[] { "evento", "" }));
			}
		}

		result.setList(retorno);

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/evento/{id}" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> get(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Evento evento = eventoService.get(id, sessao);

		if (evento != null) {
			destroyProxyClass(evento);
			result.setData(evento);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addError("get", getMessage("error.find", new String[] { "evento" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/evento/list","/api/evento/list" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> list(@RequestBody PaginateForm pages, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}
		result = eventoService.list(pages, sessao);
		destroyProxyClassList(result.getList());

		return new ResponseEntity<Result>(result, returnStatus);

	}
	
	@RequestMapping(value = { "/evento/listproximos","/api/evento/listproximos" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> listproximos(@RequestBody PaginateForm pages, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}
		System.out.println("BUSCA EVENTOS");
		result = eventoService.listproximos(pages, sessao);
		destroyProxyClassList(result.getList());

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/evento/listAll" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> listAll(HttpServletRequest request) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		result = eventoService.listAll(sessao);

		destroyProxyClassList(result.getList());
		return new ResponseEntity<Result>(result, returnStatus);
	}

	public Result referenciasDelete(Evento p, Result result, SessaoUser sessao) {

		Integer r = 0;

		return result;
	}

	public void destroyProxyClass(Evento p) {

	}

	public void destroyProxyClassList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				destroyProxyClass((Evento) p);
			}
		}
	}

}