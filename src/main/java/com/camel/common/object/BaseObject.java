package com.camel.common.object;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseObject {
    @Column(name = "admin_id", nullable = false, length=10)
    private int sysuserId;
    @Column(name = "lastupdated", nullable = false)
    private Timestamp lastUpdated;
    public int getSysuserId() {
        return sysuserId;
    }
    public void setSysuserId(int sysuserId) {
        this.sysuserId = sysuserId;
    }
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    
    
}
