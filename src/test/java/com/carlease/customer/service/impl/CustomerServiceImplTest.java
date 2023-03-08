package com.carlease.customer.service.impl;

import static org.mockito.Mockito.when;

import com.carlease.customer.config.CustomerStatus;
import com.carlease.customer.exception.CustomerDuplicationException;
import com.carlease.customer.exception.CustomerException;
import com.carlease.customer.exception.CustomerNotFoundException;
import com.carlease.customer.mapper.CustomerMapper;
import com.carlease.customer.model.request.CustomerRequest;
import com.carlease.customer.model.response.CustomerResponse;
import com.carlease.customer.persistence.CustomerDao;
import com.carlease.customer.repository.CustomerRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
  @Mock CustomerRepository customerRepository;

  @Mock CustomerMapper customerMapper;

  @InjectMocks CustomerServiceImpl customerService;

  @Test
  public void createCustomerTest() throws CustomerDuplicationException {
    CustomerDao customerDao = Mockito.mock(CustomerDao.class);
    CustomerResponse customer = Mockito.mock(CustomerResponse.class);
    when(customerRepository.findByEmailAddress(Mockito.anyString())).thenReturn(null);
    when(customerMapper.mapCustomerRequestToCustomerDao(Mockito.any(CustomerRequest.class)))
        .thenReturn(customerDao);
    when(customerRepository.save(Mockito.any(CustomerDao.class))).thenReturn(customerDao);
    when(customerMapper.mapCustomerDaoToCustomerResponse(Mockito.any(CustomerDao.class)))
        .thenReturn(customer);
    CustomerRequest customerRequest =
        new CustomerRequest(
            "J Kolay", "test", "12", "1213", "Amstelveen", "jk@gmail.com", "1234567890");
    Assertions.assertNotNull(customerService.createCustomer(customerRequest));
  }

  @Test
  public void getCustomerByCustomerIdTest() throws CustomerNotFoundException {
    CustomerDao customerDao = Mockito.mock(CustomerDao.class);
    CustomerResponse customer = Mockito.mock(CustomerResponse.class);
    when(customerRepository.findByCustomerId(Mockito.anyInt())).thenReturn(customerDao);
    when(customerMapper.mapCustomerDaoToCustomerResponse(Mockito.any(CustomerDao.class)))
        .thenReturn(customer);
    Assertions.assertNotNull(customerService.getCustomerByCustomerId(1));
  }

  @Test
  public void updateCustomerTest() throws CustomerNotFoundException {
    CustomerDao customerDao = Mockito.mock(CustomerDao.class);
    CustomerDao existingCustomerDao = Mockito.mock(CustomerDao.class);
    CustomerResponse customer = Mockito.mock(CustomerResponse.class);
    when(customerRepository.findByCustomerId(Mockito.anyInt())).thenReturn(existingCustomerDao);
    when(customerMapper.mapCustomerRequestToCustomerDao(Mockito.any(CustomerRequest.class)))
        .thenReturn(customerDao);
    when(existingCustomerDao.getCustomerId()).thenReturn(1);
    when(existingCustomerDao.getStatus()).thenReturn(CustomerStatus.NEW.getValue());
    when(existingCustomerDao.getCreatedAt()).thenReturn(LocalDateTime.now());
    when(customerRepository.save(Mockito.any(CustomerDao.class))).thenReturn(customerDao);
    when(customerMapper.mapCustomerDaoToCustomerResponse(Mockito.any(CustomerDao.class)))
        .thenReturn(customer);
    CustomerRequest customerRequest =
        new CustomerRequest(
            "J Kolay", "test", "12", "1213", "Amstelveen", "jk@gmail.com", "1234567890");
    Assertions.assertNotNull(customerService.updateCustomer(1, customerRequest));
  }

  @Test
  public void deleteCustomerTestWithException() {
    CustomerDao existingCustomerDao = Mockito.mock(CustomerDao.class);
    when(customerRepository.findByCustomerId(Mockito.anyInt())).thenReturn(existingCustomerDao);
    when(existingCustomerDao.getStatus()).thenReturn(CustomerStatus.LEASED.getValue());
    Assertions.assertThrows(CustomerException.class, () -> customerService.deleteCustomer(1));
  }
}
