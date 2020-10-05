/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.services;

import java.util.UUID;

import com.tamil.mts.mtsemployee.web.model.EmployeeDto;

/**
 * @author murugan
 *
 */
public interface EmployeeService {

	EmployeeDto getEmployeeById(UUID employeeId);

	EmployeeDto saveNewEmployee(EmployeeDto employeeDto);
}
