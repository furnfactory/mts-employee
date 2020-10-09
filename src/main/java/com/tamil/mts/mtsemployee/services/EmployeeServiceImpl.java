/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tamil.mts.mtsemployee.web.model.EmployeeDto;

import lombok.extern.slf4j.Slf4j;

/**
 * @author murugan
 *
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public EmployeeDto getEmployeeById(UUID employeeId) {
		return EmployeeDto.builder().id(UUID.randomUUID()).name("Murugan").age(33).build();
	}

	@Override
	public EmployeeDto saveNewEmployee(EmployeeDto employeeDto) {
		return EmployeeDto.builder().id(UUID.randomUUID()).build();
	}

	@Override
	public void updateEmployee(UUID empId, EmployeeDto employeeDto) {
		// TODO Implement update Employee
		log.info("TODO: Implement update Employee");
	}

	@Override
	public void deleteEmployeeById(UUID empId) {
		// TODO Implement delete Employee
		log.info("TODO: Implement delete Employee");
	}

}
