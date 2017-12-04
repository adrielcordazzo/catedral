
	package br.com.xlabi.service;

	import java.util.Date;
	import java.util.List;

	import javax.transaction.Transactional;

	import org.hibernate.criterion.Restrictions;
	import org.hibernate.criterion.SimpleExpression;
	import org.springframework.stereotype.Service;

	import br.com.xlabi.entity.Contatotipo;
	import br.com.xlabi.result.PaginateForm;
	import br.com.xlabi.result.Result;
	import br.com.xlabi.result.SessaoUser;
	import br.com.xlabi.service.geral.AbstractService;

	@Service
	@Transactional
	public class ContatotipoService extends AbstractService<String, Contatotipo> {	public Contatotipo save(Contatotipo contatotipo, SessaoUser sessao) {

		setReferencias(contatotipo, sessao);
		try {
			super.save(contatotipo);
			return contatotipo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Contatotipo contatotipo = get(id, sessao);
			if (contatotipo != null) {
				super.delete(contatotipo);
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

	public Contatotipo get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Contatotipo temp = super.get(restricao, ruser, rcontratante);
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

	public List<Contatotipo> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Contatotipo>) result.getList();
	}

	public Result listAll(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Contatotipo contatotipo) {
		if (contatotipo != null) {
			contatotipo.getCriado();
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Contatotipo) p);
			}
		}
	}

	public Contatotipo synchronizeAndSave(Contatotipo p, Contatotipo vs) {

		super.save(p);
		return p;
	}

	public Contatotipo setReferencias(Contatotipo p, SessaoUser sessao) {
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

