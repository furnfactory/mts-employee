/*
 * Created on 16-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.web.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author murugan
 *
 */
@Slf4j
@JsonTest
public class EmployeeDtoTest {

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void serializeEmployeeDto() throws JsonProcessingException {
		EmployeeDto employee = DataProducer.getValidEmployeeDto();
		String employeeJson = objectMapper.writeValueAsString(employee);
		log.info("Employee as Json: " + employeeJson);
		assertNotNull(employeeJson, "Employee Dto serialized to Json format using Jackson.");
	}

	@Test
	public void deSerializeEmployeeJson() throws JsonProcessingException {
		String jsonFormatEmployee = "{\"id\":\"858eacd8-30a4-49e0-a10c-c5fd028178c4\","
				+ "\"version\":null,\"name\":\"sqJRM\",\"age\":40,\"createdDate\":null,"
				+ "\"lastModifiedDate\":null,\"joiningDate\":\"2012-08-15T12:59:31.812785+05:30\","
				+ "\"employeeType\":\"LABOUR\",\"salary\":3998.60}";
		EmployeeDto employee = objectMapper.readValue(jsonFormatEmployee, EmployeeDto.class);
		assertNotNull(employee, "Employee data in Json foramt deserialized to required Java object using Jackson.");
	}

}
