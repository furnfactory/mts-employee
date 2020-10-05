/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tamil.mts.mtsemployee.web.model.EmployeeDto;

/**
 * @author murugan
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public EmployeeDto getEmployeeById(UUID employeeId) {
		return EmployeeDto.builder().id(UUID.randomUUID()).name("Murugan").age(33).build();
	}

}
