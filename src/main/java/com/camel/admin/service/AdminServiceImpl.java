package com.camel.admin.service;

import com.camel.admin.dao.AdminDao;
import com.camel.admin.valueholders.Admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin getAdmin(int id) {
        return adminDao.getAdmin(id);
    }

    @Override
    public Admin insertAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminDao.insertAdmin(admin);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminDao.updateAdmin(admin);
    }

    @Override
    public void deleteAdmin(int id) {
        adminDao.deleteAdmin(id);
    }

@Override
public Admin getAdminByUsernameAndPassword(String userName, String password) throws Exception {

    Admin admin = adminDao.getByUserName(userName);

    if (admin == null) {
        throw new Exception("Username does not exist.");
    }

    boolean isMatch = passwordEncoder.matches(password, admin.getPassword());

    if (!isMatch) {
        throw new Exception("Invalid password.");
    }

    return admin;
}
}