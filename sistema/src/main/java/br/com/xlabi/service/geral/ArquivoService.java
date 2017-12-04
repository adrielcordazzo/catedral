
package br.com.xlabi.service.geral;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.xlabi.entity.geral.Arquivo;
import br.com.xlabi.entity.geral.Pasta;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;

@Service
@Transactional
public class ArquivoService extends AbstractService<String, Arquivo> {

	@Autowired
	PastaService pastaServ;

	public Arquivo save(Arquivo arquivo) {
		try {
			super.save(arquivo);
			return arquivo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Arquivo saveFile(MultipartFile input, Pasta pasta, SessaoUser sessao, Integer deleteFolder)
			throws IOException {

		if (deleteFolder == 1) {
			SimpleExpression re = Restrictions.eq("pasta.id", pasta.getId());
			Result r = this.listAllRestricao(re);
			List<Arquivo> ar = (List<Arquivo>) r.getList();
			for (Arquivo a : ar) {
				this.delete(a);
			}
		}

		pasta = pastaServ.get(pasta.getId(), null);

		String contentType = input.getContentType();
		String filename = input.getOriginalFilename();
		String extension = filename.substring(filename.lastIndexOf(".") + 1);
		Path file = Paths.get(pastaServ.getCaminho(pasta.getContratante()) + pasta.getId() + "/" + filename);

		Path parentDir = file.getParent();
		if (!Files.exists(parentDir))
			Files.createDirectories(parentDir);

		byte data[] = IOUtils.toByteArray(input.getInputStream());
		Files.write(file, data);

		Arquivo arq = new Arquivo();
		arq.setTipo(contentType);
		arq.setNome(filename);
		arq.setContratante(sessao.getContratante());
		arq.setExtensao(extension);
		arq.setUsuario(sessao.getUsuario());
		arq.setPasta(pasta);
		arq.setTamanho(Files.size(file) + "");

		this.save(arq);

		return arq;

	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Arquivo arquivo = get(id, sessao);
			if (arquivo != null) {
				super.delete(arquivo);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Integer count(SessaoUser sessao) {
		SimpleExpression restricao = null;
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		return super.count(restricao, ruser, rcontratante);
	}

	public Arquivo get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		// SimpleExpression ruser = retriction("usuario.id",
		// sessao.getUsuario());
		// SimpleExpression rcontratante = retriction("contratante.id",
		// sessao.getContratante());
		return super.get(restricao);
	}

	public Integer CountFk(String campo, String value, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq(campo, value);
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		return super.countFK(restricao, ruser, rcontratante);
	}

	public Result list(PaginateForm pages, SessaoUser sessao) {
		SimpleExpression restricao = null;
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listRestriction(pages, "ordena", "nome", restricao, ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public Result listAll(SessaoUser sessao) {
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());

		Result result = super.listAllRestricao(Order.asc("ordena"), ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public Result listAllPasta(SessaoUser sessao, String pasta) {
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		SimpleExpression pasta1 = Restrictions.eq("pasta.id", pasta);
		Result result = super.listAllRestricao(Order.asc("ordena"), ruser, rcontratante, pasta1);
		inicializeList(result.getList());
		return result;
	}

	public Result listAll(String id) {
		SimpleExpression ruser = Restrictions.eq("pasta.id", id);

		Result result = super.listAllRestricao(Order.asc("ordena"), ruser);
		inicializeList(result.getList());
		return result;
	}

	public List<Arquivo> getFilesPasta(String id) {
		SimpleExpression ruser = Restrictions.eq("pasta.id", id);

		Result result = super.listAllRestricao(Order.asc("ordena"), ruser);
		if (result.getList() == null) {
			return null;
		}
		inicializeList(result.getList());

		if (result.getList() != null) {
			return (List<Arquivo>) result.getList();
		}

		return new ArrayList<Arquivo>();

	}

	public Arquivo getFilePasta(String id) {
		SimpleExpression ruser = Restrictions.eq("pasta.id", id);

		Result result = super.listAllRestricao(Order.desc("principal"), ruser);
		if (result.getList() == null) {
			return null;
		}
		inicializeList(result.getList());
		if (!(result.getList().size() > 0)) {
			return null;
		}
		Arquivo a = (Arquivo) result.getList().get(0);
		return a;
	}
	

	public Arquivo getArquivo(String id) {
		SimpleExpression ruser = Restrictions.eq("id", id);

		Result result = super.listAllRestricao(Order.asc("ordena"), ruser);

		System.out.println("IMAGEM " + result.getList());
		if (!(result.getList().size() > 0)) {
			return null;
		}
		inicializeList(result.getList());
		Arquivo a = (Arquivo) result.getList().get(0);
		return a;
	}

	public void inicialize(Arquivo arquivo) {
		arquivo.getCriado();
	}

	public void inicializeList(List<?> list) {
		for (Object p : list) {
			inicialize((Arquivo) p);
		}
	}

	public Arquivo synchronizeAndSave(Arquivo p, Arquivo vs) {

		super.save(p);
		return p;
	}

}