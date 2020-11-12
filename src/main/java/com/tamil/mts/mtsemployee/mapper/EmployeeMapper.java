/*
 * Created on 09-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.mapper;

import org.mapstruct.Mapper;

import com.tamil.mts.mtsemployee.domain.Employee;
import com.tamil.mts.mtsemployee.web.model.EmployeeDto;

/**
 * @author murugan
 *
 */
@Mapper(uses = {DateTimeMapper.class, LocalDateMapper.class, LocalTimestampMapper.class})
public interface EmployeeMapper {

	EmployeeDto convertToModel(Employee employee);

	Employee convertToDomain(EmployeeDto employeeDto);
	
}
