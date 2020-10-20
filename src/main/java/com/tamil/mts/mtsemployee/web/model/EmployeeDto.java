/*
 * Created on 05-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.web.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author murugan
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

	@Null
	@JsonProperty("empId")
	private UUID id;

	@Null
	private Integer version;

	@NotBlank
	@Size(min = 3, max = 100)
	@JsonProperty("empName")
	private String name;

	@Min(18)
	@Max(60)
	@JsonProperty("empAge")
	private Integer age;

	@Null
	private OffsetDateTime createdDate;

	@Null
	@JsonProperty("modifiedDate")
	private OffsetDateTime lastModifiedDate;

	@NotNull
	//@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING, timezone = "UTC")
	@JsonSerialize(using = DateTimeSerializer.class)
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private OffsetDateTime joiningDate;

	@NotNull
	@JsonProperty("empType")
	private EmployeeType employeeType;

	@NotNull
	@Positive
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal salary;

	@Past
	@JsonProperty("empDob")
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private LocalDate dateOfBirth;

}
