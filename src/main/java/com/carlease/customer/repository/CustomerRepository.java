package com.carlease.customer.repository;

import com.carlease.customer.persistence.CustomerDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Customer repository class
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerDao, Integer> {
    CustomerDao findByEmailAddress(String emailAddress);

    CustomerDao findByCustomerId(Integer customerId);
}
