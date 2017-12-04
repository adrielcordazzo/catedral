
	package br.com.xlabi.service;

	import java.util.Date;
	import java.util.List;

	import javax.transaction.Transactional;

	import org.hibernate.criterion.Restrictions;
	import org.hibernate.criterion.SimpleExpression;
	import org.springframework.stereotype.Service;

	import br.com.xlabi.entity.Conteudocategoria;
	import br.com.xlabi.result.PaginateForm;
	import br.com.xlabi.result.Result;
	import br.com.xlabi.result.SessaoUser;
	import br.com.xlabi.service.geral.AbstractService;

	@Service
	@Transactional
	public class ConteudocategoriaService extends AbstractService<String, Conteudocategoria> {	public Conteudocategoria save(Conteudocategoria conteudocategoria, SessaoUser sessao) {

		setReferencias(conteudocategoria, sessao);
		try {
			super.save(conteudocategoria);
			return conteudocategoria;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Conteudocategoria conteudocategoria = get(id, sessao);
			if (conteudocategoria != null) {
				super.delete(conteudocategoria);
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

	public Conteudocategoria get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Conteudocategoria temp = super.get(restricao, ruser, rcontratante);
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

	public List<Conteudocategoria> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Conteudocategoria>) result.getList();
	}

	public Result listAll(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Conteudocategoria conteudocategoria) {
		if (conteudocategoria != null) {
			conteudocategoria.getId();
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Conteudocategoria) p);
			}
		}
	}

	public Conteudocategoria synchronizeAndSave(Conteudocategoria p, Conteudocategoria vs) {

		super.save(p);
		return p;
	}

	public Conteudocategoria setReferencias(Conteudocategoria p, SessaoUser sessao) {
		

		return p;
	}

}

