/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.services;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tamil.mts.mtsemployee.domain.Employee;
import com.tamil.mts.mtsemployee.mapper.EmployeeMapper;
import com.tamil.mts.mtsemployee.repositories.EmployeeRepository;
import com.tamil.mts.mtsemployee.web.model.EmployeeDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author murugan
 *
 */
@Slf4j
@RequiredArgsConstructor
@Service("beerService")
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	private final EmployeeMapper employeeMapper;

	@Override
	public Long getTotalEmployeeCount() {
		return employeeRepository.count();
	}
	
	@Override
	public EmployeeDto getEmployeeById(UUID employeeId) {
		return employeeMapper.convertToModel(employeeRepository.findById(employeeId).get());
	}

	@Override
	public EmployeeDto saveNewEmployee(EmployeeDto employeeDto) {
		return employeeMapper.convertToModel(employeeRepository.save(employeeMapper.converToDomain(employeeDto)));
		// return EmployeeDto.builder().id(UUID.randomUUID()).build();
	}

	@Override
	public EmployeeDto updateEmployee(UUID empId, EmployeeDto employeeDto) {
		Employee employee = employeeRepository.findById(empId).get();
		employee.setName(employeeDto.getName());
		employee.setAge(employeeDto.getAge());
		employee.setSalary(employeeDto.getSalary());
		employee.setEmployeeType(employeeDto.getEmployeeType().toString());
		employee.setJoiningDate(Timestamp.valueOf(employeeDto.getJoiningDate().toLocalDateTime()));
		return employeeMapper.convertToModel(employeeRepository.save(employee));
	}

	@Override
	public void deleteEmployeeById(UUID empId) {
		// TODO Implement delete Employee
		log.info("TODO: Implement delete Employee");
	}

}
