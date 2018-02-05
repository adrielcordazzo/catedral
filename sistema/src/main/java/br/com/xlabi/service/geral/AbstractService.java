package br.com.xlabi.service.geral;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.xlabi.entity.Configuracaocampo;
import br.com.xlabi.entity.geral.AbstractEntity;
import br.com.xlabi.result.PaginateForm;
import br.com.xlabi.result.Result;
import br.com.xlabi.result.SessaoUser;
import br.com.xlabi.service.ConfiguracaocampoService;

public abstract class AbstractService<PK extends Serializable, T> {

	protected final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractService() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Map<String, String> getConfiguracao(List<Configuracaocampo> config) {

		Map<String, String> map = new HashMap<String, String>();
		for (Configuracaocampo c : config) {
			map.put(c.getCampo().getVariavel(), c.getValor());
		}

		return map;

	}

	@SuppressWarnings("unchecked")
	public T getBy(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public T save(T entity) {
		AbstractEntity e = (AbstractEntity) entity;
		if (e.getId() == null) {
			getSession().saveOrUpdate(entity);
		} else {
			update(entity);
		}

		return entity;
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

	public Result listBusca(PaginateForm pages, String order, String like) {
		return this.listRestriction(pages, order, like);
	}

	@SuppressWarnings("unchecked")
	public T get(Criterion... restricoes) {

		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		// criteria.setMaxResults(1);

		if (restricoes != null) {
			for (Criterion r : restricoes) {
				if (r != null)
					criteria.add(r);
			}
		}
		List<T> lista = null;
		try {
			lista = criteria.list();

			System.out.println("lista+" + lista.size());
			if (lista.size() > 0)
				return lista.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public Result listRestriction(PaginateForm pages, String order, String like, Criterion... restricoes) {

		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setMaxResults(pages.getMaxResult());
		if (pages.getOrder() == 0) {
			if (order != null)
				criteria.addOrder(Order.asc(order));
		} else {
			if (order != null)
				criteria.addOrder(Order.desc(order));
		}
		
		if(pages.getOrdercampo() != null) {
			if (pages.getOrder() == 0) {
				criteria.addOrder(Order.asc(pages.getOrdercampo()));
			}else {
				criteria.addOrder(Order.desc(pages.getOrdercampo()));
			}
		}

		if (pages.getAlias() != null) {
			criteria.createAlias(pages.getAlias(), "alias");
		}

		criteria.setFirstResult((pages.getPagina() - 1) * pages.getMaxResult());

		if (pages.getSearch() != null) {
			Criterion nome = Restrictions.like(like, "%" + pages.getSearch() + "%");
			criteria.add(nome);
		}

		if (restricoes != null) {
			for (Criterion r : restricoes) {
				if (r != null)
					criteria.add(r);
			}
		}

		if (pages.getCampos().size() > 0) {
			for (int i = 0; i <= pages.getCampos().size() - 1; i++) {
				try {
					String campo = pages.getCampos().get(i);
					String value = pages.getValues().get(i);
					if (value.equals("NULL") || value.equals("null")) {
						criteria.add(Restrictions.isNull(campo));
					} else if (value.equals("NOTNULL") || value.equals("notnull")) {
						criteria.add(Restrictions.isNotNull(campo));
					} else {
						criteria.add(Restrictions.eq(campo, value));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		Result result = new Result();
		List<T> lista = null;
		try {
			lista = criteria.list();

			Number qt = ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult());
			Integer totalResult = 0;
			if (qt != null)
				totalResult = qt.intValue();
			result.setList(lista);
			result.setCountResult(totalResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Integer count(Criterion... restricoes) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (restricoes != null) {
			for (Criterion r : restricoes) {
				if (r != null)
					criteria.add(r);
			}
		}
		Number qt = ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult());
		Integer totalResult = 0;
		if (qt != null)
			totalResult = qt.intValue();
		return totalResult;
	}

	public Integer countFK(Criterion... restricoes) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (restricoes != null) {
			for (Criterion r : restricoes) {
				if (r != null)
					criteria.add(r);
			}
		}
		Number qt = ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult());
		Integer totalResult = 0;
		if (qt != null)
			totalResult = qt.intValue();
		return totalResult;

	}

	public Result listAllRestricao(Criterion... restricoes) {

		return listAllRestricao(null, restricoes);
	}

	@SuppressWarnings("unchecked")
	public Result listAllRestricao(Order ordem, Criterion... restricoes) {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (restricoes != null) {
			for (Criterion r : restricoes) {
				if (r != null)
					criteria.add(r);
			}
		}
		if (ordem != null) {
			criteria.addOrder(ordem);
		}

		Result result = new Result();
		List<T> lista = null;
		try {
			lista = criteria.list();
			Number qt = ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult());
			Integer totalResult = 0;

			if (qt != null)
				totalResult = qt.intValue();

			result.setCountResult(totalResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

		result.setList(lista);

		return result;
	}

	public void UpdateQuery(String strQuery, Object... params) {
		org.hibernate.Query query = getSession().createQuery(strQuery);

		if (params.length > 0) {
			int i = 0;
			for (Object param : params) {
				query.setParameter(i, param);
				i++;
			}
		}

		int result = query.executeUpdate();

	}

	@SuppressWarnings("unchecked")
	public List<T> ListQuery(String strQuery, Object... params) {
		org.hibernate.Query query = getSession().createQuery(strQuery);

		if (params.length > 0) {
			int i = 0;
			for (Object param : params) {
				query.setParameter(i, param);
				i++;
			}
		}

		List<T> entity = query.list();

		return entity;

	}

	protected void logSistem(T modelo, String acao, String descricao) {

		// LogSistem log = new LogSistem();
		// log.setAcao(acao);
		// log.setClasse(modelo.getClass().getName());
		// log.setDate(new Date());
		// // log.setDescricao(descricao + modelo.getDescricaoLog());
		// // log.setIdClasse(modelo.getId());
		// // log.setUsuario(getPrincialSession().getUsuario());
		// try {
		// getSession().save(log);
		// } catch (Exception e) {
		//
		// }
	}

	public SimpleExpression retriction(String campo, String value, SessaoUser a) {

		if (a != null && !campo.equals("usuario.id") && !campo.equals("usuario")) {
			// return Restrictions.eq(campo, a.getUsuario().getId());
		}

		return null;
	}

	public SimpleExpression retriction(String campo, AbstractEntity a) {

		if (a != null && !campo.equals("usuario.id") && !campo.equals("usuario")) {
			return Restrictions.eq(campo, a.getId());
		}

		return null;
	}
	
	public SimpleExpression retrictionMembro(String campo, AbstractEntity a) {
		
		 if (a != null) {
			 return Restrictions.eq(campo, a.getId());
		 }

		return null;
	}

}
