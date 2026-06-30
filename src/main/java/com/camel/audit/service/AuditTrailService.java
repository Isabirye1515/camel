package com.camel.audit.service;

import java.util.List;

import com.camel.audit.valueholder.AuditTrail;
import com.camel.common.object.BaseService;

public interface AuditTrailService extends BaseService<AuditTrail, Integer> {
    List<AuditTrail> getAllAudits();
    void insertAudit(AuditTrail audit);
    void handleSaveAudit(String tableName, String action);
    void publishAudits();
    
}
