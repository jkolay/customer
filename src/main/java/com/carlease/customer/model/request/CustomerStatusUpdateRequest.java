package com.carlease.customer.model.request;

import com.carlease.customer.config.CustomerValidationMessageConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Customer status update model class
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerStatusUpdateRequest {
    @NotBlank(message = CustomerValidationMessageConfig.STATUS_NOT_NULL)
    @Schema(description = "The status of the Customer", example = "Leased")
    public String status;
}
