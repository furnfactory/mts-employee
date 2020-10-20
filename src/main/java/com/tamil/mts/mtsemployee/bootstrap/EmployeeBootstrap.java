/*
 * Created on 07-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.bootstrap;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tamil.mts.mtsemployee.domain.Employee;
import com.tamil.mts.mtsemployee.repositories.EmployeeRepository;
import com.tamil.mts.mtsemployee.services.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author murugan
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor 
public class EmployeeBootstrap implements CommandLineRunner {

	private final EmployeeService employeeService;
	
	private final DataProducer dataProducer;

	@Override
	public void run(String... args) throws Exception {
		loadEmployees();
	}

	private void loadEmployees() {
		log.info(this.getClass().getSimpleName() + ": loadEmployees()");
		employeeService.saveNewEmployee(dataProducer.getValidEmployeeDto());
		employeeService.saveNewEmployee(dataProducer.getValidEmployeeDto());
		log.info("Total Employees Loaded : " + employeeService.getTotalEmployeeCount().toString());
	}
}
