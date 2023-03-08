package com.carlease.customer.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
/**
 * Customer Error response model class
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerErrorResponse {
    private List<CustomerErrorModel> errors;
}
