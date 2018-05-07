
package br.com.xlabi.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xlabi.entity.Categoria;
import br.com.xlabi.entity.Evento;
import br.com.xlabi.entity.Eventomembro;
import br.com.xlabi.entity.Membro;
import br.com.xlabi.entity.geral.Pasta;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.geral.AbstractService;
import br.com.xlabi.service.geral.PastaService;

@Service
@Transactional
public class EventoService extends AbstractService<String, Evento> {
	
	@Autowired
	PastaService pastaServ;

	public Evento save(Evento evento, SessaoUser sessao) {
		
		if (evento.getId() == null) {
			Pasta p = new Pasta();
			p.setPasta("Template");
			pastaServ.save(p, sessao);
			evento.setPasta(p);
		}

		setReferencias(evento, sessao);
		try {
			super.save(evento);
			return evento;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Evento evento = get(id, sessao);
			if (evento != null) {
				super.delete(evento);
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

	public Evento get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Evento temp = super.get(restricao, ruser, rcontratante);
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
		Result result = super.listRestriction(pages, "data", "titulo", restricao, ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public List<Evento> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Evento>) result.getList();
	}

	public Result listAll(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Evento evento) {
		if (evento != null) {
			evento.getId();
			if(evento.getMembros() != null) {
				for(Eventomembro m : evento.getMembros()) {
					m.getId();
				}
			}
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Evento) p);
			}
		}
	}

	public Evento synchronizeAndSave(Evento p, Evento vs) {

		super.save(p);
		return p;
	}

	public Evento setReferencias(Evento p, SessaoUser sessao) {
		
		p.setContratante(sessao.getContratante());
		p.setUsuario(sessao.getUsuario());

		if(p.getMembros() != null){
			for(Eventomembro a : p.getMembros()){
				a.setContratante(sessao.getContratante());
				a.setUsuario(sessao.getUsuario());
				a.setEvento(p);
			}
		}
		
		return p;
	}

}
