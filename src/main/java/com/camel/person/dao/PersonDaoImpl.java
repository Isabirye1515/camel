package com.camel.person.dao;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.camel.person.valueholders.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Component
@Transactional
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Person getPerson(int id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public Person insertPerson(Person person) {
        entityManager.persist(person);
        return person;
    }

    @Override
    public Person updatePerson(Person person) {
        return entityManager.merge(person);
    }

    @Override
    public void deletePerson(int id) {
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            entityManager.remove(person);
        }
    }
}