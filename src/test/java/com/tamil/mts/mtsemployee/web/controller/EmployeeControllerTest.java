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
import com.tamil.mts.mtsemployee.web.model.DataProducer;
import com.tamil.mts.mtsemployee.web.model.EmployeeDto;

/**
 * @author murugan
 *
 */
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "mtsapps.in", uriPort = 80)
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
		given(employeeService.getEmployeeById(any(UUID.class))).willReturn(DataProducer.getValidEmployeeDto());

		ConstrainedFields fields = new ConstrainedFields(EmployeeDto.class);

		mockMvc.perform(get(EMPLOYEE_API_PATH + "{empId}", UUID.randomUUID().toString()).param("alldetails", "yes")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(document("api/v1/employee/get",
						pathParameters(parameterWithName("empId").description("UUID of the desired Employee Id.")),
						requestParameters(
								parameterWithName("alldetails").description("Get all details of the Employee.")),
						responseFields(fields.withPath("empId").description("Employee UUID Id"),
								fields.withPath("version").description("Version number"),
								fields.withPath("empName").description("Employee Name"),
								fields.withPath("empAge").description("Employee Age"),
								fields.withPath("createdDate").description("Created Date"),
								fields.withPath("modifiedDate").description("Date Updated"),
								fields.withPath("joiningDate").description("Employee Joining Date"),
								fields.withPath("empType").description("Employee Type"),
								fields.withPath("salary").description("Base Salary"),
								fields.withPath("empDob").description("Employee Date Of Birth"))));
	}

	@Test
	public void createValidEmployee() throws Exception {
		EmployeeDto employeeDto = DataProducer.getNewEmployeeDto();
		String employeeDtoJson = objectMapper.writeValueAsString(employeeDto);
		given(employeeService.saveNewEmployee(any(EmployeeDto.class))).willReturn(DataProducer.getValidEmployeeDto());

		ConstrainedFields fields = new ConstrainedFields(EmployeeDto.class);

		mockMvc.perform(post(EMPLOYEE_API_PATH).contentType(MediaType.APPLICATION_JSON).content(employeeDtoJson))
				.andExpect(status().isCreated())
				.andDo(document("api/v1/employee/post",
						requestFields(fields.withPath("empId").ignored(), fieldWithPath("version").ignored(),
								fields.withPath("createdDate").ignored(), fieldWithPath("modifiedDate").ignored(),
								fields.withPath("empName").description("Employee Name"),
								fields.withPath("empAge").description("Employee Age"),
								fields.withPath("joiningDate").description("Employee Joining Date"),
								fields.withPath("empType").description("Employee Type"),
								fields.withPath("salary").description("Salary Amount [INR]"),
								fields.withPath("empDob").description("Employee Date Of Birth"))));
	}

	@Test
	public void createInvalidEmployee() throws Exception {
		EmployeeDto employeeDto = DataProducer.getInvalidEmployeeDto();
		String employeeDtoJson = objectMapper.writeValueAsString(employeeDto);
		given(employeeService.saveNewEmployee(any(EmployeeDto.class))).willReturn(DataProducer.getValidEmployeeDto());
		mockMvc.perform(post(EMPLOYEE_API_PATH).contentType(MediaType.APPLICATION_JSON).content(employeeDtoJson))
				.andExpect(status().is4xxClientError());
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
