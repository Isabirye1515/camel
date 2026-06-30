package com.camel.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.camel.admin.dao.RoleDao;
import com.camel.admin.valueholders.Role;

@Service(value = "roleService")
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getRole(int id) {
       return roleDao.getRole(id);
    }

    @Override
    public Role insertRole(Role role) {
       return roleDao.insertRole(role);
    }

    @Override
    public Role updateRole(Role role) {
       return roleDao.updateRole(role);
    }

    @Override
    public void deleteRole(int id) {
      roleDao.deleteRole(id);
    }
    
}
