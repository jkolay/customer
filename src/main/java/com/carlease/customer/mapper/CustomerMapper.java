package com.carlease.customer.mapper;

import com.carlease.customer.model.request.CustomerRequest;
import com.carlease.customer.model.response.CustomerResponse;
import com.carlease.customer.persistence.CustomerDao;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/** Mapper class for customer service */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CustomerMapper {
  /**
   * This method maps customer request to customer dao
   *
   * @param customerRequest
   * @return
   */
  CustomerDao mapCustomerRequestToCustomerDao(CustomerRequest customerRequest);

  /**
   * this method maps customer dao to customer response
   *
   * @param customerDao
   * @return
   */
  CustomerResponse mapCustomerDaoToCustomerResponse(CustomerDao customerDao);
}
