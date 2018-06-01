

	package br.com.xlabi.service;

	import java.util.Date;
	import java.util.List;

	import javax.transaction.Transactional;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
	import org.hibernate.criterion.SimpleExpression;
	import org.springframework.stereotype.Service;

	import br.com.xlabi.service.geral.AbstractService;

	import br.com.xlabi.entity.Comentario;
	import br.com.xlabi.entity.geral.Pasta;
	import br.com.xlabi.result.PaginateForm;
	import br.com.xlabi.result.Result;
	import br.com.xlabi.result.SessaoUser;

	@Service
	@Transactional
	public class ComentarioService extends AbstractService<String, Comentario> {
			
		
	public Comentario save(Comentario comentario, SessaoUser sessao) {
				
				
			setReferencias(comentario, sessao);
			try {
				super.save(comentario);
				return comentario;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		public boolean delete(String id, SessaoUser sessao) {
			try {
				Comentario comentario = get(id, sessao);
				if (comentario != null) {
					super.delete(comentario);
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

		public Comentario get(String id, SessaoUser sessao) {
			SimpleExpression restricao = Restrictions.eq("id", id);
			SimpleExpression ruser = retriction("usuario.id", sessao.getUsuario());
			SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
            Comentario comentario = super.get(restricao, ruser, rcontratante);
            inicialize(comentario);
			return comentario;
		}

        public Comentario getIdExterno(String id, SessaoUser sessao) {
    		SimpleExpression restricao = Restrictions.eq("externalid", id);
    		SimpleExpression ruser = retriction("usuario.id", null, sessao);
    		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
    		Comentario temp = super.get(restricao, ruser, rcontratante);
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
			
			Criterion rnulo = null;
			Integer indicenulo = pages.getCampos().indexOf("conteudo.id");
			if (indicenulo > -1) {
				if (pages.getValues().get(indicenulo).equals("NULL")) {
					pages.getCampos().remove("conteudo.id");
					rnulo = Restrictions.isNull("conteudo.id");
					pages.getValues().remove("NULL");
				} 
			}
			
			Result result = super.listRestriction(pages, "nome", "id", restricao, ruser, rcontratante, rnulo);
			inicializeList(result.getList());
			return result;
		}

        public List<Comentario> listAllEntity(SessaoUser sessao) {
    		SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());
    		Result result = super.listAllRestricao(rcontratante);
    		inicializeList(result.getList());
    		return (List<Comentario>) result.getList();
    	}


		public Result listAll(SessaoUser sessao) {

		// SimpleExpression ruser = retriction("usuario.id",
		// sessao.getUsuario());
			SimpleExpression rcontratante = retriction("contratante.id", sessao.getContratante());

			Result result = super.listAllRestricao(rcontratante);

			inicializeList(result.getList());
			return result;
		}

		public void inicialize(Comentario comentario) {
			if(comentario != null){
				comentario.getCriado();
			}
		}

		public void inicializeList(List<?> list) {
			if(list != null){
				for (Object p : list) {
					inicialize((Comentario) p);
				}
			}
		}

		public Comentario synchronizeAndSave(Comentario p, Comentario vs) {

			super.save(p);
			return p;
		}

		public Comentario setReferencias(Comentario p, SessaoUser sessao) {
			p.setContratante(sessao.getContratante());
			p.setUsuario(sessao.getUsuario());
			if (p.getInativo() == null)
				p.setInativo(0);

			if(p.getCriado() == null)
				p.setCriado(new Date());
			p.setAtualizado(new Date());
			if (p.getExcluido() == null)
				p.setExcluido(0);

			return p;
		}

	}