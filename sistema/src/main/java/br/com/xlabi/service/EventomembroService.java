
package br.com.xlabi.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import br.com.xlabi.entity.Eventomembro;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.geral.AbstractService;

@Service
@Transactional
public class EventomembroService extends AbstractService<String, Eventomembro> {
	public Eventomembro save(Eventomembro eventomembro, SessaoUser sessao) {

		setReferencias(eventomembro, sessao);
		try {
			super.save(eventomembro);
			return eventomembro;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Eventomembro eventomembro = get(id, sessao);
			if (eventomembro != null) {
				super.delete(eventomembro);
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

	public Eventomembro get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Eventomembro temp = super.get(restricao, ruser, rcontratante);
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
		Result result = super.listRestriction(pages, "id", "id", restricao, ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public List<Eventomembro> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Eventomembro>) result.getList();
	}

	public Result listAll(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Eventomembro eventomembro) {
		if (eventomembro != null) {
			eventomembro.getId();
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Eventomembro) p);
			}
		}
	}

	public Eventomembro synchronizeAndSave(Eventomembro p, Eventomembro vs) {

		super.save(p);
		return p;
	}

	public Eventomembro setReferencias(Eventomembro p, SessaoUser sessao) {

		return p;
	}

}
