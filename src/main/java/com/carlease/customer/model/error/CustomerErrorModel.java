package com.carlease.customer.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerErrorModel {
    private String description;
    private String code;
    private ErrorSeverityLevelCodeType severityLevel;

}
