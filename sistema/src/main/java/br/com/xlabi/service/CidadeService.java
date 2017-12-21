
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

import br.com.xlabi.entity.Cidade;
import br.com.xlabi.entity.Membro;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.geral.AbstractService;

@Service
@Transactional
public class CidadeService extends AbstractService<String, Cidade> {

	@Caching(evict = { @CacheEvict(value = "listAllEntityCidade", key = "#sessao.contratante.id"),
			@CacheEvict(value = "listAllCidade", key = "#sessao.contratante.id") })
	public Cidade save(Cidade cidade, SessaoUser sessao) {

		setReferencias(cidade, sessao);
		try {
			super.save(cidade);
			return cidade;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Cidade cidade = get(id, sessao);
			if (cidade != null) {
				super.delete(cidade);
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

	public Cidade get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Cidade temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
	}
	
	public Cidade getNome(String nome, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("nome", nome);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		return super.get(restricao, rcontratante);
	}

	public Cidade externalid(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("externalid", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Cidade temp = super.get(restricao, ruser, rcontratante);
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

	@Cacheable(value = "listAllEntityCidade", key = "#sessao.contratante.id")
	public List<Cidade> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Cidade>) result.getList();
	}

	@Cacheable(value = "listAllCidade", key = "#sessao.contratante.id")
	public Result listAll(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(Order.asc("nome"), rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Cidade cidade) {
		if (cidade != null) {
			cidade.getCriado();
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Cidade) p);
			}
		}
	}

	public Cidade synchronizeAndSave(Cidade p, Cidade vs) {

		super.save(p);
		return p;
	}

	public Cidade setReferencias(Cidade p, SessaoUser sessao) {
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
