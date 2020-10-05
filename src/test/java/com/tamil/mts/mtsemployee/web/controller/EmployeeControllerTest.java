/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tamil.mts.mtsemployee.services.EmployeeService;
import com.tamil.mts.mtsemployee.web.model.EmployeeDto;
import com.tamil.mts.mtsemployee.web.model.EmployeeType;

/**
 * @author murugan
 *
 */
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	EmployeeService employeeService;

	@Test
	void getEmployeeById() throws Exception {
		given(employeeService.getEmployeeById(any(UUID.class))).willReturn(getEmployeeDto());
		mockMvc.perform(get("/api/v1/employee/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void createEmployee() throws Exception {
		EmployeeDto employeeDto = getEmployeeDto();
		String employeeDtoJson = objectMapper.writeValueAsString(employeeDto);
		given(employeeService.saveNewEmployee(any(EmployeeDto.class))).willReturn(getEmployeeDto());
		mockMvc.perform(post("/api/v1/employee/").contentType(MediaType.APPLICATION_JSON).content(employeeDtoJson))
				.andExpect(status().isCreated());
	}

	EmployeeDto getEmployeeDto() {
		return EmployeeDto.builder().id(UUID.randomUUID()).name("TestEmployee").age(24)
				.employeeType(EmployeeType.LABOUR).build();
	}
}
