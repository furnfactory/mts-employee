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
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
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
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

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

		ConstrainedFields fields = new ConstrainedFields(EmployeeDto.class);

		mockMvc.perform(get(EMPLOYEE_API_PATH + "{empId}", UUID.randomUUID().toString()).param("alldetails", "yes")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(document("api/v1/employee/",
						pathParameters(parameterWithName("empId").description("UUID of the desired Employee Id.")),
						requestParameters(
								parameterWithName("alldetails").description("Get all details of the Employee.")),
						responseFields(fields.withPath("id").description("Employee UUID Id"),
								fields.withPath("version").description("Version number"),
								fields.withPath("name").description("Employee Name"),
								fields.withPath("age").description("Employee Age"),
								fields.withPath("createdDate").description("Created Date"),
								fields.withPath("lastModifiedDate").description("Date Updated"),
								fields.withPath("joiningDate").description("Employee Joining Date"),
								fields.withPath("employeeType").description("Employee Type"),
								fields.withPath("salary").description("Base Salary"))));
	}

	@Test
	public void createValidEmployee() throws Exception {
		EmployeeDto employeeDto = getNewEmployeeDto();
		String employeeDtoJson = objectMapper.writeValueAsString(employeeDto);
		given(employeeService.saveNewEmployee(any(EmployeeDto.class))).willReturn(getValidEmployeeDto());

		ConstrainedFields fields = new ConstrainedFields(EmployeeDto.class);

		mockMvc.perform(post(EMPLOYEE_API_PATH).contentType(MediaType.APPLICATION_JSON).content(employeeDtoJson))
				.andExpect(status().isCreated())
				.andDo(document("api/v1/employee/",
						requestFields(fields.withPath("id").ignored(), fieldWithPath("version").ignored(),
								fields.withPath("createdDate").ignored(), fieldWithPath("lastModifiedDate").ignored(),
								fields.withPath("name").description("Employee Name"),
								fields.withPath("age").description("Employee Age"),
								fields.withPath("joiningDate").description("Employee Joining Date"),
								fields.withPath("employeeType").description("Employee Type"),
								fields.withPath("salary").description("Salary Amount [INR]"))));
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

	private static class ConstrainedFields {

		private final ConstraintDescriptions constraintDescriptions;

		ConstrainedFields(Class<?> input) {
			this.constraintDescriptions = new ConstraintDescriptions(input);
		}

		private FieldDescriptor withPath(String path) {
			return fieldWithPath(path).attributes(key("constraints").value(StringUtils
					.collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path), ". ")));
		}
	}
}
