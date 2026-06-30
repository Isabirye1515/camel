package com.camel.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camel.audit.service.AuditTrailService;
import com.camel.common.CamelConstants;


@Aspect
@Component
public class AuditAspect {

    @Autowired
    private AuditTrailService auditTrailService;

    @AfterReturning("execution(* com.camel.*.service.*.*(..))")
    public void audit(JoinPoint joinPoint) {

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        System.out.println(className+" "+methodName);
    }
@AfterReturning("execution(* com.camel.person.service..*(..))")
public void handlePersonAudit(JoinPoint joinPoint) {

    String method = joinPoint.getSignature().getName();
    auditTrailService.handleSaveAudit(CamelConstants.PERSON_TABLE, method);
   
}
@AfterReturning("execution(* com.camel.admin.service.RoleService.*(..))")
public void handlerRoleAudit(JoinPoint joinPoint) {

    String method = joinPoint.getSignature().getName();
    auditTrailService.handleSaveAudit(CamelConstants.ROLE_TABLE, method);
   
}


}