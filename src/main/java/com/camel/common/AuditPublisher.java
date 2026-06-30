package com.camel.common;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camel.audit.valueholder.AuditTrail;

@Service
public class AuditPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendAudit(AuditTrail auditTrail) {

        rabbitTemplate.convertAndSend(
                "amq.direct",
                "audit.key",
                auditTrail
        );
    }
}