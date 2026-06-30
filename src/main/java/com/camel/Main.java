package com.camel;

import java.sql.Timestamp;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.camel.admin.service.AdminService;
import com.camel.admin.service.RoleService;
import com.camel.admin.valueholders.Admin;
import com.camel.admin.valueholders.Role;
import com.camel.audit.service.AuditTrailService;
import com.camel.common.AuditConsumer;
import com.camel.common.MessagingService;
import com.camel.config.CommonConfig;
import com.camel.person.service.PersonService;
import com.camel.person.valueholders.Person;

public class Main {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        try {

            Person person = new Person();
            person.setFirstName("Kamya");
            person.setLastName("Eric");
            person.setGender(Person.Gender.MALE);
            person.setMiddleName("junior");
            person.setLastUpdated(new Timestamp(System.currentTimeMillis()));

            CommonConfig config = (CommonConfig)context.getBean("commonConfig");


           PersonService personService = (PersonService) context.getBean("personService");
           AuditTrailService auditTrailService = (AuditTrailService)context.getBean("auditTrailService");
           // Person savedPerson = personService.insertPerson(person);

           AdminService adminService = (AdminService)context.getBean("adminService");
           AuditConsumer consumer = (AuditConsumer) context.getBean("consumer");
           RoleService roleService = (RoleService)context.getBean("roleService");
          
        
           System.out.println(config.getZeroAuth());
           MessagingService service = (MessagingService)context.getBean("messagingService");
           service.sendMessage("isabiryeelijah10@gmail.com", "What a fuck", "You suck you ass hole");
         
            


        } finally {
            context.close();
        }
    }
}