/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.web.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tamil.mts.mtsemployee.services.EmployeeService;
import com.tamil.mts.mtsemployee.web.model.EmployeeDto;

/**
 * @author murugan
 *
 */
@RequestMapping("api/v1/employee")
@RestController
public class EmployeeController {

	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
	
	@GetMapping({"/{empId}"})
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("empId") UUID empId) {
		 return new ResponseEntity<>(employeeService.getEmployeeById(empId), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity createEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployeeDto = employeeService.saveNewEmployee(employeeDto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("savedEmployeeURI", "api/v1/employee/" + savedEmployeeDto.getId().toString());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@SuppressWarnings({ "rawtypes"})
	@PutMapping({"/{empId}"})
    public ResponseEntity updateEmployee(@PathVariable("empId") UUID empId, @RequestBody EmployeeDto employeeDto) {
		employeeService.updateEmployee(empId, employeeDto);
		return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
	}
	
	@DeleteMapping({"/{empId}"})
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void deleteBeer(@PathVariable("empId") UUID empId){
		employeeService.deleteEmployeeById(empId);
    }
}
