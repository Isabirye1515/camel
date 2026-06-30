package com.camel.audit.dao;

import java.util.List;

import com.camel.audit.valueholder.AuditTrail;
import com.camel.common.object.BaseDao;

public interface AuditTrailDao extends BaseDao<AuditTrail,Integer> {

        List<AuditTrail> getAllAudits();
    void insertAudit(AuditTrail audit);
}