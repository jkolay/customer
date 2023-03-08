package com.carlease.customer.model.error;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Customer Error Model class for to display error to user */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestErrorModel {

  private Map<String, String> errorDescription;
  private String code;
  private ErrorSeverityLevelCodeType severityLevel;
}
