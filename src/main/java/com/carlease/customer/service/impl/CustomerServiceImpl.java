package com.carlease.customer.service.impl;

import com.carlease.customer.exception.CustomerDuplicationException;
import com.carlease.customer.exception.CustomerNotFoundException;
import com.carlease.customer.mapper.CustomerMapper;
import com.carlease.customer.model.request.CustomerRequest;
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

import java.util.List;

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


    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) throws CustomerDuplicationException {
        CustomerDao existingCustomerDao = customerRepository.findByEmailAddress(customerRequest.getEmailAddress());

        if (existingCustomerDao != null) {
            throw new CustomerDuplicationException("Customer email is already present in the app");
        }
        CustomerDao customerDao = customerMapper.mapCustomerRequestToCustomerDao(customerRequest);
        return customerMapper.mapCustomerDaoToCustomerResponse(customerRepository.save(customerDao));
    }

    @Override
    public CustomerResponse getCustomerByCustomerId(Integer customerId) throws CustomerNotFoundException {
        CustomerDao customerDao = customerRepository.getReferenceById(customerId);
        if(customerDao!=null) {
            return customerMapper.mapCustomerDaoToCustomerResponse(customerDao);
        }
        throw new CustomerNotFoundException("Customer is not found");
    }

    @Override
    public List<CustomerResponse> getAllCustomerDetails(int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size);
        return customerRepository.findAll(pageRequest).map(customerDao -> customerMapper.mapCustomerDaoToCustomerResponse(customerDao)).getContent();
    }

    @Override
    public CustomerResponse updateCustomer(Integer customerId, CustomerRequest customerRequest) throws CustomerNotFoundException {
        CustomerDao existingCustomerDao = customerRepository.getReferenceById(customerId);
        if (existingCustomerDao != null) {
            CustomerDao customerDao = customerMapper.mapCustomerRequestToCustomerDao(customerRequest);
            customerDao.setCustomerId(existingCustomerDao.getCustomerId());
            customerDao.setCreatedAt(existingCustomerDao.getCreatedAt());
            return customerMapper.mapCustomerDaoToCustomerResponse(customerRepository.save(existingCustomerDao));
        }
        throw new CustomerNotFoundException("Customer Not Found");
    }

    @Override
    public void deleteCustomer(Integer customerId) throws CustomerNotFoundException {
        CustomerDao existingCustomerDao = customerRepository.findByCustomerId(customerId);
        if (existingCustomerDao != null) {
           customerRepository.delete(existingCustomerDao);
        }
        else{
            throw new CustomerNotFoundException("Customer Not Found");
        }

    }
}
