package com.carlease.customer.repository;

import com.carlease.customer.persistence.CustomerDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Customer repository class */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerDao, Integer> {
  /**
   * find customer by email address
   * @param emailAddress
   * @return
   */
  CustomerDao findByEmailAddress(String emailAddress);

  /**
   * find customer from db by customer id
   * @param customerId
   * @return
   */
  CustomerDao findByCustomerId(Integer customerId);
}
