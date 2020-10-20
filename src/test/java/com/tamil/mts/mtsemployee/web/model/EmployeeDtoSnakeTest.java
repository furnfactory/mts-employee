/*
 * Created on 17-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.web.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
@ActiveProfiles("snake")
@JsonTest
public class EmployeeDtoSnakeTest {

	@Autowired
	ObjectMapper objectMapper;

	// SNAKE_CASE: active profile - we use the naming strategy of the property.
	// Field names words are separated by  underscores
	@Test
	public void serializeEmployeeDto() throws JsonProcessingException {
		EmployeeDto employee = getValidEmployeeDto();
		String employeeJson = objectMapper.writeValueAsString(employee);
		log.info("Employee as Json: " + employeeJson);
		assertNotNull(employeeJson, "Employee Dto serialized to Json format using Jackson.");
	}

	@Test
	public void deSerializeEmployeeJson() throws JsonProcessingException {
		String jsonFormatEmployee = "{\"id\":\"fd024a25-51e3-458d-adbf-914f1b940b2a\","
				+ "\"version\":null,\"name\":\"gSLURHf\",\"age\":26,\"created_date\":null,"
				+ "\"last_modified_date\":null,\"joining_date\":\"2013-01-06T21:18:08+05:30\","
				+ "\"employee_type\":\"LABOUR\",\"salary\":3885.37}\"";
		EmployeeDto employee = objectMapper.readValue(jsonFormatEmployee, EmployeeDto.class);
		assertNotNull(employee, "Employee data in Json format deserialized to required Java object using Jackson.");
	}

	private EmployeeDto getValidEmployeeDto() {
		EmployeeDto employee = EmployeeDto.builder().id(UUID.randomUUID())
				.name(RandomStringUtils.randomAlphabetic(3, 50)).age(RandomUtils.nextInt(18, 60))
				.employeeType(EmployeeType.LABOUR)
				.salary(NumberUtils.toScaledBigDecimal(RandomUtils.nextDouble(1000, 5000)))
				.joiningDate(OffsetDateTime.now().minusDays(RandomUtils.nextLong(100, 3000))).build();
		log.info("Valid Employee generated: " + employee.toString());
		return employee;
	}
}
