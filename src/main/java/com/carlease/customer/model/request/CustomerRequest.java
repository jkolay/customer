package com.carlease.customer.model.request;

import com.carlease.customer.config.CustomerValidationMessageConfig;
import com.carlease.customer.config.ValidationConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Customer request model object
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    @NotBlank(message = CustomerValidationMessageConfig.CUSTOMER_NAME_NOT_NULL)
    @Size(max = ValidationConstant.MAX_LENGTH_NAME, message = CustomerValidationMessageConfig.CUSTOMER_NAME_SIZE_NOT_VALID)
    @Pattern(regexp = ValidationConstant.PATTERN_NAME, message = CustomerValidationMessageConfig.CUSTOMER_NAME_PATTERN_NOT_VALID)
    @Schema(description = "The name of the Customer", example = "Jayati Kolay")
    private String name;

    @NotBlank(message = CustomerValidationMessageConfig.STREET_NOT_NULL)
    @Size(max = ValidationConstant.MAX_LENGTH_ADDRESS, message = CustomerValidationMessageConfig.STREET_SIZE_NOT_VALID)
    @Pattern(regexp = ValidationConstant.PATTERN_NAME, message = CustomerValidationMessageConfig.STREET_PATTERN_NOT_VALID)
    @Schema(description = "street", example = "pianoweg")
    private String street;

    @NotBlank(message = CustomerValidationMessageConfig.HOUSE_NUMBER_NOT_NULL)
    @Size(max = ValidationConstant.HOUSE_NUMBER_LENGTH, message = CustomerValidationMessageConfig.HOUSE_NUMBER_SIZE_NOT_VALID)
    @Pattern(regexp = ValidationConstant.HOUSE_NUMBER_LENGTH_NAME, message = CustomerValidationMessageConfig.HOUSE_NUMBER_PATTERN_NOT_VALID)
    @Schema(description = "The house number of the Customer", example = "120")
    private String houseNumber;

    @NotBlank(message = CustomerValidationMessageConfig.ZIPCODE_NOT_NULL)
    @Size(max = ValidationConstant.ZIPCODE_LENGTH, message = CustomerValidationMessageConfig.ZIPCODE_SIZE_NOT_VALID)
    @Pattern(regexp = ValidationConstant.PATTERN_ZIPCODE, message = CustomerValidationMessageConfig.ZIPCODE_PATTERN_NOT_VALID)
    @Schema(description = "Zipcode of the Customer", example = "1312JK")
    private String zipcode;

    @NotBlank(message = CustomerValidationMessageConfig.CUSTOMER_PLACE_NOT_NULL)
    @Size(max = ValidationConstant.PLACE_LENGTH, message = CustomerValidationMessageConfig.CUSTOMER_PLACE_SIZE_NOT_VALID)
    @Pattern(regexp = ValidationConstant.PLACE_PATTERN, message = CustomerValidationMessageConfig.CUSTOMER_PLACE_PATTERN_NOT_VALID)
    @Schema(description = "The place of the Customer", example = "Almere")
    private String place;

    @NotBlank(message = CustomerValidationMessageConfig.CUSTOMER_EMAIL_NOT_NULL)
    @Size(max = ValidationConstant.MAX_LENGTH_EMAIL, message = CustomerValidationMessageConfig.CUSTOMER_EMAIL_SIZE_NOT_VALID)
    @Pattern(regexp = ValidationConstant.PATTERN_EMAIL, message = CustomerValidationMessageConfig.CUSTOMER_EMAIL_PATTERN_NOT_VALID)
    @Schema(description = "The email of the Customer", example = "jayatii.kolaay@gmail.com")
    private String emailAddress;

    @NotBlank(message = CustomerValidationMessageConfig.CUSTOMER_PHONE_NUMBER_NOT_NULL)
    @Size(max = ValidationConstant.MAX_LENGTH_PHONE_NUMBER, message = CustomerValidationMessageConfig.CUSTOMER_PHONE_NUMBER_SIZE_NOT_VALID)
    @Pattern(regexp = ValidationConstant.PATTERN_PHONE_NUMBER, message = CustomerValidationMessageConfig.CUSTOMER_PHONE_NUMBER_PATTERN_NOT_VALID)
    @Schema(description = "The phone number of the Customer", example = "0620470774")
    private String phoneNumber;



}
