
package br.com.xlabi.controller.geral;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import br.com.xlabi.entity.geral.Arquivo;
import br.com.xlabi.entity.geral.Pasta;
import br.com.xlabi.form.ArquivoOrdem;
import br.com.xlabi.form.FormArquivoOrdem;
import br.com.xlabi.form.FormCrop;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.geral.ArquivoService;
import br.com.xlabi.service.geral.PastaService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.filters.Canvas;
import net.coobird.thumbnailator.geometry.Positions;

@Controller
public class ArquivoController extends AbstractController {

	@Autowired
	private ArquivoService arquivoService;

	@Autowired
	private PastaService pastaServ;

	@RequestMapping(value = { "/arquivo/setPrincipal/{id}" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> setPrincipal(@PathVariable("id") String id,
			HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Arquivo arquivo = arquivoService.get(id, sessao);

		arquivo.setPrincipal("s");
		List<Arquivo> arquivos = arquivoService.getFilesPasta(arquivo.getPasta().getId());
		for (Object oAr : arquivos) {
			Arquivo a = (Arquivo) oAr;
			a.setPrincipal(null);
			arquivoService.save(a);
		}
		arquivoService.save(arquivo);
		if (arquivo != null) {
			result.setData(arquivo);
		} else {
			returnStatus = HttpStatus.BAD_REQUEST;
			result.addError("get", "Erro ao encontrar Arquivo com o id:" + id);
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/arquivo/ordenar" }, method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> ordenar(@Valid @RequestBody FormArquivoOrdem ordenar,
			HttpServletRequest request, BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		for (ArquivoOrdem a : ordenar.getOrdem()) {
			Arquivo arq = arquivoService.get(a.getId(), sessao);
			arq.setOrdena(a.getPosicao());
			arquivoService.save(arq);

		}
		result.addSuccessMessage("Nova Ordem Salva");
		return new ResponseEntity<Result>(result, returnStatus);

	}

	public BufferedImage cropSubImage(BufferedImage in, FormCrop crop) {

		if (crop.getX() == null || crop.getX() < 0) {
			crop.setX(0d);
		}

		if (crop.getY() == null || crop.getY() < 0) {
			crop.setY(0d);
		}

		Integer w = (int) ((in.getWidth() * crop.getWidth()) / crop.getLargura());
		Integer h = (int) ((in.getHeight() * crop.getHeight()) / crop.getAltura());
		Integer X = (int) ((in.getWidth() * crop.getX()) / crop.getLargura());
		Integer Y = (int) ((in.getHeight() * crop.getY()) / crop.getAltura());

		System.out.println("altura tela:" + crop.getAltura());
		System.out.println("largura tela:" + crop.getLargura());
		System.out.println("altura imagem:" + in.getHeight());
		System.out.println("largura imagem:" + in.getWidth());
		System.out.println("altura corte " + h);
		System.out.println("largura corte " + w);
		System.out.println("x corte " + X);
		System.out.println("y corte " + Y);

		BufferedImage SubImgage = in.getSubimage(X, Y, w, h);

		return SubImgage;
	}

	@RequestMapping(value = { "/arquivo/crop" }, method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> crop(@Valid @RequestBody FormCrop crop, HttpServletRequest request,
			BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		Arquivo arq = arquivoService.get(crop.getId(), sessao);
		arq.setLegenda(crop.getLegenda());
		arq.setMascaraalign(crop.getMascaraalign());

		if (crop.getWidth() != null && crop.getWidth() > 200) {
			try {
				File file = new File(
						pastaServ.getCaminho(arq.getContratante()) + arq.getPasta().getId() + "/" + arq.getNome());
				BufferedImage in = javax.imageio.ImageIO.read(file);
				BufferedImage SubImgage = cropSubImage(in, crop);
				File outputfile = new File(
						pastaServ.getCaminho(arq.getContratante()) + arq.getPasta().getId() + "/" + arq.getNome());

				if (arq.getNome().toUpperCase().contains("PNG")) {
					ImageIO.write(SubImgage, "png", outputfile);
				} else {
					ImageIO.write(SubImgage, "jpg", outputfile);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		arquivoService.save(arq);

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/arquivo/save" }, method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> update(@Valid @RequestBody Arquivo arquivo, HttpServletRequest request,
			BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		if (arquivoService.save(arquivo) != null) {
			result.addSuccessMessage(getMessage("sucesso.save", new String[] { "arquivo", arquivo.getCriado() }));
			result.setData(arquivo);
		} else {
			returnStatus = HttpStatus.NO_CONTENT;
			result.addErrorMessage(getMessage("error.save", new String[] { "arquivo", arquivo.getCriado() }));
		}

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/arquivo/{id}" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> delete(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Arquivo u = arquivoService.get(id, sessao);
		result = referenciasDelete(u, result);
		if (arquivoService.delete(id, sessao)) {
			result.setData(null);
			result.addSuccessMessage(getMessage("sucesso.delete", new String[] { "arquivo", u.getCriado() }));
		} else {
			returnStatus = HttpStatus.NO_CONTENT;

			result.addError("get", getMessage("error.delete", new String[] { "arquivo", u.getCriado() }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/arquivo/deleteAll" }, method = RequestMethod.DELETE, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> deleteAll(@RequestBody String[] ids, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		List<String> retorno = new ArrayList<String>();
		Arquivo arquivo = new Arquivo();
		for (int i = 0; i < ids.length; i++) {
			arquivo = arquivoService.get(ids[i], sessao);
			result = referenciasDelete(arquivo, result);
			if (arquivoService.delete(ids[i], sessao)) {
				retorno.add(ids[i]);
			} else {
				returnStatus = HttpStatus.BAD_REQUEST;
				result.addError("get", getMessage("error.delete", new String[] { "usuário", arquivo.getCriado() }));
			}
		}

		result.setList(retorno);

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/arquivo/{id}" }, method = RequestMethod.GET, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> get(@PathVariable("id") String id, HttpServletRequest request) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}
		Arquivo arquivo = arquivoService.get(id, sessao);

		if (arquivo != null) {
			destroyProxyClass(arquivo);
			result.setData(arquivo);
		} else {
			returnStatus = HttpStatus.BAD_REQUEST;
			result.addError("get", getMessage("error.find", new String[] { "arquivo" }));
		}
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/arquivo/list" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> list(@RequestBody PaginateForm pages, HttpServletRequest request,
			BindingResult bindingResult) {

		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}
		result = arquivoService.list(pages, sessao);
		// destroyProxyClassList(result.getList());

		return new ResponseEntity<Result>(result, returnStatus);

	}

	@RequestMapping(value = { "/arquivo/listAll" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Result> listAll(@RequestBody PaginateForm pages, HttpServletRequest request,
			BindingResult bindingResult) {
		Result result = new Result();
		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, bindingResult) != null) {
			return super.acceptRequest(sessao, bindingResult);
		}

		if (!pages.getCampos().contains("pasta.id")) {
			return new ResponseEntity<Result>(result, HttpStatus.BAD_REQUEST);
		}

		result = arquivoService.listAllPasta(sessao, pages.getValues().get(0));
		// destroyProxyClassList(result.getList());
		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/arquivo/listAll/{idPasta}",
			"/api/arquivo/listAll/{idPasta}" }, method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Result listAll(@PathVariable("idPasta") String id) {

		Result result = new Result();
		result = arquivoService.listAll(id);

		return result;
	}

	public Result referenciasDelete(Arquivo p, Result result) {

		return result;
	}

	public void destroyProxyClass(Arquivo p) {

	}

	public void destroyProxyClassList(List<?> list) {
		for (Object p : list) {
			destroyProxyClass((Arquivo) p);
		}
	}

	@RequestMapping(value = { "/arquivos/uploads/{id_pasta}",
			"/api/arquivos/uploads/{id_pasta}" }, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Result> uploadFile(@RequestParam(value = "file") MultipartFile input,
			@PathVariable("id_pasta") String idPasta, MultipartHttpServletRequest request) {

		HttpStatus returnStatus = HttpStatus.OK;
		SessaoUser sessao = this.verificaSessao(request);
		if (super.acceptRequest(sessao, null) != null) {
			return super.acceptRequest(sessao, null);
		}

		Result result = new Result();

		Pasta pasta = pastaServ.get(idPasta, null);

		Arquivo arq = uploadSaveFile(input, pasta, sessao);
		if (arq == null) {
			returnStatus = HttpStatus.BAD_REQUEST;
			result.addError("File", "Arquivo não pode ser salvo");
		}
		result.setData(arq);

		return new ResponseEntity<Result>(result, returnStatus);
	}

	@RequestMapping(value = { "/arquivo/getFile/{hash}", "api/arquivo/getFile/{hash}" }, method = RequestMethod.GET)
	@ResponseBody
	public byte[] getUserFile(@PathVariable("hash") String hash,
			@RequestParam(value = "size", required = false) String size, HttpServletResponse response)
			throws Exception {
		Arquivo arq = null;

		arq = arquivoService.getArquivo(hash);

		byte[] bytes = null;
		if (arq != null) {

			File file = new File(
					pastaServ.getCaminho(arq.getContratante()) + arq.getPasta().getId() + "/" + arq.getNome());
			FileInputStream input = new FileInputStream(file);
			
			if(arq.getExtensao().toUpperCase().equals("JPG") || arq.getExtensao().toUpperCase().equals("JPEG") || arq.getExtensao().toUpperCase().equals("PNG") || arq.getExtensao().toUpperCase().equals("GIF")) {

				BufferedImage image = ImageIO.read(input);
				Builder<BufferedImage> thumbnail = Thumbnails.of(image).size(800, 600);
	
				bytes = recuperarFileBufferedImage(thumbnail, arq, response);
			}else {
				bytes = recuperarFileInputStream(input,response, arq);
			}

		} else {
			bytes = recuperarFileUrl("http://moderna.xlabi.com.br/relatorio/img/semfoto.png", response);
		}
		return bytes;
	}
	/// 177.35.84.125

	@RequestMapping(value = { "/arquivo/getPastaSize/{id}/{largura}/{alutra}",
			"/api/arquivo/getPastaSize/{id}/{largura}/{alutra}" }, method = RequestMethod.GET)
	@ResponseBody
	public byte[] getPastaSize(@PathVariable("id") String id, @PathVariable(value = "largura") Integer largura,
			@PathVariable(value = "alutra") Integer alutra, HttpServletResponse response) throws Exception {
		Arquivo arq = null;

		arq = arquivoService.getFilePasta(id);

		byte[] bytes = null;
		if (arq != null) {
			// recuperando o conteúdo do arquivo
			// byte[] content = arq.getc();
			File file = new File(
					pastaServ.getCaminho(arq.getContratante()) + arq.getPasta().getId() + "/" + arq.getNome());
			FileInputStream input = new FileInputStream(file);

			BufferedImage image = ImageIO.read(input);

			int rsiw = 600;
			int rsih = 600;
			if (image.getWidth() < rsiw) {
				rsiw = image.getWidth();
				rsih = image.getWidth();
			}

			if (image.getHeight() < rsih) {
				rsiw = image.getHeight();
				rsih = image.getHeight();
			}

			Builder<BufferedImage> thumbnail = Thumbnails.of(image).sourceRegion(Positions.CENTER, rsiw, rsih)
					.size(largura, alutra);

			aplicaMarcaDagua(thumbnail, arq, arq.getContratante().getMarcadagua(),
					thumbnail.asBufferedImage().getWidth() / 3, thumbnail.asBufferedImage().getHeight() / 3);

			thumbnail.addFilter(new Canvas(largura, alutra, Positions.CENTER, Color.WHITE));

			bytes = recuperarFileBufferedImage(thumbnail, arq, response);

		} else {
			Pasta p = pastaServ.get(id);

			InputStream input = new URL(p.getContratante().getSemfoto()).openStream();

			BufferedImage image = ImageIO.read(input);

			int rsiw = 600;
			int rsih = 600;
			if (image.getWidth() < rsiw) {
				rsiw = image.getWidth();
				rsih = image.getWidth();
			}

			if (image.getHeight() < rsih) {
				rsiw = image.getHeight();
				rsih = image.getHeight();
			}

			Builder<BufferedImage> thumbnail = Thumbnails.of(image).sourceRegion(Positions.CENTER, rsiw, rsih)
					.size(largura, alutra);

			arq = new Arquivo();
			arq.setContratante(p.getContratante());
			arq.setPasta(p);
			arq.setNome("teste");

			bytes = recuperarFileBufferedImage(thumbnail, arq, response);

		}
		return bytes;
	}

	@RequestMapping(value = { "/arquivo/getPastaImage/{id}/{largura}/{alutra}",
			"/api/arquivo/getPastaImage/{id}/{largura}/{alutra}" }, method = RequestMethod.GET)
	@ResponseBody
	public byte[] getPastaImage(@PathVariable("id") String id, @PathVariable(value = "largura") Integer largura,
			@PathVariable(value = "alutra") Integer alutra, HttpServletResponse response) throws Exception {
		Arquivo arq = null;

		arq = arquivoService.getFilePasta(id);

		byte[] bytes = null;
		if (arq != null) {
			// recuperando o conteúdo do arquivo
			// byte[] content = arq.getc();
			File file = new File(
					pastaServ.getCaminho(arq.getContratante()) + arq.getPasta().getId() + "/" + arq.getNome());
			FileInputStream input = new FileInputStream(file);

			BufferedImage image = ImageIO.read(input);

			int rsiw = 600;
			int rsih = 600;
			if (image.getWidth() < rsiw) {
				rsiw = image.getWidth();
				rsih = image.getWidth();
			}

			if (image.getHeight() < rsih) {
				rsiw = image.getHeight();
				rsih = image.getHeight();
			}

			Builder<BufferedImage> thumbnail = Thumbnails.of(image).size(largura, alutra);

			bytes = recuperarFileBufferedImage(thumbnail, arq, response);

		} else {
			Pasta p = pastaServ.get(id);

			InputStream input = new URL(p.getContratante().getSemfoto()).openStream();

			BufferedImage image = ImageIO.read(input);

			int rsiw = 600;
			int rsih = 600;
			if (image.getWidth() < rsiw) {
				rsiw = image.getWidth();
				rsih = image.getWidth();
			}

			if (image.getHeight() < rsih) {
				rsiw = image.getHeight();
				rsih = image.getHeight();
			}

			Builder<BufferedImage> thumbnail = Thumbnails.of(image).sourceRegion(Positions.CENTER, rsiw, rsih)
					.size(largura, alutra);

			arq = new Arquivo();
			arq.setContratante(p.getContratante());
			arq.setPasta(p);
			arq.setNome("teste");

			bytes = recuperarFileBufferedImage(thumbnail, arq, response);

		}
		return bytes;
	}

	@RequestMapping(value = { "/arquivo/getPasta/{idPasta}",
			"/api/arquivo/getPasta/{idPasta}" }, method = RequestMethod.GET)
	@ResponseBody
	public byte[] getPasta(@PathVariable("idPasta") String idPasta,
			@RequestParam(value = "size", required = false) String size, HttpServletResponse response)
			throws Exception {
		Arquivo arq = null;

		arq = arquivoService.getFilePasta(idPasta);

		byte[] bytes = null;
		if (arq != null) {

			File file = new File(
					pastaServ.getCaminho(arq.getContratante()) + arq.getPasta().getId() + "/" + arq.getNome());

			FileInputStream input = new FileInputStream(file);

			BufferedImage image = ImageIO.read(input);
			Builder<BufferedImage> thumbnail = Thumbnails.of(image).size(800, 600);

			bytes = recuperarFileBufferedImage(thumbnail, arq, response);

		} else {

			bytes = recuperarFileUrl("http://moderna.xlabi.com.br/relatorio/img/semfoto.png", response);

		}
		return bytes;
	}

	@RequestMapping(value = { "/arquivo/getArquivo/{id}", "/api/arquivo/getArquivo/{id}" }, method = RequestMethod.GET)
	@ResponseBody
	public byte[] getArquivo(@PathVariable("id") String id, @RequestParam(value = "size", required = false) String size,
			HttpServletResponse response) throws Exception {
		Arquivo arq = null;

		arq = arquivoService.getArquivo(id);

		byte[] bytes = null;
		if (arq != null) {

			File file = new File(
					pastaServ.getCaminho(arq.getContratante()) + arq.getPasta().getId() + "/" + arq.getNome());
			FileInputStream input = new FileInputStream(file);

			BufferedImage image = ImageIO.read(input);
			Builder<BufferedImage> thumbnail = Thumbnails.of(image).size(800, 600);

			bytes = recuperarFileBufferedImage(thumbnail, arq, response);

		} else {
			bytes = recuperarFileUrl("http://moderna.xlabi.com.br/relatorio/img/semfoto.png", response);
		}
		return bytes;
	}

	@RequestMapping(value = { "/arquivo/getArquivo/{id}/{largura}/{alutra}",
			"/api/arquivo/getArquivo/{id}/{largura}/{altura}" }, method = RequestMethod.GET)
	@ResponseBody
	public byte[] getArquivoSize(@PathVariable("id") String id, @PathVariable(value = "largura") Integer largura,
			@PathVariable(value = "altura") Integer altura, HttpServletResponse response) throws Exception {
		Arquivo arq = null;

		arq = arquivoService.getArquivo(id);

		byte[] bytes = null;
		if (arq != null) {
			File file = new File(
					pastaServ.getCaminho(arq.getContratante()) + arq.getPasta().getId() + "/" + arq.getNome());
			FileInputStream input = new FileInputStream(file);
			BufferedImage image = ImageIO.read(input);
			Builder<BufferedImage> thumbnail = Thumbnails.of(image).size(largura, altura);

			aplicaMarcaDagua(thumbnail, arq, arq.getContratante().getMarcadagua(), largura, altura);

			thumbnail.addFilter(new Canvas(largura, altura, Positions.CENTER, Color.WHITE));

			bytes = recuperarFileBufferedImage(thumbnail, arq, response);

		} else {
			bytes = recuperarFileUrl("http://moderna.xlabi.com.br/relatorio/img/semfoto.png", response);
		}
		return bytes;
	}

	public Arquivo uploadSaveFile(MultipartFile input, Pasta pasta, SessaoUser sessao) {
		Arquivo arq = null;
		try {

			String contentType = input.getContentType();
			String filename = input.getOriginalFilename();
			String extension = filename.substring(filename.lastIndexOf(".") + 1);
			Path file = Paths.get(pastaServ.getCaminho(pasta.getContratante()) + pasta.getId() + "/" + filename);
			Path parentDir = file.getParent();
			if (!Files.exists(parentDir))
				Files.createDirectories(parentDir);

			byte data[] = IOUtils.toByteArray(input.getInputStream());
			Files.write(file, data);

			arq = new Arquivo();
			arq.setTipo(contentType);
			arq.setNome(filename);
			arq.setContratante(sessao.getContratante());
			arq.setExtensao(extension);
			arq.setUsuario(sessao.getUsuario());
			arq.setPasta(pasta);
			arq.setTamanho(Files.size(file) + "");

			arquivoService.save(arq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arq;
	}

	public byte[] recuperarFile(Arquivo arq, HttpServletResponse response) throws Exception {
		byte[] bytes = null;

		File file = new File(pastaServ.getCaminho(arq.getContratante()) + arq.getPasta().getId() + "/" + arq.getNome());

		FileInputStream input = new FileInputStream(file);
		bytes = IOUtils.toByteArray(input);

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", "foto");
		response.setHeader(headerKey, headerValue);

		// para funcionar o play do video
		response.addHeader("Content-Range", "bytes " + 0 + "-" + bytes.length + "/" + bytes.length);
		response.addHeader("Accept-Ranges", "bytes");
		response.addHeader("Connection", "Keep-Alive");
		response.addHeader("Keep-Alive", "timeout=5, max=98");

		response.setContentLength(bytes.length);
		response.setContentType("image/png");
		response.flushBuffer();

		return bytes;
	}

	public byte[] recuperarFileBufferedImage(Builder<BufferedImage> thumbnail, Arquivo arq,
			HttpServletResponse response) throws Exception {
		File f = File.createTempFile("xlabi" + Math.random(), "png");
		ImageIO.write(thumbnail.asBufferedImage(), "PNG", f);
		FileInputStream input = new FileInputStream(f);
		f.delete();
		return recuperarFileInputStream(input, response, arq);

	}

	public byte[] recuperarFileInputStream(InputStream input, HttpServletResponse response, Arquivo arq) throws Exception {
		if(arq == null) {
			arq = new Arquivo();
			arq.setNome("foto");
			arq.setTipo("image/png");
		}
		byte[] bytes = IOUtils.toByteArray(input);
		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", arq.getNome());
		response.setHeader(headerKey, headerValue);

		// para funcionar o play do video
		response.addHeader("Content-Range", "bytes " + 0 + "-" + bytes.length + "/" + bytes.length);
		response.addHeader("Accept-Ranges", "bytes");
		response.addHeader("Connection", "Keep-Alive");
		response.addHeader("Keep-Alive", "timeout=5, max=98");

		response.setContentLength(bytes.length);
		response.setContentType(arq.getTipo());
		response.flushBuffer();
		return bytes;
	}

	public void aplicaMarcaDagua(Builder<BufferedImage> thumbnail, Arquivo arq, String urlMarca, Integer largura,
			Integer altura) throws MalformedURLException, IOException {

		InputStream marca = new URL(urlMarca).openStream();

		Positions posicao = Positions.CENTER;

		if (arq.getMascaraalign() != null) {
			if (arq.getMascaraalign().equals("td")) {
				posicao = Positions.TOP_RIGHT;
			} else if (arq.getMascaraalign().equals("te")) {
				posicao = Positions.TOP_LEFT;
			} else if (arq.getMascaraalign().equals("id")) {
				posicao = Positions.BOTTOM_RIGHT;
			} else if (arq.getMascaraalign().equals("ie")) {
				posicao = Positions.BOTTOM_LEFT;
			}
		}

		if (marca != null) {
			BufferedImage watermarkImage = Thumbnails.of(marca).size(largura / 3, altura / 3).asBufferedImage();
			thumbnail.watermark(posicao, watermarkImage, 0.8f);
		}
	}

	public byte[] recuperarFileUrl(String url, HttpServletResponse response) throws Exception {
		InputStream input = new URL(url).openStream();
		return recuperarFileInputStream(input, response,null);
	}

}