package com.carlease.customer.service;

import com.carlease.customer.model.request.UserRequestModel;
import com.carlease.customer.persistence.RegisterUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface UserManagementService {
  ResponseEntity<String> register(UserRequestModel userRequestModel);

  RegisterUser getUserDetailsAfterLogin(Authentication authentication);
}
