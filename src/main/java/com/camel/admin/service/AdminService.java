package com.camel.admin.service;

import com.camel.admin.valueholders.Admin;

public interface AdminService {
        Admin getAdmin(int id);
    Admin insertAdmin(Admin admin);
    Admin updateAdmin(Admin admin);
    void deleteAdmin(int id);
    Admin getAdminByUsernameAndPassword(String userName,String password) throws Exception;
}
