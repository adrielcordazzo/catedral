
package br.com.xlabi.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
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

import br.com.xlabi.controller.geral.AbstractController;
import br.com.xlabi.entity.Membro;
import br.com.xlabi.entity.geral.Arquivo;
import br.com.xlabi.form.FormRelatorio;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.BairroService;
import br.com.xlabi.service.CargoService;
import br.com.xlabi.service.CidadeService;
import br.com.xlabi.service.EstadoService;
import br.com.xlabi.service.MembroService;
import br.com.xlabi.service.geral.RequestHttpSevice;

@Controller

public class MembroController extends AbstractController {


	@Autowired
	private MembroService membroService;

	@Autowired
	CidadeService cidadeServ;
	
	@Autowired
	BairroService bairroServ;
	
	@Autowired
	EstadoService estadoServ;
	
	@Autowired
	CargoService cargoServ;
	
	@Autowired
	RequestHttpSevice requestHttp;
	
	
	
	@RequestMapping(value = "/membro/upload", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Result> uploadFile(HttpServletRequest request,
			@RequestParam("file") MultipartFile input) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		Arquivo arq = null;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}

		try {

			result = membroService.readXls(input.getInputStream(), input.getOriginalFilename(), sessao);

		} catch (Exception e) {
			e.printStackTrace();
			result.addError("file", "erro ao fazer o upload");
		}

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/membro/save" }, method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody Membro membro, HttpServletRequest request,
			BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		if (membroService.save(membro, sessao) != null) {
			result.addSuccessMessage(getMessage("sucesso.save", new String[] { "membro", "" }));
			result.setData(membro);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addErrorMessage(getMessage("error.save", new String[] { "membro", "" }));
		}

		return new ResponseEntity<Result>(result, returnStatus);

	}
	
	@RequestMapping(value = { "/membro/form" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> form(HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}

		HashMap<String, Object> dados = new HashMap<String, Object>();

		dados.put("modelo", new Membro());
		dados.put("cidade", cidadeServ.listAllEntity(sessao));
		dados.put("bairro", bairroServ.listAllEntity(sessao));
		dados.put("estado", estadoServ.listAllEntity(sessao));
		dados.put("cargos", cargoServ.listAllEntity(sessao));

		result.setData(dados);
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/membro/{id}" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> delete(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Membro u = membroService.get(id, sessao);
		result = referenciasDelete(u, result, sessao);
		if (!result.isValid()) {
			returnStatus = HttpStatus.OK;
			return new ResponseEntity<Result>(result, returnStatus);
		}
		if (membroService.delete(id, sessao)) {
			result.setData(null);
			result.addSuccessMessage(getMessage("sucesso.delete", new String[] { "membro", "" }));
		} else {
			returnStatus = HttpStatus.NO_CONTENT;

			result.addError("get", getMessage("error.delete", new String[] { "membro", "" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/membro/deleteAll" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> deleteAll(@RequestBody String[] ids, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		List<String> retorno = new ArrayList<String>();

		Membro membro = new Membro();

		for (int i = 0; i < ids.length; i++) {
			membro = membroService.get(ids[i], sessao);
			result = referenciasDelete(membro, result, sessao);
			if (!result.isValid()) {
				returnStatus = HttpStatus.OK;
				return new ResponseEntity<Result>(result, returnStatus);
			}
			if (membroService.delete(ids[i], sessao)) {
				retorno.add(ids[i]);
			} else {
				returnStatus = HttpStatus.NO_CONTENT;
				result.addError("get", getMessage("error.delete", new String[] { "membro", "" }));
			}
		}

		result.setList(retorno);

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/membro/{id}" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> get(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Membro membro = membroService.get(id, sessao);

		if (membro != null) {
			destroyProxyClass(membro);
			result.setData(membro);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addError("get", getMessage("error.find", new String[] { "membro" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}
	
	@RequestMapping(value = { "/membro/getImagem/{id}","/api/membro/getImagem/{id}" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getImagem(@PathVariable("id") String id, HttpServletRequest request) {

		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);

		Membro membro = membroService.get(id, sessao);
		
		String imagem = membro.getImagemmembro();
		if(imagem == null) {
			imagem = "http://moderna.xlabi.com.br/relatorio/img/semfoto.png";
		}

		return new ResponseEntity<String>(imagem, returnStatus);
	}
	
	
	@RequestMapping(value = { "/membro/saveFicha/{id}/{ficha}" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> saveFicha(@PathVariable("id") String id, @PathVariable("ficha") String ficha, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Membro membro = membroService.saveFicha(id, ficha, sessao);

		if (membro != null) {
			result.addSuccessMessage(getMessage("sucesso.save", new String[] { "membro", "" }));
			result.setData(membro);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addError("get", getMessage("error.find", new String[] { "membro" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}
	

	@RequestMapping(value = { "/membro/list", "/api/membro/list" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> list(@RequestBody PaginateForm pages, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}
		result = membroService.list(pages, sessao);
		destroyProxyClassList(result.getList());

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/membro/listAll","/api/membro/listAll" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> listAll(HttpServletRequest request) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		result = membroService.listAll(sessao);

		destroyProxyClassList(result.getList());
		return new ResponseEntity<Result>(result, returnStatus);
	}

	public Result referenciasDelete(Membro p, Result result, SessaoUser sessao) {

		Integer r = 0;

		return result;
	}

	public void destroyProxyClass(Membro p) {
		//p.setCargos(null);
	}

	public void destroyProxyClassList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				destroyProxyClass((Membro) p);
			}
		}
	}
	
	/**/
	
	@RequestMapping(value = { "/membro/imprimir", "/api/membro/imprimir" }, method = RequestMethod.POST )
	public @ResponseBody ResponseEntity<byte[]> print(@RequestParam("dados") String form, HttpServletRequest request,
			HttpServletResponse response) {
		
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			super.acceptRequest(sessao, null);
			return null;
		}
		
		PaginateForm pages = new PaginateForm();
		
		//System.out.println("FILTROSSSSSSSSSSSSSSSSSSSSSSSSSSS " + form);
		
		try {
			JSONObject formjson = new JSONObject(form);
			String aniversario = formjson.getString("aniversario");
			String cargo = formjson.getString("cargo");
			String ficha = formjson.getString("ficha");
			
			if(aniversario != null && !aniversario.equals("")) {
				pages.getCampos().add("aniversario");
				pages.getValues().add(aniversario);
			}
			
			if(cargo != null && !cargo.equals("")) {
				pages.getCampos().add("cargo");
				pages.getValues().add(cargo);
			}
			
			if(ficha != null && !ficha.equals("")) {
				pages.getCampos().add("ficha");
				pages.getValues().add(ficha);
			}
			
		} catch (JSONException e) {
			
		}

		result = membroService.list(pages, sessao);

		byte[] x = null;
		String json = objectToJson(result.getList());
		
		//System.out.println("JSONNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN " + json);
		if (result.getList() != null) {
			String retorno = requestHttp.postRequest("http://moderna.xlabi.com.br/relatorio/relatoriocatedral.php", json);
			
			//System.out.println("RETORNOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO " + retorno);

			x = returnFile("http://moderna.xlabi.com.br/relatorio/relatoriocatedral.pdf", "application/pdf", "relatoriocatedral.pdf", response);

		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addError("get", "Erro");
		}
		
		return new ResponseEntity<byte[]>(x, returnStatus);
	}
	
	

}