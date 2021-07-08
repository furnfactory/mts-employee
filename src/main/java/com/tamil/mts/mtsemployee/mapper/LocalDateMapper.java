/*
 * Created on 29-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.mapper;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

/**
 * @author murugan
 *
 */
@Component
public class LocalDateMapper {

	public LocalDate convertToLocalDate(Date date) {
		if (date == null) {
			return null;
		}

		return date.toLocalDate();
	}

	public Date convertToTimestamp(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		return Date.valueOf(localDate);
	}
}
