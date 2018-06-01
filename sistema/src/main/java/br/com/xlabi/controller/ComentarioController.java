
package br.com.xlabi.controller;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.xlabi.controller.geral.AbstractController;

import br.com.xlabi.entity.Comentario;
import br.com.xlabi.entity.Conteudo;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.ComentarioService;
import br.com.xlabi.service.ConteudoService;

@Controller

public class ComentarioController extends AbstractController {

	@Autowired
	private ComentarioService comentarioService;
	
	@Autowired
	ConteudoService conteudoServ;
	
	@RequestMapping(value = "/comentario/upload", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Result> uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

		Result result = new Result();
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
			String myString = IOUtils.toString(stream, "UTF-8");

			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonRetorno = mapper.readTree(myString);
			JsonNode array = jsonRetorno;//.get("data");
			Gson gson = new Gson();
			if (array.isArray()) {
				for (JsonNode j : array) {

					Comentario i = new Comentario();
					
					if(j.get("id") != null) {
						i.setExternalid(j.get("id").asText());
					}
					
					if(j.get("nome") != null) {
						i.setNome(j.get("nome").asText());
					}
					
					if(j.get("email") != null) {
						i.setEmail(j.get("email").asText());
					}
					
					if(j.get("descricao") != null) {
						i.setMensagem(j.get("descricao").asText());
					}
					
					if(j.get("id_artigo") != null) {
						Conteudo c = conteudoServ.externalid(j.get("id_artigo").asText(), sessao);
						if(c != null) {
							i.setConteudo(c);
						}
					}
					
					if(!j.get("data").asText().equals("0000-00-00 00:00:00")) {
						String[] dataparts = j.get("data").asText().split(" ");
						String[] data = dataparts[0].split("-");
						DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						String dataformatada = data[2]+"/"+data[1]+"/"+data[0];
						System.out.println("DATA FORMATADA " + dataformatada);
						System.out.println("ARTIGO " + i.getNome() + " " + i.getId());
						Date date = (Date)formatter.parse(dataformatada);
						i.setCriado(date);
					}
					
					comentarioService.save(i, sessao);
				}
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			result.addError("file", "erro ao fazer o upload");
		}

		HttpStatus returnStatus = HttpStatus.OK;

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/comentario/save", "/api/comentario/save" }, method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody Comentario comentario,
			HttpServletRequest request, BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		if (comentarioService.save(comentario, sessao) != null) {
			result.addSuccessMessage(getMessage("sucesso.save", new String[] { "comentario", comentario.getId() }));
			result.setData(comentario);
		} else {
			returnStatus = HttpStatus.BAD_REQUEST;
			result.addErrorMessage(getMessage("error.save", new String[] { "comentario", comentario.getId() }));
		}

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/comentario/{id}",
			"/api2/comentario/{id}" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> delete(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Comentario u = comentarioService.get(id, sessao);
		result = referenciasDelete(u, result, sessao);
		if (!result.isValid()) {
			returnStatus = HttpStatus.OK;
			return new ResponseEntity<Result>(result, returnStatus);
		}
		if (comentarioService.delete(id, sessao)) {
			result.setData(null);
			result.addSuccessMessage(getMessage("sucesso.delete", new String[] { "comentario", u.getId() }));
		} else {
			returnStatus = HttpStatus.BAD_REQUEST;

			result.addError("get", getMessage("error.delete", new String[] { "comentario", u.getId() }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/comentario/deleteAll",
			"/api2/comentario/deleteAll" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> deleteAll(@RequestBody String[] ids, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		List<String> retorno = new ArrayList<String>();

		Comentario comentario = new Comentario();

		for (int i = 0; i < ids.length; i++) {
			comentario = comentarioService.get(ids[i], sessao);
			result = referenciasDelete(comentario, result, sessao);
			if (!result.isValid()) {
				returnStatus = HttpStatus.OK;
				return new ResponseEntity<Result>(result, returnStatus);
			}
			if (comentarioService.delete(ids[i], sessao)) {
				retorno.add(ids[i]);
			} else {
				returnStatus = HttpStatus.BAD_REQUEST;
				result.addError("get", getMessage("error.delete", new String[] { "comentario", comentario.getId() }));
			}
		}

		result.setList(retorno);

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/comentario/form" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> form(HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}

		HashMap<String, Object> dados = new HashMap<String, Object>();

		dados.put("modelo", new Comentario());
		// dados.put("categorias", categoriaServ.listAllEntity(sessao));

		result.setData(dados);
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/comentario/{id}",
			"/api2comentario/{id}" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> get(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Comentario comentario = comentarioService.get(id, sessao);

		if (comentario != null) {
			destroyProxyClass(comentario);
			result.setData(comentario);
		} else {
			returnStatus = HttpStatus.BAD_REQUEST;
			result.addError("get", getMessage("error.find", new String[] { "comentario" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/comentario/list",
			"/api/comentario/list" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> list(@RequestBody PaginateForm pages, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}
		result = comentarioService.list(pages, sessao);
		destroyProxyClassList(result.getList());

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/comentario/listAll",
			"/api2/comentario/listAll" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> listAll(HttpServletRequest request) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		result = comentarioService.listAll(sessao);

		destroyProxyClassList(result.getList());
		return new ResponseEntity<Result>(result, returnStatus);
	}

	public Result referenciasDelete(Comentario p, Result result, SessaoUser sessao) {

		Integer r = 0;

		return result;
	}

	public void destroyProxyClass(Comentario p) {
		if (p.getConteudo() != null) {
			p.getConteudo().setCategorias(null);
		}
	}

	public void destroyProxyClassList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				destroyProxyClass((Comentario) p);
			}
		}
	}

}