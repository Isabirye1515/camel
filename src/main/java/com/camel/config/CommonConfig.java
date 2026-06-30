package com.camel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "commonConfig")
public class CommonConfig {
    @Value("${camel.zero.auth.key}")
    private String zeroAuth;
    @Value("${camel.default.page.size}")
    private int pageSize;
    @Value("${camel.email.password}")
    private String emailPassword;
    @Value("${camel.email.username}")
    private String emailUser;
    @Value("${camel.email.host}")
    private String emailHost;
    @Value("${camel.email.host.port}")
    private int emailPort;

    public String getZeroAuth() {
        return zeroAuth;
    }

    public void setZeroAuth(String zeroAuth) {
        this.zeroAuth = zeroAuth;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public int getEmailPort() {
        return emailPort;
    }

    public void setEmailPort(int emailPort) {
        this.emailPort = emailPort;
    }

    

    

    
    
}
