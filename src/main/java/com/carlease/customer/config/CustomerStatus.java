package com.carlease.customer.config;

/**
 * enum which maintains customer status
 */
public enum CustomerStatus {
    NEW("new"),
    LEASED("leased");

  private final String value;

    CustomerStatus(String value) {
        this.value = value;
    }

    public static CustomerStatus fromString(String value) {
        for (CustomerStatus customerStatus : CustomerStatus.values()) {
            if (customerStatus.value.equalsIgnoreCase(value)) {
                return customerStatus;
            }
        }
        return null;
    }

    public String getValue() {
        return this.value;
    }
}
