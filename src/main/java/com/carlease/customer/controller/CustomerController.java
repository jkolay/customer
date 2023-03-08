package com.carlease.customer.controller;

import com.carlease.customer.exception.CustomerDuplicationException;
import com.carlease.customer.exception.CustomerException;
import com.carlease.customer.exception.CustomerNotFoundException;
import com.carlease.customer.model.request.CustomerRequest;
import com.carlease.customer.model.request.CustomerStatusUpdateRequest;
import com.carlease.customer.model.response.CustomerResponse;
import com.carlease.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Customer controller which consists of all the endpoints for customer service */
@RestController
@RequestMapping(value = "api/v1/customer")
public class CustomerController {
  private final CustomerService customerService;
  private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  /**
   * this is the endpoint implementation to create a customer in the system
   *
   * @param request the customer model object which consists of customer details
   * @return the customer response object after customer creation in app
   * @throws CustomerDuplicationException the customer duplication happens if someone enters same
   *     customer email id
   */
  @Operation(description = "create a customer")
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<CustomerResponse> createCustomer(
      @Valid @RequestBody CustomerRequest request) throws CustomerDuplicationException {
    logger.info("Creating a new customer");
    return ResponseEntity.ok(customerService.createCustomer(request));
  }

  /**
   * this is the endpoint implementation to retrieve customer based on a customer id
   *
   * @param customerId the customer id of the customer
   * @return the customer response
   * @throws CustomerNotFoundException this exception gets thrown when customer is not present
   */
  @Operation(description = "Retrieve a customer")
  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  @ResponseStatus(HttpStatus.FOUND)
  public ResponseEntity<CustomerResponse> getCustomer(@Valid @PathVariable("id") Integer customerId)
      throws CustomerNotFoundException {
    logger.info("retrieving customer details");
    return ResponseEntity.ok(customerService.getCustomerByCustomerId(customerId));
  }

  /**
   * this is the endpoint implementation to retrieve all customers
   *
   * @param page the page number which needs to be fetched
   * @param size the number of records which would be fetched per page
   * @return the list of customer response
   */
  @Operation(description = "List all customers")
  @RequestMapping(method = RequestMethod.GET, value = "/customerList")
  @ResponseStatus(HttpStatus.FOUND)
  public ResponseEntity<List<CustomerResponse>> geAllCustomer(
      @PathParam("page") Integer page, @PathParam("size") Integer size) {
    logger.info("Retrieving list of customers");
    return ResponseEntity.ok(customerService.getAllCustomerDetails(page, size));
  }

  /**
   * this is the endpoint implementation to update a customer details
   *
   * @param request the customer details
   * @param customerId the customer id
   * @return the customer response
   * @throws CustomerNotFoundException this gets thrown when the customer not present
   */
  @Operation(description = "Update a customer")
  @RequestMapping(method = RequestMethod.PUT, value = "/{customerId}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ResponseEntity<CustomerResponse> updateCustomer(
      @Valid @RequestBody CustomerRequest request, @PathVariable("customerId") Integer customerId)
      throws CustomerNotFoundException {
    logger.info("Updating an existing customer");
    return ResponseEntity.ok(customerService.updateCustomer(customerId, request));
  }

  /**
   * this is the endpoint implementation to delete a customer
   *
   * @param customerId the customer id
   * @throws CustomerException the exception gets thrown when customer has leased a car
   * @throws CustomerNotFoundException gets thrown when customer is not fond in the system
   */
  @Operation(description = "Delete a customer")
  @RequestMapping(method = RequestMethod.DELETE, value = "/{customerId}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("customerId") Integer customerId)
      throws CustomerException, CustomerNotFoundException {
    logger.info("Deleting an existing customer");
    customerService.deleteCustomer(customerId);
  }

  /**
   * this is the endpoint implementation to update customer status
   *
   * @param customerId the customer id
   * @param updateStatusRequestModel the new status
   * @return the updated customer object
   * @throws CustomerException this gets thrown if the status value is not correct
   * @throws CustomerNotFoundException this gets thrown when customer is not found
   */
  @Operation(description = "Update a customer status by customerId")
  @RequestMapping(method = RequestMethod.PUT, value = "/modify/status/{customerId}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CustomerResponse> updateCustomerStatus(
      @PathVariable("customerId") Integer customerId,
      @Valid @RequestBody CustomerStatusUpdateRequest updateStatusRequestModel)
      throws CustomerException, CustomerNotFoundException {
    logger.info("Modifying an existing customer");
    return ResponseEntity.ok(
        customerService.updateCustomerStatus(customerId, updateStatusRequestModel));
  }
}
