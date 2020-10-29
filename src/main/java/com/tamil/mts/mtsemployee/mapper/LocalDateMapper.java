/*
 * Created on 29-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.mapper;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

/**
 * @author murugan
 *
 */
@Component
public class LocalDateMapper {

	public LocalDate convertToLocalDate(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}

		return timestamp.toLocalDateTime().toLocalDate();
	}

	public Timestamp convertToTimestamp(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		return Timestamp.valueOf(localDate.toString());
	}
}
