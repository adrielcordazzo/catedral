
package br.com.xlabi.service.geral;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xlabi.configuration.SecurityUtil;
import br.com.xlabi.entity.geral.Contratante;
import br.com.xlabi.entity.geral.Role;
import br.com.xlabi.entity.geral.Usuario;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;

@Service
@Transactional
public class ContratanteService extends AbstractService<String, Contratante> {
	
	@Autowired
	RoleService roleServ;
	
	@Autowired
	UsuarioService usuarioServ;

	public Contratante save(Contratante contratante, SessaoUser sessao) {
		
		List<Role> roles = new ArrayList<Role>();
		
		Boolean novo = false;
		if(contratante.getId() == null){
			novo = true;		
			
			Role role = roleServ.get("402880eb5ef2c4ef015ef30882950000", sessao);
			roles.add(role);
			
			contratante.setRoles(roles);
		}

		setReferencias(contratante, sessao);
		try {
			super.save(contratante);
			
			if(novo){
				Usuario usuario = new Usuario();
				usuario.setAtualizado(new Date());
				usuario.setContratante(contratante.getId());
				usuario.setCriado(new Date());
				usuario.setEmail(contratante.getEmail());
				usuario.setNome(contratante.getResponsavel());
				String pass = SecurityUtil.getHash("xlabi", "SHA-256");
				usuario.setSenha(pass);
				usuario.setRoles(roles);
				usuario.setTelefone(contratante.getFone());
				
				usuarioServ.save(usuario, sessao);
			}
			
			return contratante;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(String id, SessaoUser sessao) {
		try {
			Contratante contratante = get(id, sessao);
			if (contratante != null) {
				super.delete(contratante);
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

	public Contratante get(String id, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq("id", id);
		Contratante c = super.get(restricao);
		inicialize(c);
		return c;
	}

	public Integer CountFk(String campo, String value, SessaoUser sessao) {
		SimpleExpression restricao = Restrictions.eq(campo, value);
		// SimpleExpression ruser = retriction("usuario.id",
		// sessao.getUsuario());
		// SimpleExpression rcontratante = retriction("contratante.id",
		// sessao.getContratante());
		return super.countFK(restricao);
	}

	public Result list(PaginateForm pages, SessaoUser sessao) {
		SimpleExpression restricao = null;
		// SimpleExpression ruser = retriction("usuario.id",
		// sessao.getUsuario());
		// SimpleExpression rcontratante = retriction("contratante.id",
		// sessao.getContratante());

		Result result = super.listRestriction(pages, "nome", "nome");

		inicializeList(result.getList());
		return result;
	}

	public Result listAll(SessaoUser sessao) {

		// SimpleExpression ruser = retriction("usuario.id",
		// sessao.getUsuario());

		Result result = super.listAllRestricao();

		inicializeList(result.getList());
		return result;
	}

	public void inicialize(Contratante contratante) {
		if (contratante != null) {
			contratante.getId();
			for (Role r : contratante.getRoles()) {
				r.getRole();
			}

		}

	}

	public void inicializeList(List<?> list) {
		if (list != null)
			for (Object p : list) {
				inicialize((Contratante) p);
			}
	}

	public Contratante synchronizeAndSave(Contratante p, Contratante vs) {

		super.save(p);
		return p;
	}

	public Contratante setReferencias(Contratante p, SessaoUser sessao) {

		if (p.getInativo() == null)
			p.setInativo(0);

		p.setCriado(new Date());
		p.setAtualizado(new Date());
		if (p.getExcluido() == null)
			p.setExcluido(0);

		return p;
	}

}