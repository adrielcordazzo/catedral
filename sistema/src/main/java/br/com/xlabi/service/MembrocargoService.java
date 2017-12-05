
package br.com.xlabi.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import br.com.xlabi.entity.Membrocargo;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.geral.AbstractService;

@Service
@Transactional
public class MembrocargoService extends AbstractService<String, Membrocargo> {

	public Membrocargo save(Membrocargo membrocargo, SessaoUser sessao) {

		setReferencias(membrocargo, sessao);
		try {
			super.save(membrocargo);
			return membrocargo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Membrocargo membrocargo = get(id, sessao);
			if (membrocargo != null) {
				super.delete(membrocargo);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Membrocargo externalid(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("externalid", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Membrocargo temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
	}

	public Integer count(SessaoUser sessao) {
		SimpleExpression restricao = null;
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		return super.count(restricao, ruser, rcontratante);
	}

	public Membrocargo get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Membrocargo temp = super.get(restricao, ruser, rcontratante);
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

	public List<Membrocargo> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Membrocargo>) result.getList();
	}

	public Result listAll(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(Order.asc("nome"), rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Membrocargo membrocargo) {
		if (membrocargo != null) {
			membrocargo.getId();
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Membrocargo) p);
			}
		}
	}

	public Membrocargo synchronizeAndSave(Membrocargo p, Membrocargo vs) {

		super.save(p);
		return p;
	}

	public Membrocargo setReferencias(Membrocargo p, SessaoUser sessao) {


		return p;
	}

}
