package com.carlease.customer.model.request;

import com.carlease.customer.config.CustomerValidationMessageConfig;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestModel {
  @NotBlank(message = CustomerValidationMessageConfig.USER_NAME_NOT_NULL)
  private String name;

  @NotBlank(message = CustomerValidationMessageConfig.EMAIL_NOT_NULL)
  private String email;

  @NotBlank(message = CustomerValidationMessageConfig.MOBILE_NOT_NULL)
  private String mobileNumber;

  @NotBlank(message = CustomerValidationMessageConfig.PASSWORD_NOT_NULL)
  private String pwd;

  @NotBlank(message = CustomerValidationMessageConfig.ROLE_NOT_NULL)
  private String role;
}
