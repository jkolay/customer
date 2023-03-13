package com.carlease.customer.controller;

import static org.mockito.Mockito.when;

import com.carlease.customer.exception.CustomerDuplicationException;
import com.carlease.customer.exception.CustomerException;
import com.carlease.customer.exception.CustomerNotFoundException;
import com.carlease.customer.model.request.CustomerRequest;
import com.carlease.customer.model.request.CustomerStatusUpdateRequest;
import com.carlease.customer.model.response.CustomerResponse;
import com.carlease.customer.service.CustomerService;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
  @Mock private CustomerService customerService;

  @InjectMocks private CustomerController controller;

  @Test
  public void createCustomerTest() throws CustomerException, CustomerDuplicationException {
    when(customerService.createCustomer(Mockito.any(CustomerRequest.class)))
        .thenReturn(new CustomerResponse());
    Assertions.assertNotNull(controller.createCustomer(new CustomerRequest()));
  }

  @Test
  public void getCustomerTest() throws CustomerException, CustomerNotFoundException {
    when(customerService.getCustomerByCustomerId(Mockito.anyInt()))
        .thenReturn(new CustomerResponse());
    Assertions.assertNotNull(controller.getCustomer(1));
  }

  @Test
  public void geAllCustomerTest() {
    when(customerService.getAllCustomerDetails(Mockito.anyInt(), Mockito.anyInt()))
        .thenReturn(new ArrayList<>());
    Assertions.assertNotNull(controller.geAllCustomer(1, 1));
  }

  @Test
  public void updateCustomerTest() throws CustomerNotFoundException, CustomerException {
    Mockito.when(
            customerService.updateCustomer(Mockito.anyInt(), Mockito.any(CustomerRequest.class)))
        .thenReturn(new CustomerResponse());
    Assertions.assertNotNull(controller.updateCustomer(new CustomerRequest(), 1));
  }

}
