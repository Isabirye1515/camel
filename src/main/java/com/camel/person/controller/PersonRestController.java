package com.camel.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camel.config.SessionManagement;
import com.camel.person.service.PersonService;
import com.camel.person.valueholders.Person;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/rest")
public class PersonRestController {

    @Autowired
    private SessionManagement sessionManagement;

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/person")
    public ResponseEntity<Person> getPersonById(@RequestParam(required = true) int id,HttpServletRequest request) {
        sessionManagement.handleSession(request);
        sessionManagement.put(request, "last.person.id", id);

    System.out.println("Session ID: " + request.getSession().getId());
    System.out.println("Is New: " + request.getSession().isNew());

        return ResponseEntity.ok(personService.getPerson(id));
    }

}
