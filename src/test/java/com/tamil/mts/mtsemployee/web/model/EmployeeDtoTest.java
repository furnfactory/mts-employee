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
		log.info("Serialize EmployeeDTO : " + employee);
		log.info("Employee Json: " + employeeJson);
		assertNotNull(employeeJson, "Employee Dto serialized to Json format using Jackson.");
	}

	@Test
	//Json Format datetime pattern for reference
	//yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX 	- 2012-08-15T12:59:31.250716+05:30
	//yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ 	- 2012-08-15T12:59:31.250716+0530
	//yyyy-MM-dd'T'HH:mm:ssZ 			- 2012-08-15T12:59:31+0530
	public void deSerializeEmployeeJson() throws JsonProcessingException {
		String employeeJson = "{\"id\":\"858eacd8-30a4-49e0-a10c-c5fd028178c4\","
				+ "\"version\":null,\"empName\":\"murugan\",\"empAge\":40,\"createdDate\":null,"
				+ "\"lastModifiedDate\":null,\"joiningDate\":\"2012-08-15T12:59:31+0000\","
				+ "\"employeeType\":\"LABOUR\",\"salary\":3998.60,\"empDob\":\"1988-08-15\"}";
		EmployeeDto employee = objectMapper.readValue(employeeJson, EmployeeDto.class);
		log.info("Deserialize EmployeeJson: " + employeeJson);
		log.info("Employee DTO : " + employee);
		String employeeToJson = objectMapper.writeValueAsString(employee);
		log.info("Employee Output Json: " + employeeToJson);
		assertNotNull(employee, "Employee data in Json foramt deserialized to required Java object using Jackson.");
	}

}
