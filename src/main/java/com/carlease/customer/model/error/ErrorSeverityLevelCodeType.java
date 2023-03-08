package com.carlease.customer.model.error;

/** Model for Error Severity Level */
public enum ErrorSeverityLevelCodeType {
  WARNING,
  ERROR,
  INFORMATION;

  public static ErrorSeverityLevelCodeType getValue(String name) {
    ErrorSeverityLevelCodeType value = null;
    for (ErrorSeverityLevelCodeType errorSeverityLevelCodeType :
        ErrorSeverityLevelCodeType.values()) {
      if (errorSeverityLevelCodeType.name().equalsIgnoreCase(name)) {
        value = errorSeverityLevelCodeType;
      }
    }
    return value;
  }
}
