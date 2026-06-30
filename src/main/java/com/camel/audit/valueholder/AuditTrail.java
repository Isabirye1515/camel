package com.camel.audit.valueholder;

import java.sql.Date;
import java.sql.Timestamp;

import com.camel.admin.valueholders.Admin;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "audit_trail")
public class AuditTrail {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 10)
    private int id; 
    @Column(name = "created_on")
    private Date createDate;
    @Column(name = "updated_on")
    private Date updateDate;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @Column(name = "table_name")
    private String tableName;
    @Column(name = "action", length = 250)
    private String actionPerformed;
    @Column(name = "lastupdated")
    private Timestamp lastUpdated;

    public AuditTrail(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getActionPerformed() {
        return actionPerformed;
    }

    public void setActionPerformed(String actionPerformed) {
        this.actionPerformed = actionPerformed;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "AuditTrail [id=" + id + ", createDate=" + createDate + ", updateDate=" + updateDate + ", admin=" + admin
                + ", tableName=" + tableName + ", actionPerformed=" + actionPerformed + ", lastUpdated=" + lastUpdated
                + "]";
    }

    

    
    
}
