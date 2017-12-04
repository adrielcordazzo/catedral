
package br.com.xlabi.service.geral;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import br.com.xlabi.entity.geral.Contratante;
import br.com.xlabi.entity.geral.Pasta;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;

@Service
@Transactional
public class PastaService extends AbstractService<String, Pasta> {

	public static final String CAMINHO = "/var/www/html/APPLICATIONFILES/imogle/";
	// public static final String CAMINHO = "C:/Xlabi/clinarquivos/";

	public String getCaminho(SessaoUser sessao) {
		return CAMINHO + sessao.getContratante().getId() + "/";
	}

	public String getCaminho(Contratante contratante) {
		return CAMINHO + contratante.getId() + "/";
	}

	public Pasta save(Pasta pasta, SessaoUser sessao) {

		setReferencias(pasta, sessao);
		try {
			super.save(pasta);
			return pasta;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Pasta pasta = get(id, sessao);
			if (pasta != null) {
				super.delete(pasta);
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

	public Pasta get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		// SimpleExpression ruser = retriction("usuario.id",
		// sessao.getUsuario());
		// SimpleExpression rcontratante = retriction("contratante.id",
		// sessao.getContratante());
		return super.get(restricao);
	}
	
	public Pasta get(String id) {
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
		Result result = super.listRestriction(pages, "pasta", "pasta", restricao, ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public Result listAll(SessaoUser sessao) {
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());

		Result result = super.listAllRestricao(ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Pasta pasta) {
		pasta.getCriado();
	}

	public void inicializeList(List<?> list) {
		if (list != null)
			for (Object p : list) {
				inicialize((Pasta) p);
			}
	}

	public Pasta setReferencias(Pasta p, SessaoUser sessao) {
		p.setContratante(sessao.getContratante());
		p.setUsuario(sessao.getUsuario());
		if (p.getInativo() == null)
			p.setInativo(0);

		p.setCriado(new Date());
		p.setAtualizado(new Date());
		if (p.getExcluido() == null)
			p.setExcluido(0);

		return p;
	}

	public Pasta synchronizeAndSave(Pasta p, Pasta vs) {

		super.save(p);
		return p;
	}

}