package com.carlease.customer.service.impl;

import com.carlease.customer.exception.CustomerException;import com.carlease.customer.mapper.UserMapper;import com.carlease.customer.model.request.UserRequestModel;
import com.carlease.customer.persistence.Authority;import com.carlease.customer.persistence.RegisterUser;import com.carlease.customer.repository.AuthorityRepository;import com.carlease.customer.repository.LoginRepository;
import com.carlease.customer.service.UserManagementService;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;import java.sql.Date;import java.util.List;

@Service
public class UserManagementServiceImpl implements UserManagementService {
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthorityRepository authorityRepository;
  @Override
  public ResponseEntity<String> register(UserRequestModel userRequestModel) {
      RegisterUser savedCustomer = null;
      ResponseEntity response = null;
      try {
          RegisterUser user= userMapper.mapUserRequestModelToCustomer(userRequestModel);
          if (!userRequestModel.getRole().equalsIgnoreCase("BROKER")
                  && userRequestModel.getRole().equalsIgnoreCase("COMPANY")) {
              throw new CustomerException("User role needs to be either Broker or Company");
          }
          String hashPwd = passwordEncoder.encode(user.getPwd());
          user.setPwd(hashPwd);
          user.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
          savedCustomer = loginRepository.save(user);

          String authorityName = "ROLE_" + user.getRole().toUpperCase();
          Authority authority = new Authority();
          authority.setName(authorityName);
          authority.setCustomer(savedCustomer);
          authorityRepository.save(authority);
          if (savedCustomer.getId() > 0) {
              response = ResponseEntity
                      .status(HttpStatus.CREATED)
                      .body("Given user details are successfully registered");
          }
      } catch (Exception ex) {
          response = ResponseEntity
                  .status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body("An exception occured due to " + ex.getMessage());
      }
      return response;

  }

  @Override
  public RegisterUser getUserDetailsAfterLogin(Authentication authentication) {
      List<RegisterUser> customers = loginRepository.findByEmail(authentication.getName());
      if (customers.size() > 0) {
          return customers.get(0);
      } else {
          return null;
      }
  }
}
