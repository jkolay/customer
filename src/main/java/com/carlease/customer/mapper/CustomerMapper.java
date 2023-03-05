package com.carlease.customer.mapper;

import com.carlease.customer.model.request.CustomerRequest;
import com.carlease.customer.model.response.CustomerResponse;
import com.carlease.customer.persistence.CustomerDao;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CustomerMapper {

    CustomerDao mapCustomerRequestToCustomerDao(CustomerRequest customerRequest);

    CustomerResponse mapCustomerDaoToCustomerResponse(CustomerDao customerDao);
}
