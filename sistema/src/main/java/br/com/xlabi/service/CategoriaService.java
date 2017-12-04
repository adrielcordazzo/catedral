
	package br.com.xlabi.service;

	import java.util.Date;
	import java.util.List;

	import javax.transaction.Transactional;

	import org.hibernate.criterion.Restrictions;
	import org.hibernate.criterion.SimpleExpression;
	import org.springframework.stereotype.Service;

	import br.com.xlabi.entity.Categoria;
	import br.com.xlabi.result.PaginateForm;
	import br.com.xlabi.result.Result;
	import br.com.xlabi.result.SessaoUser;
	import br.com.xlabi.service.geral.AbstractService;

	@Service
	@Transactional
	public class CategoriaService extends AbstractService<String, Categoria> {	public Categoria save(Categoria categoria, SessaoUser sessao) {

		setReferencias(categoria, sessao);
		try {
			super.save(categoria);
			return categoria;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Categoria categoria = get(id, sessao);
			if (categoria != null) {
				super.delete(categoria);
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

	public Categoria get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante.id", null, sessao);
		Categoria temp = super.get(restricao, ruser, rcontratante);
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

	public List<Categoria> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Categoria>) result.getList();
	}

	public Result listAll(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Categoria categoria) {
		if (categoria != null) {
			categoria.getCriado();
		}
	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Categoria) p);
			}
		}
	}

	public Categoria synchronizeAndSave(Categoria p, Categoria vs) {

		super.save(p);
		return p;
	}

	public Categoria setReferencias(Categoria p, SessaoUser sessao) {
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

