package com.carlease.customer.controller;

import com.carlease.customer.model.request.UserRequestModel;
import com.carlease.customer.persistence.RegisterUser;
import com.carlease.customer.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

   @Autowired
   UserManagementService userManagementService;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestModel userRequestModel) {
        return userManagementService.register(userRequestModel);
       }

    @RequestMapping("/user")
    public RegisterUser getUserDetailsAfterLogin(Authentication authentication) {
       return userManagementService.getUserDetailsAfterLogin(authentication);

    }

}
