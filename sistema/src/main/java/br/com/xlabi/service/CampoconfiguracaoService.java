
package br.com.xlabi.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import br.com.xlabi.entity.Campoconfiguracao;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.geral.AbstractService;

@Service
@Transactional
public class CampoconfiguracaoService extends AbstractService<String, Campoconfiguracao> {
	public Campoconfiguracao save(Campoconfiguracao campoconfiguracao, SessaoUser sessao) {

		setReferencias(campoconfiguracao, sessao);
		try {
			super.save(campoconfiguracao);
			return campoconfiguracao;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Campoconfiguracao campoconfiguracao = get(id, sessao);
			if (campoconfiguracao != null) {
				super.delete(campoconfiguracao);
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
		LogicalExpression rcontratante = Restrictions.or(retriction("contratante", sessao.getContratante()),
				Restrictions.isNull("contratante"));
		return super.count(restricao, ruser, rcontratante);
	}

	public Campoconfiguracao getVariavel(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("variavel", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		LogicalExpression rcontratante = Restrictions.or(retriction("contratante", sessao.getContratante()),
				Restrictions.isNull("contratante"));
		Campoconfiguracao temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
	}

	public Campoconfiguracao get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		LogicalExpression rcontratante = Restrictions.or(retriction("contratante", sessao.getContratante()),
				Restrictions.isNull("contratante"));
		Campoconfiguracao temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
	}

	public Integer CountFk(String campo, String value, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq(campo, value);
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		LogicalExpression rcontratante = Restrictions.or(retriction("contratante", sessao.getContratante()),
				Restrictions.isNull("contratante"));
		return super.countFK(restricao, ruser, rcontratante);
	}

	public Result list(PaginateForm pages, SessaoUser sessao) {
		SimpleExpression restricao = null;
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		LogicalExpression rcontratante = Restrictions.or(retriction("contratante", sessao.getContratante()),
				Restrictions.isNull("contratante"));
		Result result = super.listRestriction(pages, "externalid", "externalid", restricao, ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public List<Campoconfiguracao> listAllEntity(SessaoUser sessao) {
		LogicalExpression rcontratante = Restrictions.or(retriction("contratante", sessao.getContratante()),
				Restrictions.isNull("contratante"));
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Campoconfiguracao>) result.getList();
	}

	public Result listAll(SessaoUser sessao) {
		LogicalExpression rcontratante = Restrictions.or(retriction("contratante", sessao.getContratante()),
				Restrictions.isNull("contratante"));
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Campoconfiguracao campoconfiguracao) {
		if (campoconfiguracao != null) {
			campoconfiguracao.getCriado();
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Campoconfiguracao) p);
			}
		}
	}

	public Campoconfiguracao synchronizeAndSave(Campoconfiguracao p, Campoconfiguracao vs) {

		super.save(p);
		return p;
	}

	public Campoconfiguracao setReferencias(Campoconfiguracao p, SessaoUser sessao) {
		p.setContratante(sessao.getContratante().getId());
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
