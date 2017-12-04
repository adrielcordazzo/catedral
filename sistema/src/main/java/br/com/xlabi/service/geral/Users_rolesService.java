package br.com.xlabi.service.geral;
//
//package br.com.xlabi.service;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.hibernate.criterion.Restrictions;
//import org.hibernate.criterion.SimpleExpression;
//import org.springframework.stereotype.Service;
//
//import br.com.xlabi.entity.Users_roles;
//import br.com.xlabi.result.PaginateForm;
//import br.com.xlabi.result.Result;
//import br.com.xlabi.result.SessaoUser;
//
//@Service
//@Transactional
//public class Users_rolesService extends AbstractService<String, Users_roles> {
//
//	public Users_roles save(Users_roles users_roles) {
//		try {
//			super.save(users_roles);
//			return users_roles;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public boolean delete(String id, SessaoUser sessao) {
//		try {
//			Users_roles users_roles = get(id, sessao);
//			if (users_roles != null) {
//				super.delete(users_roles);
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
//	public Users_roles get(String id, SessaoUser sessao) {
//		SimpleExpression restricao = Restrictions.eq("id", id);
//		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
//		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
//		return super.get(restricao, ruser, rcontratante);
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
//		Result result = super.listRestriction(pages, "nome", "nome", restricao, ruser, rcontratante);
//		inicializeList(result.getList());
//		return result;
//	}
//
//	public Result listAll(SessaoUser sessao) {
//		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
//		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
//
//		Result result = super.listAllRestricao(ruser, rcontratante);
//		inicializeList(result.getList());
//		return result;
//	}
//
//	public void inicialize(Users_roles users_roles) {
//		users_roles.getRole_id();
//	}
//
//	public void inicializeList(List<?> list) {
//		for (Object p : list) {
//			inicialize((Users_roles) p);
//		}
//	}
//
//	public Users_roles synchronizeAndSave(Users_roles p, Users_roles vs) {
//
//		super.save(p);
//		return p;
//	}
//
//}