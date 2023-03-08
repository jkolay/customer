package com.carlease.customer.model.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Customer response model class */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

  private Integer customerId;

  private String name;

  private String street;

  private String houseNumber;

  private String zipcode;

  private String place;

  private String emailAddress;

  private String phoneNumber;

  private String status;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
