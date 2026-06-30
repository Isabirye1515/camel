package com.camel.person.service;

import com.camel.person.valueholders.Person;

public interface PersonService {
    Person getPerson(int id);
    Person insertPerson(Person person);
    Person updatePerson(Person person);
    void deletePerson(int id);
}
