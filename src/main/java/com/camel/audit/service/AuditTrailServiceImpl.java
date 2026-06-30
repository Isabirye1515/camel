package com.camel.audit.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camel.admin.service.AdminService;
import com.camel.admin.valueholders.Admin;
import com.camel.audit.dao.AuditTrailDao;
import com.camel.audit.valueholder.AuditTrail;
import com.camel.common.AuditPublisher;
import com.camel.common.object.BaseServiceImpl;
@Service(value = "auditTrailService")
@Transactional
public class AuditTrailServiceImpl extends BaseServiceImpl<AuditTrail,Integer> implements AuditTrailService {

    @Autowired
    private AuditTrailDao auditTrailDao;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuditPublisher auditPublisher;


    @Override
    @Transactional
    public List<AuditTrail> getAllAudits() {
        return auditTrailDao.getAllAudits();
   
    }

    @Override
    @Transactional
    public void insertAudit(AuditTrail audit) {
        auditTrailDao.insertAudit(audit);
     
    }
    @Override
    @Transactional
    public void handleSaveAudit(String tableName, String action) {

        AuditTrail audit = new AuditTrail();

        audit.setTableName(tableName);
        audit.setActionPerformed(action);
        audit.setCreateDate(new Date(System.currentTimeMillis()));
        audit.setUpdateDate(new Date(System.currentTimeMillis()));
        audit.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        Admin admin = adminService.getAdmin(1);
        audit.setAdmin(admin);

        auditTrailDao.insertAudit(audit);
    }
    @Override
    public void publishAudits(){
        List<AuditTrail> trails = getAllAudits();
for (AuditTrail trail : trails) {
    auditPublisher.sendAudit(trail);
}
    }
    
}
