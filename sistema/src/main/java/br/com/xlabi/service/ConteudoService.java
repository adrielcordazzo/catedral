
package br.com.xlabi.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xlabi.entity.Categoria;
import br.com.xlabi.entity.Conteudo;
import br.com.xlabi.entity.geral.Pasta;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.geral.AbstractService;
import br.com.xlabi.service.geral.PastaService;

@Service
@Transactional
public class ConteudoService extends AbstractService<String, Conteudo> {
	@Autowired
	PastaService pastaServ;

	public Conteudo save(Conteudo conteudo, SessaoUser sessao) {
		
		if (conteudo.getId() == null) {
			Pasta p = new Pasta();
			p.setPasta("Template");
			pastaServ.save(p, sessao);
			conteudo.setPasta(p);
		}

		setReferencias(conteudo, sessao);
		try {
			super.save(conteudo);
			return conteudo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Conteudo conteudo = get(id, sessao);
			if (conteudo != null) {
				super.delete(conteudo);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Conteudo externalid(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("externalid", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Conteudo temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
	}

	public Integer count(SessaoUser sessao) {
		SimpleExpression restricao = null;
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		return super.count(restricao, ruser, rcontratante);
	}

	public Conteudo get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Conteudo temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
	}
	
	public Conteudo getPorUrl(String url, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("url", url);
		SimpleExpression ruser = null;//retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Conteudo temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
	}
	
	public Result listUrl(String url, SessaoUser sessao) {
		SimpleExpression restricao = null;
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		SimpleExpression rurl = Restrictions.eq("url", url);
		PaginateForm pages = new PaginateForm();
		pages.setOrder(1);
		pages.setOrdercampo("titulo");
		Result result = super.listRestriction(pages, "titulo", "titulo", restricao, ruser, rcontratante, rurl);
		inicializeList(result.getList());
		return result;
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
		Result result = super.listRestriction(pages, "criado", "titulo", restricao, ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public List<Conteudo> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Conteudo>) result.getList();
	}

	public Result listAll(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Conteudo conteudo) {
		if (conteudo != null) {
			conteudo.getCriado();
			if(conteudo.getCategorias() != null){
				for(Categoria c : conteudo.getCategorias()){
					c.getId();
				}
			}
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Conteudo) p);
			}
		}
	}

	public Conteudo synchronizeAndSave(Conteudo p, Conteudo vs) {

		super.save(p);
		return p;
	}

	public Conteudo setReferencias(Conteudo p, SessaoUser sessao) {
		p.setContratante(sessao.getContratante());
		p.setUsuario(sessao.getUsuario());
		if (p.getInativo() == null)
			p.setInativo(0);

		p.setCriado(new Date());
		p.setAtualizado(new Date());
		if (p.getExcluido() == null)
			p.setExcluido(0);
		
		if(p.getCategorias() != null){
			for(Categoria a : p.getCategorias()){
				//a.setContratante(sessao.getContratante());
				a.setUsuario(sessao.getUsuario());
				if (a.getInativo() == null)
					a.setInativo(0);

				a.setCriado(new Date());
				a.setAtualizado(new Date());
				if (a.getExcluido() == null)
					a.setExcluido(0);
			}
		}

		return p;
	}

}
