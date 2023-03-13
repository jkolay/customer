package com.carlease.customer.repository;

import com.carlease.customer.persistence.RegisterUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoginRepository extends CrudRepository<RegisterUser, Long> {

  List<RegisterUser> findByEmail(String email);
}