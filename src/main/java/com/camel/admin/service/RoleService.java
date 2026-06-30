package com.camel.admin.service;

import com.camel.admin.valueholders.Role;

public interface RoleService {
    Role getRole(int id);
    Role insertRole(Role role);
    Role updateRole(Role role);
    void deleteRole(int id);
}
