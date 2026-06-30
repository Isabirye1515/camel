package com.camel.audit.dao;

import java.util.List;

import com.camel.audit.valueholder.AuditTrail;
import com.camel.common.object.BaseDaoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AuditTrailDaoImpl extends BaseDaoImpl<AuditTrail,Integer> implements AuditTrailDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AuditTrail> getAllAudits() {

        String jpql = "SELECT a FROM AuditTrail a";

        TypedQuery<AuditTrail> query =
                entityManager.createQuery(jpql, AuditTrail.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void insertAudit(AuditTrail audit) {
        entityManager.persist(audit);
    }
}