/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

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

	private UUID id;
	
	private String name;
	
	private Integer age;
	
	private OffsetDateTime createdDate;
	
    private OffsetDateTime lastModifiedDate;
    
    private OffsetDateTime joiningDate;
    
    private EmployeeType employeeType;
    
    private BigDecimal salary;
	
}
