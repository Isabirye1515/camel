package com.camel.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.camel.person.dao.PersonDao;
import com.camel.person.valueholders.Person;
@Service(value ="personService")
@Transactional
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonDao personDao;

    @Override
    public Person getPerson(int id) {
      return personDao.getPerson(id);
    }

    @Override
    public Person insertPerson(Person person) {
       return personDao.insertPerson(person);
    }

    @Override
    public Person updatePerson(Person person) {
        return personDao.updatePerson(person);
    }

    @Override
    public void deletePerson(int id) {
        personDao.deletePerson(id);

    }

    
    
}
