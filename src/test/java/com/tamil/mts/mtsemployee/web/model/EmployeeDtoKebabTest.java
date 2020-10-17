/*
 * Created on 17-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.web.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author murugan
 *
 */
@Slf4j
@ActiveProfiles("kebab")
@JsonTest
public class EmployeeDtoKebabTest {

	@Autowired
	ObjectMapper objectMapper;

	// KEBAB_CASE: active profile - we use the naming strategy of the property.
	// Field name words are separated by hyphen
	@Test
	public void serializeEmployeeDto() throws JsonProcessingException {
		EmployeeDto employee = DataProducer.getValidEmployeeDto();
		String employeeJson = objectMapper.writeValueAsString(employee);
		log.info("Employee as Json: " + employeeJson);
		assertNotNull(employeeJson, "Employee Dto serialized to Json format using Jackson.");
	}

	@Test
	public void deSerializeEmployeeJson() throws JsonProcessingException {
		String jsonFormatEmployee = "{\"id\":\"b9e34c0a-2b85-4f5f-afa4-8b41699cedf4\","
				+ "\"version\":null,\"name\":\"gDELEenZNXjhAosRksDULHOjuhXKCvheJdn\",\"age\":30,\"created-date\":null,"
				+ "\"last-modified-date\":null,\"joining-date\":\"2015-12-05T21:24:00.26348+05:30\","
				+ "\"employee-type\":\"LABOUR\",\"salary\":1088.04}\n";
		EmployeeDto employee = objectMapper.readValue(jsonFormatEmployee, EmployeeDto.class);
		assertNotNull(employee, "Employee data in Json foramt deserialized to required Java object using Jackson.");
	}

}
