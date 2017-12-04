
package br.com.xlabi.service.geral;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;

import br.com.xlabi.entity.geral.Role;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;

@Service
@Transactional
public class RoleService extends AbstractService<String, Role> {

	public Role save(Role role) {
		try {
			super.save(role);
			return role;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Role role = get(id, sessao);
			if (role != null) {
				super.delete(role);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Integer count(SessaoUser sessao) {
		SimpleExpression restricao = null;

		return super.count(restricao);
	}

	public Role get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);

		return super.get(restricao);
	}

	public Integer CountFk(String campo, String value, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq(campo, value);

		return super.countFK(restricao);
	}

	public Result list(PaginateForm pages, SessaoUser sessao) {
		SimpleExpression restricao = null;
		// SimpleExpression ruser = retriction("usuario.id",
		// sessao.getUsuario());
		// SimpleExpression rcontratante = retriction("contratante.id",
		// sessao.getContratante());
		Result result = super.listRestriction(pages, "role", "role", restricao);
		inicializeList(result.getList());
		return result;
	}

	public Result listAll(SessaoUser sessao) {
		Criterion ruser = Restrictions.isNull("visivel");
		// SimpleExpression rcontratante = retriction("contratante.id",
		// sessao.getContratante());

		Result result = super.listAllRestricao(ruser);
		inicializeList(result.getList());
		return result;
	}

	public List<Role> listAllEntity(SessaoUser sessao) {
		Criterion ruser = Restrictions.isNull("visivel");

		Result result = super.listAllRestricao(ruser);
		inicializeList(result.getList());
		return (List<Role>) result.getList();
	}

	public void inicialize(Role role) {
		role.getRole();
	}

	public void inicializeList(List<?> list) {
		if (list != null)
			for (Object p : list) {
				inicialize((Role) p);
			}
	}

	public Role synchronizeAndSave(Role p, Role vs) {

		super.save(p);
		return p;
	}

}