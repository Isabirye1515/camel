package com.camel.admin.dao;

import com.camel.admin.valueholders.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Admin getAdmin(int id) {
        return entityManager.find(Admin.class, id);
    }

    @Override
    public Admin insertAdmin(Admin admin) {
        entityManager.persist(admin);
        return admin;
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return entityManager.merge(admin);
    }

    @Override
    public void deleteAdmin(int id) {
        Admin admin = entityManager.find(Admin.class, id);
        if (admin != null) {
            entityManager.remove(admin);
        }
    }

   @Override
public Admin getByUserName(String userName) {

    String jpql = "SELECT a FROM Admin a WHERE a.userName = :userName";

    TypedQuery<Admin> query = entityManager.createQuery(jpql, Admin.class);

    query.setParameter("userName", userName);

    try {
        return query.getSingleResult();
    } catch (NoResultException e) {
        throw new RuntimeException("The username '" + userName + "' does not exist.");
    }
}
}