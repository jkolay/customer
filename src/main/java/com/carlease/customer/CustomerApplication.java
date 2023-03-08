package com.carlease.customer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** The Main application class for customer */
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(title = "Customer API", version = "1.0", description = "Customer Information"))
public class CustomerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerApplication.class, args);
  }
}
