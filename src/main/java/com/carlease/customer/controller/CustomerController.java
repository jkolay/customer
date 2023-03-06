package com.carlease.customer.controller;

import com.carlease.customer.exception.CustomerDuplicationException;
import com.carlease.customer.exception.CustomerException;
import com.carlease.customer.exception.CustomerNotFoundException;
import com.carlease.customer.model.request.CustomerRequest;
import com.carlease.customer.model.response.CustomerResponse;
import com.carlease.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(description = "create a customer")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@Valid @RequestBody CustomerRequest request) throws CustomerException {
        try {
            logger.info("Creating a new customer");
            return customerService.createCustomer(request);
        } catch (CustomerDuplicationException e) {
            logger.error(e.getMessage());
            throw new CustomerException(e);
        }
    }

    @Operation(description = "Retrieve a customer")
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public CustomerResponse getCustomer(@Valid @PathVariable("id") Integer customerId) throws CustomerException {
        try {
            logger.info("retrieving customer details");
            return customerService.getCustomerByCustomerId(customerId);
        } catch (CustomerNotFoundException e) {
            logger.error(e.getMessage());
            throw new CustomerException(e);
        }
    }

    @Operation(description = "List all customers")
    @RequestMapping(method = RequestMethod.GET,value = "/customerList")
    @ResponseStatus(HttpStatus.FOUND)
    public List<CustomerResponse> geAllCustomer(@PathParam("page") Integer page, @PathParam("size") Integer size) {

            logger.info("Retrieving list of customers");
            return customerService.getAllCustomerDetails(page,size);

    }

    @Operation(description = "Update a customer")
    @RequestMapping(method = RequestMethod.PUT,value = "/{customerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CustomerResponse updateCustomer(@Valid @RequestBody CustomerRequest request,@PathVariable("customerId") Integer customerId) throws CustomerException {
        try {
            logger.info("Updating an existing customer");
            return customerService.updateCustomer(customerId,request);
        } catch (CustomerNotFoundException e) {
            throw new CustomerException(e.getMessage());
        }
    }

    @Operation(description = "Delete a customer")
    @RequestMapping(method = RequestMethod.DELETE,value = "/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("customerId") Integer customerId) throws CustomerException {
        try {
            logger.info("Deleting an existing customer");
             customerService.deleteCustomer(customerId);
        } catch (CustomerNotFoundException e) {
            throw new CustomerException(e.getMessage());
        }
    }


}
