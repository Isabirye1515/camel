package com.camel.common;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.camel.audit.valueholder.AuditTrail;

@Component(value = "consumer")
public class AuditConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Async
    public Queue<AuditTrail> receiveAll() {

        Queue<AuditTrail> audits = new LinkedList<>();

        while (true) {

            AuditTrail audit =
                    (AuditTrail) rabbitTemplate.receiveAndConvert("audit.queue");

            if (audit == null) {
                break; // Queue is empty
            }

            audits.add(audit);
        }

        System.out.println("Received " + audits.size() + " audit messages");

        audits.forEach(audit -> {
            System.out.println("------------------------------------");
            System.out.println("Id              : " + audit.getId());
            System.out.println("Table           : " + audit.getTableName());
            System.out.println("Action          : " + audit.getActionPerformed());
            System.out.println("Created         : " + audit.getCreateDate());
            System.out.println("Updated         : " + audit.getUpdateDate());
            System.out.println("Last Updated    : " + audit.getLastUpdated());

            if (audit.getAdmin() != null) {
                System.out.println("Admin           : " + audit.getAdmin().getName());
                System.out.println("Username        : " + audit.getAdmin().getUserName());
            }

            System.out.println("------------------------------------");
        });

        return audits;
    }
}