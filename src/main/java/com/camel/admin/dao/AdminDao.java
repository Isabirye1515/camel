package com.camel.admin.dao;

import com.camel.admin.valueholders.Admin;

public interface AdminDao {
    Admin getAdmin(int id);
    Admin insertAdmin(Admin admin);
    Admin updateAdmin(Admin admin);
    void deleteAdmin(int id);
    Admin getByUserName(String userName);
}
