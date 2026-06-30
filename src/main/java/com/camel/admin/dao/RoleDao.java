package com.camel.admin.dao;

import com.camel.admin.valueholders.Role;

public interface RoleDao {
    Role getRole(int id);
    Role insertRole(Role role);
    Role updateRole(Role role);
    void deleteRole(int id);
}
