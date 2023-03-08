package com.carlease.customer.service.impl;

import com.carlease.customer.config.CustomerStatus;
import com.carlease.customer.exception.CustomerDuplicationException;
import com.carlease.customer.exception.CustomerException;
import com.carlease.customer.exception.CustomerNotFoundException;
import com.carlease.customer.mapper.CustomerMapper;
import com.carlease.customer.model.request.CustomerRequest;
import com.carlease.customer.model.request.CustomerStatusUpdateRequest;
import com.carlease.customer.model.response.CustomerResponse;
import com.carlease.customer.persistence.CustomerDao;
import com.carlease.customer.repository.CustomerRepository;
import com.carlease.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Customer service class method implementations
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    /**
     * this is the implementation to create a customer in the system
     * @param customerRequest the customer model object which consists of customer details
     * @return the customer response object after customer creation in app
     * @throws CustomerDuplicationException  the customer duplication happens if someone enters same customer email id
     */
    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) throws CustomerDuplicationException {
        CustomerDao existingCustomerDao = customerRepository.findByEmailAddress(customerRequest.getEmailAddress());
        logger.info("New customer details getting added in the app");
        if (existingCustomerDao != null) {
            logger.error("Customer details are duplicate");
            throw new CustomerDuplicationException("Customer email is already present in the app");
        }
        CustomerDao customerDao = customerMapper.mapCustomerRequestToCustomerDao(customerRequest);
        customerDao.setStatus(CustomerStatus.NEW.getValue());
        return customerMapper.mapCustomerDaoToCustomerResponse(customerRepository.save(customerDao));
    }
    /**
     * this is the implementation to retrieve customer based on a customer id
     * @param customerId the customer id of the customer
     * @return the customer response
     * @throws CustomerNotFoundException this exception gets thrown when customer is not present
     */
    @Override
    public CustomerResponse getCustomerByCustomerId(Integer customerId) throws CustomerNotFoundException {
        CustomerDao customerDao = customerRepository.findByCustomerId(customerId);
        if(customerDao!=null) {
            logger.info("Customer details are found");
            return customerMapper.mapCustomerDaoToCustomerResponse(customerDao);
        }
        logger.error("Customer is not available");
        throw new CustomerNotFoundException("Customer is not found");
    }
    /**
     * this is the  implementation to retrieve all customers
     * @param page the page number which needs to be fetched
     * @param size the number of records which would be fetched per page
     * @return the list of customer response
     */
    @Override
    public List<CustomerResponse> getAllCustomerDetails(int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size);
        logger.info("All customer details are getting fetched");
        return customerRepository.findAll(pageRequest).map(customerDao -> customerMapper.mapCustomerDaoToCustomerResponse(customerDao)).getContent();
    }
    /**
     *  this is the implementation to update a customer details
     * @param customerRequest the customer details
     * @param customerId the customer id
     * @return the customer response
     * @throws CustomerNotFoundException this gets thrown when the customer not present
     */
    @Override
    public CustomerResponse updateCustomer(Integer customerId, CustomerRequest customerRequest) throws CustomerNotFoundException {
        CustomerDao existingCustomerDao = customerRepository.findByCustomerId(customerId);
        logger.info("Customer information is getting updated.");
        if (existingCustomerDao != null) {
            CustomerDao customerDao = customerMapper.mapCustomerRequestToCustomerDao(customerRequest);
            customerDao.setCustomerId(existingCustomerDao.getCustomerId());
            customerDao.setStatus(existingCustomerDao.getStatus());
            customerDao.setCreatedAt(existingCustomerDao.getCreatedAt());
            return customerMapper.mapCustomerDaoToCustomerResponse(customerRepository.save(customerDao));
        }
        logger.error("Customer details is not available");
        throw new CustomerNotFoundException("Customer Not Found");
    }
    /**
     *  this is the endpoint implementation to update customer status
     * @param customerId the customer id
     * @param customerRequest the new status
     * @return the updated customer object
     * @throws CustomerException this gets thrown if the status value is not correct
     * @throws CustomerNotFoundException this gets thrown when customer is not found
     */
    @Override
    public CustomerResponse updateCustomerStatus(Integer customerId, CustomerStatusUpdateRequest customerRequest) throws CustomerNotFoundException, CustomerException {
        CustomerDao existingCustomerDao = Optional.of(customerRepository.findByCustomerId(customerId)).orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
        if (customerRequest.getStatus().equalsIgnoreCase(CustomerStatus.LEASED.getValue()) || customerRequest.getStatus().equalsIgnoreCase(CustomerStatus.NEW.getValue())) {
            existingCustomerDao.setStatus(customerRequest.getStatus());
            existingCustomerDao.setUpdatedAt(LocalDateTime.now());
            return customerMapper.mapCustomerDaoToCustomerResponse(customerRepository.save(existingCustomerDao));
        }
        throw new CustomerException("Customer status can not be updated-status can be either NEW or Leased");

    }
    /**
     *  this is the implementation  to delete a customer
     * @param customerId the customer id
     * @throws CustomerException the exception gets thrown when customer has leased a car
     * @throws CustomerNotFoundException gets thrown when customer is not fond in the system
     */
    @Override
    public void deleteCustomer(Integer customerId) throws CustomerNotFoundException, CustomerException {
        CustomerDao existingCustomerDao = Optional.of(customerRepository.findByCustomerId(customerId)).orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
        logger.info("Customer info is getting deleted");
        if (existingCustomerDao.getStatus().equalsIgnoreCase(CustomerStatus.NEW.getValue())) {
            customerRepository.delete(existingCustomerDao);
        } else {
            logger.info("Customer can not be deleted");
            throw new CustomerException("Customer can not be deleted as customer has leased a car");
        }


    }
}
