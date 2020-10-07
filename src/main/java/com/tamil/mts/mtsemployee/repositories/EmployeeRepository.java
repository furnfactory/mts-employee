/*
 * Created on 07-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tamil.mts.mtsemployee.domain.Employee;

/**
 * @author murugan
 *
 */
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, UUID>{

}
