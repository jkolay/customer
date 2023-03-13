package com.carlease.customer.mapper;


import com.carlease.customer.model.request.UserRequestModel;import com.carlease.customer.persistence.RegisterUser;import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {

    RegisterUser mapUserRequestModelToCustomer(UserRequestModel userRequestModel);
}
