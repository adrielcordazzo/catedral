
package br.com.xlabi.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import br.com.xlabi.entity.Cidade;
import br.com.xlabi.entity.Estado;
import br.com.xlabi.entity.Membro;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.geral.AbstractService;

@Service
@Transactional
public class EstadoService extends AbstractService<String, Estado> {

	@Caching(evict = { @CacheEvict(value = "EstadolistAllEntity", key = "#sessao.contratante.id"),
			@CacheEvict(value = "EstadolistAll", key = "#sessao.contratante.id") })
	public Estado save(Estado estado, SessaoUser sessao) {

		setReferencias(estado, sessao);
		try {
			super.save(estado);
			return estado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Estado estado = get(id, sessao);
			if (estado != null) {
				super.delete(estado);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Estado externalid(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("externalid", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Estado temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
	}

	public Integer count(SessaoUser sessao) {
		SimpleExpression restricao = null;
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		return super.count(restricao, ruser, rcontratante);
	}

	public Estado get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Estado temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
	}
	
	public Estado getUf(String nome, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("uf", nome);
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
		Result result = super.listRestriction(pages, "nome", "nome", restricao, ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	@Cacheable(value = "EstadolistAllEntity", key = "#sessao.contratante.id")
	public List<Estado> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Estado>) result.getList();
	}

	@Cacheable(value = "EstadolistAll", key = "#sessao.contratante.id")
	public Result listAll(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Estado estado) {
		if (estado != null) {
			estado.getCriado();
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Estado) p);
			}
		}
	}

	public Estado synchronizeAndSave(Estado p, Estado vs) {

		super.save(p);
		return p;
	}

	public Estado setReferencias(Estado p, SessaoUser sessao) {
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
