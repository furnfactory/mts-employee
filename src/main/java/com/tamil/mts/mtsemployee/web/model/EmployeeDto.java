/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author murugan
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

	@Null
	private UUID id;
	
	@NotBlank
	private String name;
	
	@Min(18) @Max(60)
	private Integer age;
	
	@Null
	private OffsetDateTime createdDate;
	
	@Null
    private OffsetDateTime lastModifiedDate;
    
	@NotNull
    private OffsetDateTime joiningDate;
    
	@NotNull
    private EmployeeType employeeType;
    
	@NotNull
    @Positive
    private BigDecimal salary;
	
}
