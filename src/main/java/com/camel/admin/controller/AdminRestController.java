package com.camel.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camel.admin.service.AdminService;
import com.camel.admin.util.AdminForm;
import com.camel.admin.valueholders.Admin;
import com.camel.common.ResponseError;



@RestController
@RequestMapping(value = "/rest")
public class AdminRestController {
    @Autowired
    private AdminService adminService;


    @GetMapping(value = "/admin")
    public ResponseEntity<AdminForm> getAdminById(@RequestParam(required = true) int id){
    AdminForm form = new AdminForm();
    Admin admin = adminService.getAdmin(id);
    if(admin == null || admin.getRole() == null){
      return ResponseEntity.internalServerError().build();
    }
    form.setId(admin.getId());
   form.setAdminName(admin.getName());
   form.setUserName(admin.getUserName());
   form.setDescription(admin.getRole().getDescription());
   form.setTitle(admin.getRole().getTitle());

   return ResponseEntity.ok(form);

    }

    @PostMapping(value = "/admin/credentials")
    public ResponseEntity<?> getAdminByLogin(@RequestBody LoginRequest loginRequest) throws Exception {
        try{
        AdminForm form = new AdminForm();
        Admin admin = adminService.getAdminByUsernameAndPassword(
            loginRequest.getUserName(), 
            loginRequest.getPassword()
        );
    
        
        form.setId(admin.getId());
        form.setAdminName(admin.getName());
        form.setUserName(admin.getUserName());
        form.setDescription(admin.getRole().getDescription());
        form.setTitle(admin.getRole().getTitle());

        return ResponseEntity.ok(form);
    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseError("Incorrect username or password!", HttpStatus.UNAUTHORIZED.value()));

    }
    }
}

// Create a DTO for the login request
class LoginRequest {
    private String userName;
    private String password;
    
    // Getters and setters
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
