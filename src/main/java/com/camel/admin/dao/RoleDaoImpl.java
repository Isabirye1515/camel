package com.camel.admin.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.camel.admin.valueholders.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Component
@Transactional
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRole(int id) {
       return entityManager.find(Role.class, id);
    }

    @Override
    public Role insertRole(Role role) {
    entityManager.persist(role);
    return role;
    }

    @Override
    public Role updateRole(Role role) {
    entityManager.merge(role);
    return role;
    }

    @Override
    public void deleteRole(int id) {
       Role role = entityManager.find(Role.class, id);
       if(role !=null){
        entityManager.remove(role);
       }
    }

    
}
