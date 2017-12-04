//
// package br.com.xlabi.service.geral;
//
// import java.util.Date;
// import java.util.List;
//
// import javax.transaction.Transactional;
//
// import org.hibernate.criterion.Restrictions;
// import org.hibernate.criterion.SimpleExpression;
// import org.springframework.stereotype.Service;
//
// import br.com.xlabi.entity.geral.Configuracao;
// import br.com.xlabi.result.PaginateForm;
// import br.com.xlabi.result.Result;
// import br.com.xlabi.result.SessaoUser;
//
// @Service
// @Transactional
// public class ConfiguracaoService extends AbstractService<String,
// Configuracao> {
//
// public Configuracao save(Configuracao config, SessaoUser sessao) {
//
// setReferencias(config, sessao);
// try {
// super.save(config);
// return config;
// } catch (Exception e) {
// e.printStackTrace();
// }
// return null;
// }
//
// public boolean delete(String id, SessaoUser sessao) {
// try {
// Configuracao config = get(id, sessao);
// if (config != null) {
// super.delete(config);
// return true;
// }
// } catch (Exception e) {
// e.printStackTrace();
// }
// return false;
// }
//
// public Integer count(SessaoUser sessao) {
// SimpleExpression restricao = null;
//
// SimpleExpression rcontratante = retriction("contratante.id",
// sessao.getContratante());
// return super.count(restricao, rcontratante);
// }
//
// public Configuracao get(String id, SessaoUser sessao) {
// SimpleExpression restricao = Restrictions.eq("id", id);
// // SimpleExpression ruser = retriction("usuario.id",
// // sessao.getUsuario());
// // SimpleExpression rcontratante = retriction("contratante.id",
// // sessao.getContratante());
// return super.get(restricao);
// }
//
// public Integer CountFk(String campo, String value, SessaoUser sessao) {
// SimpleExpression restricao = Restrictions.eq(campo, value);
//
// SimpleExpression rcontratante = retriction("contratante.id",
// sessao.getContratante());
// return super.countFK(restricao, rcontratante);
// }
//
// public Result list(PaginateForm pages, SessaoUser sessao) {
// SimpleExpression restricao = null;
//
// SimpleExpression rcontratante = retriction("contratante.id",
// sessao.getContratante());
// Result result = super.listRestriction(pages, "CONFIGNAME", "CONFIGNAME",
// restricao, rcontratante);
// inicializeList(result.getList());
// return result;
// }
//
// public Result listAll(SessaoUser sessao) {
//
// SimpleExpression rcontratante = retriction("contratante.id",
// sessao.getContratante());
//
// Result result = super.listAllRestricao(rcontratante);
// inicializeList(result.getList());
// return result;
// }
//
// public void inicialize(Configuracao config) {
// config.getCriado();
// }
//
// public void inicializeList(List<?> list) {
// if (list != null)
// for (Object p : list) {
// inicialize((Configuracao) p);
// }
// }
//
// public Configuracao setReferencias(Configuracao p, SessaoUser sessao) {
// p.setContratante(sessao.getContratante());
//
// p.setCriado(new Date());
// p.setAtualizado(new Date());
//
// return p;
// }
//
// public Configuracao synchronizeAndSave(Configuracao p, Configuracao vs) {
//
// super.save(p);
// return p;
// }
//
// }
