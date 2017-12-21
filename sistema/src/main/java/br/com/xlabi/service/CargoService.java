
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

import br.com.xlabi.entity.Cargo;
import br.com.xlabi.entity.Estado;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.geral.AbstractService;

@Service
@Transactional
public class CargoService extends AbstractService<String, Cargo> {

	public Cargo save(Cargo cargo, SessaoUser sessao) {

		setReferencias(cargo, sessao);
		try {
			super.save(cargo);
			return cargo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Cargo cargo = get(id, sessao);
			if (cargo != null) {
				super.delete(cargo);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Cargo externalid(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("externalid", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Cargo temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
	}

	public Integer count(SessaoUser sessao) {
		SimpleExpression restricao = null;
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		return super.count(restricao, ruser, rcontratante);
	}

	public Cargo get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Cargo temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
	}
	
	public Cargo getNome(String nome, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("cargo", nome);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		return super.get(restricao, rcontratante);
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
		Result result = super.listRestriction(pages, "cargo", "cargo", restricao, ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public List<Cargo> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Cargo>) result.getList();
	}

	public Result listAll(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(Order.asc("cargo"), rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Cargo cargo) {
		if (cargo != null) {
			cargo.getId();
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Cargo) p);
			}
		}
	}

	public Cargo synchronizeAndSave(Cargo p, Cargo vs) {

		super.save(p);
		return p;
	}

	public Cargo setReferencias(Cargo p, SessaoUser sessao) {
		p.setContratante(sessao.getContratante());
		p.setUsuario(sessao.getUsuario());

		return p;
	}

}
