package com.carlease.customer.service;

import com.carlease.customer.exception.CustomerDuplicationException;
import com.carlease.customer.exception.CustomerException;
import com.carlease.customer.exception.CustomerNotFoundException;
import com.carlease.customer.model.request.CustomerRequest;
import com.carlease.customer.model.request.CustomerStatusUpdateRequest;
import com.carlease.customer.model.response.CustomerResponse;
import java.util.List;

/** The service interface for customer service */
public interface CustomerService {

  CustomerResponse createCustomer(CustomerRequest customerRequest)
      throws CustomerDuplicationException;

  CustomerResponse getCustomerByCustomerId(Integer customerId) throws CustomerNotFoundException;

  List<CustomerResponse> getAllCustomerDetails(int page, int size);

  CustomerResponse updateCustomer(Integer customerId, CustomerRequest customerRequest)
      throws CustomerNotFoundException;

  CustomerResponse updateCustomerStatus(
      Integer customerId, CustomerStatusUpdateRequest customerRequest)
      throws CustomerNotFoundException, CustomerException;

  void deleteCustomer(Integer customerId) throws CustomerNotFoundException, CustomerException;
}
