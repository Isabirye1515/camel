package com.camel.person.dao;

import com.camel.person.valueholders.Person;

public interface PersonDao {
    Person getPerson(int id);
    Person insertPerson(Person person);
    Person updatePerson(Person person);
    void deletePerson(int id);
    
    
}
