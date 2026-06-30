package com.camel.common.object;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

    private final Class<T> targetClass;

    @PersistenceContext
    private EntityManager entityManager;

    public BaseDaoImpl() {
        this.targetClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public List<T> getAll() {
        String hql = "from " + targetClass.getName();
        return getCurrentSession()
                .createQuery(hql, targetClass)
                .getResultList();
    }

    @Override
    public List<T> getAllMatching(Map<String, Object> criteria, boolean asc) {

        StringBuilder hql = new StringBuilder("from ")
                .append(targetClass.getName())
                .append(" e");

        buildWhereClause(hql, criteria);

        hql.append(" order by e.id ")
                .append(asc ? "asc" : "desc");

        Query<T> query = getCurrentSession()
                .createQuery(hql.toString(), targetClass);

        bindParameters(query, criteria);

        return query.getResultList();
    }

@Override
public List<T> getPage(int page, int pageSize) {

    String hql = "from " + targetClass.getName() + " e order by e.id";

    return getCurrentSession()
            .createQuery(hql, targetClass)
            .setFirstResult((page - 1) * pageSize)
            .setMaxResults(pageSize)
            .getResultList();
}

    @Override
    public int getCount() {

        String hql = "select count(e) from "
                + targetClass.getName()
                + " e";

        Long count = getCurrentSession()
                .createQuery(hql, Long.class)
                .getSingleResult();

        return count.intValue();
    }

    @Override
    public int getCountMatching(Map<String, Object> criteria, boolean asc) {

        StringBuilder hql = new StringBuilder("select count(e) from ")
                .append(targetClass.getName())
                .append(" e");

        buildWhereClause(hql, criteria);

        Query<Long> query = getCurrentSession()
                .createQuery(hql.toString(), Long.class);

        bindParameters(query, criteria);

        Long count = query.getSingleResult();

        return count.intValue();
    }

    private void buildWhereClause(StringBuilder hql, Map<String, Object> criteria) {

        if (criteria != null && !criteria.isEmpty()) {

            hql.append(" where ");

            int i = 0;

            for (String key : criteria.keySet()) {

                hql.append("e.")
                        .append(key)
                        .append(" = :")
                        .append(key);

                if (++i < criteria.size()) {
                    hql.append(" and ");
                }
            }
        }
    }

    private void bindParameters(Query<?> query, Map<String, Object> criteria) {

        if (criteria == null) {
            return;
        }

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
}