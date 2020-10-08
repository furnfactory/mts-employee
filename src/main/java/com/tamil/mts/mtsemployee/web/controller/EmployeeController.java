/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.exception.spi.ViolatedConstraintNameExtracter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

import lombok.extern.slf4j.Slf4j;

/**
 * @author murugan
 *
 */
@Slf4j
@Validated
@RequestMapping("api/v1/employee")
@RestController
public class EmployeeController {

	private Object object;
	private Object object2;
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping({ "/{empId}" })
	public ResponseEntity<EmployeeDto> getEmployeeById(@NotNull @PathVariable("empId") UUID empId) {
		return new ResponseEntity<>(employeeService.getEmployeeById(empId), HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity createEmployee(@Valid @NotNull @RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployeeDto = employeeService.saveNewEmployee(employeeDto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "api/v1/employee/" + savedEmployeeDto.getId().toString());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@SuppressWarnings({ "rawtypes" })
	@PutMapping({ "/{empId}" })
	public ResponseEntity updateEmployee(@NotNull @PathVariable("empId") UUID empId,
			@Valid @NotNull @RequestBody EmployeeDto employeeDto) {
		employeeService.updateEmployee(empId, employeeDto);
		return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
	}

	@DeleteMapping({ "/{empId}" })
	@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
	public void deleteEmployee(@NotNull @PathVariable("empId") UUID empId) {
		employeeService.deleteEmployeeById(empId);
	}

	// TODO - Difference between ConstraintViolation & MethodArgumentNotValid
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException ex) {
		List<String> validationErrors = new ArrayList<>(ex.getConstraintViolations().size());
		ex.getConstraintViolations().forEach(violation -> {
			validationErrors.add(violation.getPropertyPath() + "_" + violation.getMessage());
		});
		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> validationErrorHandler(MethodArgumentNotValidException ex) {
		List<String> validationErrors = new ArrayList<>(ex.getBindingResult().getErrorCount());
		ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
			log.warn("Dto: {}, Invalid Request: {} = {},  Message: {}", fieldError.getObjectName(),
					fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
			validationErrors.add(String.format("%s = %s, message: %s", fieldError.getField(),
					fieldError.getRejectedValue(), fieldError.getDefaultMessage()));
		});
		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	}

}
