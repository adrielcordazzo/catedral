//
//	package br.com.xlabi.service;
//
//	import java.util.Date;
//	import java.util.List;
//
//	import javax.transaction.Transactional;
//
//	import org.hibernate.criterion.Restrictions;
//	import org.hibernate.criterion.SimpleExpression;
//	import org.springframework.stereotype.Service;
//
//	import br.com.xlabi.entity.Contratante_roles;
//	import br.com.xlabi.result.PaginateForm;
//	import br.com.xlabi.result.Result;
//	import br.com.xlabi.result.SessaoUser;
//	import br.com.xlabi.service.geral.AbstractService;
//
//	@Service
//	@Transactional
//	public class Contratante_rolesService extends AbstractService<String, Contratante_roles> {	public Contratante_roles save(Contratante_roles contratante_roles, SessaoUser sessao) {
//
//		setReferencias(contratante_roles, sessao);
//		try {
//			super.save(contratante_roles);
//			return contratante_roles;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public boolean delete(String id, SessaoUser sessao) {
//		try {
//			Contratante_roles contratante_roles = get(id, sessao);
//			if (contratante_roles != null) {
//				super.delete(contratante_roles);
//				return true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	public Integer count(SessaoUser sessao) {
//		SimpleExpression restricao = null;
//		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
//		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
//		return super.count(restricao, ruser, rcontratante);
//	}
//
//	public Contratante_roles get(String id, SessaoUser sessao) {
//		SimpleExpression restricao = Restrictions.eq("id", id);
//		SimpleExpression ruser = retriction("usuario.id", null, sessao);
//		SimpleExpression rcontratante = retriction("contratante.id", null, sessao);
//		Contratante_roles temp = super.get(restricao, ruser, rcontratante);
//		inicialize(temp);
//		return temp;
//	}
//
//	public Integer CountFk(String campo, String value, SessaoUser sessao) {
//		SimpleExpression restricao = Restrictions.eq(campo, value);
//		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
//		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
//		return super.countFK(restricao, ruser, rcontratante);
//	}
//
//	public Result list(PaginateForm pages, SessaoUser sessao) {
//		SimpleExpression restricao = null;
//		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
//		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
//		Result result = super.listRestriction(pages, "externalid", "externalid", restricao, ruser, rcontratante);
//		inicializeList(result.getList());
//		return result;
//	}
//
//	public List<Contratante_roles> listAllEntity(SessaoUser sessao) {
//		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
//		Result result = super.listAllRestricao(rcontratante);
//		inicializeList(result.getList());
//		return (List<Contratante_roles>) result.getList();
//	}
//
//	public Result listAll(SessaoUser sessao) {
//		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
//		Result result = super.listAllRestricao(rcontratante);
//		inicializeList(result.getList());
//		return result;
//	}
//
//	public void inicialize(Contratante_roles contratante_roles) {
//		if (contratante_roles != null) {
//			contratante_roles.getCriado();
//		}
//	}
//
//	public void inicializeList(List<?> list) {
//		if (list != null) {
//			for (Object p : list) {
//				inicialize((Contratante_roles) p);
//			}
//		}
//	}
//
//	public Contratante_roles synchronizeAndSave(Contratante_roles p, Contratante_roles vs) {
//
//		super.save(p);
//		return p;
//	}
//
//	public Contratante_roles setReferencias(Contratante_roles p, SessaoUser sessao) {
//		p.setContratante(sessao.getContratante());
//		p.setUsuario(sessao.getUsuario());
//		if (p.getInativo() == null)
//			p.setInativo(0);
//
//		p.setCriado(new Date());
//		p.setAtualizado(new Date());
//		if (p.getExcluido() == null)
//			p.setExcluido(0);
//
//		return p;
//	}
//
//}
//
