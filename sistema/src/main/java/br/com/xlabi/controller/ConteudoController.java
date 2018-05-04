
package br.com.xlabi.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookException;
import com.restfb.types.FacebookType;

import br.com.xlabi.controller.geral.AbstractController;
import br.com.xlabi.entity.Conteudo;
import br.com.xlabi.entity.Conteudotipo;
import br.com.xlabi.entity.geral.Arquivo;
import br.com.xlabi.entity.geral.Pasta;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.CategoriaService;
import br.com.xlabi.service.ConteudoService;
import br.com.xlabi.service.ConteudocategoriaService;
import br.com.xlabi.service.ConteudotipoService;
import br.com.xlabi.service.geral.ArquivoService;
import br.com.xlabi.service.geral.PastaService;

@Controller

public class ConteudoController extends AbstractController {

	@Autowired
	ConteudocategoriaService conteudocategoriaServ;

	@Autowired
	private ConteudoService conteudoService;
	
	@Autowired
	ConteudotipoService conteudotipoServ;
	
	@Autowired
	CategoriaService categoriaServ;
	
	@Autowired
	PastaService pastaServ;
	
	@Autowired
	ArquivoService arquivoServ;
	
	
	@RequestMapping(value = "/conteudo/uploadfoto", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Result> uploadfoto(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

		Result result = new Result();
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		} 
		// Arquivo arq = null; 
		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
			String myString = IOUtils.toString(stream, "UTF-8");
	
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonRetorno = mapper.readTree(myString);
			JsonNode array = jsonRetorno;//.get("data");
			Gson gson = new Gson();
			if (array.isArray()) {
				for (JsonNode j : array) {
					String codigo = j.get("id_item").asText();
					String caminho = j.get("imagem").asText();
					System.out.println(caminho);
	
					Conteudo i = conteudoService.externalid(codigo, sessao);
	
					if (i == null) {
						continue;
					}
	
					
					
					URLConnection connection = new URL("http://www.catedralquadrangular.com.br/admin/imagens/" + caminho).openConnection();
					connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
					connection.connect();
					
					InputStream marca = connection.getInputStream();
					
					Pasta pasta = pastaServ.get(i.getPasta().getId(), null);
					String idPasta = pasta.getId();
					String filename = caminho;
					String extension = filename.substring(filename.lastIndexOf(".") + 1);
	
					Path pfil3 = Paths.get(pastaServ.getCaminho(pasta.getContratante()) + idPasta + "/" + filename);
					Path parentDir = pfil3.getParent();
					if (!Files.exists(parentDir))
						Files.createDirectories(parentDir);
	
					byte data[] = IOUtils.toByteArray(marca);
					Files.write(pfil3, data);
					Arquivo arq = new Arquivo();
	
					arq.setTipo("image/png");
					arq.setNome(filename);
					arq.setContratante(sessao.getContratante());
					arq.setExtensao(extension);
					arq.setUsuario(sessao.getUsuario());
					arq.setPasta(pasta);
					arq.setTamanho(Files.size(pfil3) + "");
					arq.setContratante(i.getContratante());
					arq.setUsuario(i.getUsuario());
	
					arquivoServ.save(arq);
	
				}
			}
		}catch(
	
		Exception e)
		{
			e.printStackTrace();
			result.addError("file", "erro ao fazer o upload");
		}
	
		HttpStatus returnStatus = HttpStatus.OK;
	
		return new ResponseEntity<Result>(result,returnStatus);
	}
	
	
	@RequestMapping(value = "/conteudo/upload", method = RequestMethod.POST)
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

					Conteudo i = conteudoService.externalid(j.get("id").asText(), sessao);
					if (i != null) {
						continue;
					}
					i = new Conteudo();
					i.setTitulo(j.get("titulo").asText());
					i.setConteudo(j.get("descricao").asText());
					i.setYoutube(j.get("youtube").asText());

					i.setExternalid(j.get("id").asText());
					
					Conteudotipo ct = conteudotipoServ.get("40288a826328e88a016328e92bc90000", sessao);
					i.setConteudotipo(ct);

					conteudoService.save(i, sessao);
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
	 
	 

	@RequestMapping(value = { "/conteudo/save" }, method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody Conteudo conteudo,
			HttpServletRequest request, BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		if (conteudoService.save(conteudo, sessao) != null) {
			result.addSuccessMessage(getMessage("sucesso.save", new String[] { "conteudo", "" }));
			result.setData(conteudo);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addErrorMessage(getMessage("error.save", new String[] { "conteudo", "" }));
		}

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/conteudo/form" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> form(HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}

		HashMap<String, Object> dados = new HashMap<String, Object>();

		dados.put("modelo", new Conteudo());
		dados.put("conteudotipo", conteudotipoServ.listAllEntity(sessao));
		//dados.put("categorias", categoriaServ.listAllEntity(sessao));

		result.setData(dados);
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/conteudo/{id}" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> delete(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Conteudo u = conteudoService.get(id, sessao);
		result = referenciasDelete(u, result, sessao);
		if (!result.isValid()) {
			returnStatus = HttpStatus.OK;
			return new ResponseEntity<Result>(result, returnStatus);
		}
		if (conteudoService.delete(id, sessao)) {
			result.setData(null);
			result.addSuccessMessage(getMessage("sucesso.delete", new String[] { "conteudo", "" }));
		} else {
			returnStatus = HttpStatus.NO_CONTENT;

			result.addError("get", getMessage("error.delete", new String[] { "conteudo", "" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/conteudo/deleteAll" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> deleteAll(@RequestBody String[] ids, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		List<String> retorno = new ArrayList<String>();

		Conteudo conteudo = new Conteudo();

		for (int i = 0; i < ids.length; i++) {
			conteudo = conteudoService.get(ids[i], sessao);
			result = referenciasDelete(conteudo, result, sessao);
			if (!result.isValid()) {
				returnStatus = HttpStatus.OK;
				return new ResponseEntity<Result>(result, returnStatus);
			}
			if (conteudoService.delete(ids[i], sessao)) {
				retorno.add(ids[i]);
			} else {
				returnStatus = HttpStatus.NO_CONTENT;
				result.addError("get", getMessage("error.delete", new String[] { "conteudo", "" }));
			}
		}

		result.setList(retorno);

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/conteudo/{id}","/api/conteudo/{id}" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> get(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Conteudo conteudo = conteudoService.get(id, sessao);

		if (conteudo != null) {
			destroyProxyClass(conteudo);
			result.setData(conteudo);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addError("get", getMessage("error.find", new String[] { "conteudo" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}
	
	@RequestMapping(value = { "/conteudoPorUrl/{id}","/api/conteudoPorUrl/{url}" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> getUrl(@PathVariable("url") String url, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Conteudo conteudo = conteudoService.getPorUrl(url, sessao);

		if (conteudo != null) {
			destroyProxyClass(conteudo);
			result.setData(conteudo);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addError("get", getMessage("error.find", new String[] { "conteudo" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}
	
	@RequestMapping(value = { "/listarConteudoPorUrl/{url}","/api/listarConteudoPorUrl/{url}" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> listUrl(@PathVariable("url") String url, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		result = conteudoService.listUrl(url, sessao);

		destroyProxyClassList(result.getList());

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/conteudo/list", "/api/conteudo/list" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> list(@RequestBody PaginateForm pages, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}
		result = conteudoService.list(pages, sessao);
		destroyProxyClassList(result.getList());

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/conteudo/listAll", "/api/conteudo/listAll" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Result> listAll(HttpServletRequest request) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		result = conteudoService.listAll(sessao);
		destroyProxyClassList(result.getList());
		return new ResponseEntity<Result>(result, returnStatus);
	}

	public Result referenciasDelete(Conteudo p, Result result, SessaoUser sessao) {

		Integer r = 0;

		return result;
	}

	public void destroyProxyClass(Conteudo p) {
		
	}

	public void destroyProxyClassList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				destroyProxyClass((Conteudo) p);
			}
		}
	}

}