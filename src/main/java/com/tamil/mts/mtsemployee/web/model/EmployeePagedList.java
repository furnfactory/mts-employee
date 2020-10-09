/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.web.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * @author murugan
 *
 */
public class EmployeePagedList extends PageImpl<EmployeeDto> {

	private static final long serialVersionUID = 1L;

	public EmployeePagedList(List<EmployeeDto> content) {
		super(content);
	}

	public EmployeePagedList(List<EmployeeDto> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}
}
