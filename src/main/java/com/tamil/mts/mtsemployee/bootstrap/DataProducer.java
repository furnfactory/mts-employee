/*
 * Created on 17-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.bootstrap;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import com.tamil.mts.mtsemployee.web.model.EmployeeDto;
import com.tamil.mts.mtsemployee.web.model.EmployeeType;

import lombok.extern.slf4j.Slf4j;

/**
 * @author murugan
 *
 */
@Slf4j
@Component
public class DataProducer {

	public EmployeeDto getValidEmployeeDto() {
		EmployeeDto employee = EmployeeDto.builder().id(UUID.randomUUID())
				.name(RandomStringUtils.randomAlphabetic(3, 50)).age(RandomUtils.nextInt(18, 60))
				.employeeType(EmployeeType.LABOUR)
				.salary(NumberUtils.toScaledBigDecimal(RandomUtils.nextDouble(1000, 5000)))
				.joiningDate(OffsetDateTime.now(ZoneOffset.UTC).minusDays(RandomUtils.nextLong(100, 3000)))
				.dateOfBirth(LocalDate.now().minusYears(RandomUtils.nextLong(20, 50))).build();
		log.info("Valid Employee generated: " + employee.toString());
		return employee;
	}

	public EmployeeDto getNewEmployeeDto() {
		EmployeeDto employee = EmployeeDto.builder().name(RandomStringUtils.randomAlphabetic(10, 60))
				.age(RandomUtils.nextInt(18, 60))
				.joiningDate(OffsetDateTime.now(ZoneOffset.of("+05:30")).minusDays(RandomUtils.nextLong(100, 3000)))
				.salary(NumberUtils.toScaledBigDecimal(RandomUtils.nextDouble(1000, 5000)))
				.employeeType(EmployeeType.ACCOUNTANT).build();
		log.info("Valid New Employee generated: " + employee.toString());
		return employee;
	}

	public EmployeeDto getInvalidEmployeeDto() {
		EmployeeDto employee = EmployeeDto.builder().id(UUID.randomUUID())
				.name(RandomStringUtils.randomAlphabetic(0, 2)).age(RandomUtils.nextInt(70, 100))
				.employeeType(EmployeeType.LABOUR)
				.dateOfBirth(LocalDate.now().minusYears(RandomUtils.nextLong(10, 15))).build();
		log.info("Invalid Employee generated: " + employee.toString());
		return employee;
	}

}
