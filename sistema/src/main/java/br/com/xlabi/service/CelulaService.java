
package br.com.xlabi.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xlabi.service.geral.AbstractService;
import br.com.xlabi.service.geral.PastaService;
import br.com.xlabi.entity.Celula;
import br.com.xlabi.entity.geral.Pasta;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;

@Service
@Transactional
public class CelulaService extends AbstractService<String, Celula> {
	@Autowired
	PastaService pastaServ;
	
	public Celula save(Celula celula, SessaoUser sessao) {
		
		if (celula.getId() == null) {
			Pasta p = new Pasta();
			p.setPasta("Template");
			pastaServ.save(p, sessao);
			celula.setPasta(p);
		}

		setReferencias(celula, sessao);
		try {
			super.save(celula);
			return celula;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Celula celula = get(id, sessao);
			if (celula != null) {
				super.delete(celula);
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

	public Celula get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Celula celula = super.get(restricao, ruser, rcontratante);
		inicialize(celula);
		return celula;
	}

	public Celula getIdExterno(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("externalid", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Celula temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
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
		Result result = super.listRestriction(pages, "nome", "nome", restricao, ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public List<Celula> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Celula>) result.getList();
	}

	public Result listAll(SessaoUser sessao) {

		// SimpleExpression ruser = retriction("usuario.id",
		// sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());

		Result result = super.listAllRestricao(rcontratante);

		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Celula celula) {
		if (celula != null) {
			celula.getCriado();
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Celula) p);
			}
		}
	}

	public Celula synchronizeAndSave(Celula p, Celula vs) {

		super.save(p);
		return p;
	}

	public Celula setReferencias(Celula p, SessaoUser sessao) {
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

}