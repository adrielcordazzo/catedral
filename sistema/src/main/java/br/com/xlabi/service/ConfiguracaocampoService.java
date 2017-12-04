
package br.com.xlabi.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import br.com.xlabi.entity.Configuracaocampo;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.geral.AbstractService;

@Service
@Transactional
public class ConfiguracaocampoService extends AbstractService<String, Configuracaocampo> {
	public Configuracaocampo save(Configuracaocampo configuracaocampo, SessaoUser sessao) {

		setReferencias(configuracaocampo, sessao);
		try {
			super.save(configuracaocampo);
			return configuracaocampo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Configuracaocampo configuracaocampo = get(id, sessao);
			if (configuracaocampo != null) {
				super.delete(configuracaocampo);
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

	public Configuracaocampo get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Configuracaocampo temp = super.get(restricao, ruser, rcontratante);
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
		Result result = super.listRestriction(pages, "externalid", "externalid", restricao, ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public List<Configuracaocampo> listAllEntityContratante(String id) {
		SimpleExpression rcontratante = Restrictions.eq("contratante.id", id);
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Configuracaocampo>) result.getList();
	}

	public List<Configuracaocampo> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Configuracaocampo>) result.getList();
	}

	public Result listAll(SessaoUser sessao) {
		LogicalExpression rcontratante = Restrictions.or(retriction("contratante.id", sessao.getContratante()),
				Restrictions.isNull("contratante.id"));
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Configuracaocampo configuracaocampo) {
		if (configuracaocampo != null) {
			configuracaocampo.getCriado();
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Configuracaocampo) p);
			}
		}
	}

	public Configuracaocampo synchronizeAndSave(Configuracaocampo p, Configuracaocampo vs) {

		super.save(p);
		return p;
	}

	public void deleteCampos(String id) {
		System.out.println("VAI DELETAR CONTRATANTE xxx");
		String hql = "delete from Configuracaocampo p " + " where p.contratante.id=:id ";
		getSession().createQuery(hql).setString("id", id).executeUpdate();
	}

	public Configuracaocampo setReferencias(Configuracaocampo p, SessaoUser sessao) {
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
