/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tamil.mts.mtsemployee.services.EmployeeService;
import com.tamil.mts.mtsemployee.web.model.EmployeeDto;
import com.tamil.mts.mtsemployee.web.model.EmployeeType;

/**
 * @author murugan
 *
 */
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	EmployeeService employeeService;

	private final String EMPLOYEE_API_PATH = "/api/v1/employee/";

	@Test
	public void getEmployeeById() throws Exception {
		given(employeeService.getEmployeeById(any(UUID.class))).willReturn(getValidEmployeeDto());
		mockMvc.perform(
				get(EMPLOYEE_API_PATH + "{empId}", UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document("api/v1/employee/",
						pathParameters(parameterWithName("empId").description("UUID of the desired Employee Id.")),
						responseFields(fieldWithPath("id").description("Employee UUID Id"),
								fieldWithPath("version").description("Version number"),
								fieldWithPath("name").description("Employee Name"),
								fieldWithPath("age").description("Employee Age"),
								fieldWithPath("createdDate").description("Created Date"),
								fieldWithPath("lastModifiedDate").description("Date Updated"),
								fieldWithPath("joiningDate").description("Employee Joining Date"),
								fieldWithPath("employeeType").description("Employee Type"),
								fieldWithPath("salary").description("Base Salary"))));
	}

	@Test
	public void createValidEmployee() throws Exception {
		EmployeeDto employeeDto = getNewEmployeeDto();
		String employeeDtoJson = objectMapper.writeValueAsString(employeeDto);
		given(employeeService.saveNewEmployee(any(EmployeeDto.class))).willReturn(getValidEmployeeDto());
		mockMvc.perform(post(EMPLOYEE_API_PATH).contentType(MediaType.APPLICATION_JSON).content(employeeDtoJson))
				.andExpect(status().isCreated())
				.andDo(document("api/v1/employee/",
						requestFields(fieldWithPath("id").ignored(), fieldWithPath("version").ignored(),
								fieldWithPath("createdDate").ignored(), fieldWithPath("lastModifiedDate").ignored(),
								fieldWithPath("name").description("Employee Name"),
								fieldWithPath("age").description("Employee Age"),
								fieldWithPath("joiningDate").description("Employee Joining Date"),
								fieldWithPath("employeeType").description("Employee Type"),
								fieldWithPath("salary").description("Salary Amount [INR]"))));
	}

	@Test
	public void createInvalidEmployee() throws Exception {
		EmployeeDto employeeDto = getInvalidEmployeeDto();
		String employeeDtoJson = objectMapper.writeValueAsString(employeeDto);
		given(employeeService.saveNewEmployee(any(EmployeeDto.class))).willReturn(getValidEmployeeDto());
		mockMvc.perform(post(EMPLOYEE_API_PATH).contentType(MediaType.APPLICATION_JSON).content(employeeDtoJson))
				.andExpect(status().is4xxClientError());
	}

	private EmployeeDto getValidEmployeeDto() {
		return EmployeeDto.builder().id(UUID.randomUUID()).name("TestEmployee").age(24)
				.employeeType(EmployeeType.LABOUR).build();
	}

	private EmployeeDto getNewEmployeeDto() {
		return EmployeeDto.builder().name("Test").age(24).joiningDate(OffsetDateTime.now())
				.salary(BigDecimal.valueOf(1000.00)).employeeType(EmployeeType.LABOUR).build();
	}

	private EmployeeDto getInvalidEmployeeDto() {
		return EmployeeDto.builder().id(UUID.randomUUID()).name("").age(90).employeeType(EmployeeType.LABOUR).build();
	}
}
