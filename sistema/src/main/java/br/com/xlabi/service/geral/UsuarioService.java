
package br.com.xlabi.service.geral;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import br.com.xlabi.entity.geral.Pasta;
import br.com.xlabi.entity.geral.Role;
import br.com.xlabi.entity.geral.Usuario;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;

@Service
@Transactional
public class UsuarioService extends AbstractService<String, Usuario> {
	@Autowired
	PastaService pastaServ;

	@Caching(evict = { @CacheEvict(value = "UsuariolistAllEntity", key = "#sessao.contratante.id"),
			@CacheEvict(value = "UsuariolistAll", key = "#sessao.contratante.id") })
	public Usuario save(Usuario usuario, SessaoUser sessao) {
		try {
			if (usuario.getContratante() == null) {
				usuario.setContratante(sessao.getContratante().getId());
			}

			if (usuario.getId() == null) {
				Pasta p = new Pasta();
				p.setId(null);
				p.setPasta("usuario");
				pastaServ.save(p, sessao);
				usuario.setPasta(p);
			}

			super.save(usuario);

			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Usuario usuario = get(id, sessao);
			if (usuario != null) {
				super.delete(usuario);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Usuario externalid(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("externalid", id);
		SimpleExpression ruser = retriction("usuario.id", null, sessao);
		SimpleExpression rcontratante = retriction("contratante", sessao.getContratante());
		Usuario temp = super.get(restricao, ruser, rcontratante);
		inicialize(temp);
		return temp;
	}

	public Integer count(SessaoUser sessao) {
		SimpleExpression restricao = null;
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante", sessao.getContratante());
		return super.count(restricao, ruser, rcontratante);
	}

	public Usuario getUser(String id) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		SimpleExpression ruser = null;
		SimpleExpression rcontratante = null;
		return super.get(restricao, ruser, rcontratante);
	}

	public Usuario get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		// SimpleExpression ruser = retriction("usuario.id",
		// sessao.getUsuario());
		// SimpleExpression rcontratante = retriction("contratante",
		// sessao.getContratante());
		Usuario user = super.get(restricao);
		inicialize(user);
		return user;
	}

	public Integer CountFk(String campo, String value, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq(campo, value);
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante", sessao.getContratante());
		return super.countFK(restricao, ruser, rcontratante);
	}

	public Result list(PaginateForm pages, SessaoUser sessao) {
		SimpleExpression restricao = null;
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante", sessao.getContratante());
		Result result = super.listRestriction(pages, "nome", "nome", restricao, ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	@Cacheable(value = "UsuariolistAll", key = "#sessao.contratante.id")
	public Result listAll(SessaoUser sessao) {
		SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
		SimpleExpression rcontratante = retriction("contratante", sessao.getContratante());

		Result result = super.listAllRestricao(ruser, rcontratante);
		inicializeList(result.getList());
		return result;
	}

	@Cacheable(value = "UsuariolistAllEntity", key = "#sessao.contratante.id")
	public List<Usuario> listAllEntity(SessaoUser sessao) {
		SimpleExpression rcontratante = retriction("contratante", sessao.getContratante());
		Result result = super.listAllRestricao(rcontratante);
		inicializeList(result.getList());
		return (List<Usuario>) result.getList();
	}

	public void inicialize(Usuario usuario) {
		if (usuario != null) {
			usuario.getNome();
			if(usuario.getRoles() != null) {
				for(Role r : usuario.getRoles()) {
					r.getId();
					usuario.getPermissoes().add(r.getId());
				}
			}
		}

	}

	public void inicializeList(List<?> list) {
		if (list != null) {
			for (Object p : list) {
				inicialize((Usuario) p);
			}
		}
	}

	public Usuario synchronizeAndSave(Usuario p, Usuario vs) {

		super.save(p);
		return p;
	}

	public Usuario getEmail(String ssoId) {
		SimpleExpression restricao = Restrictions.eq("email", ssoId);
		SimpleExpression ruser = null;
		SimpleExpression rcontratante = null;
		System.out.println("passou aqui ");
		return super.get(restricao);
	}

}