/*
 * Created on 19-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.web.model;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author murugan
 *
 */
@Slf4j
@Component
public class DateTimeDeserializer extends StdDeserializer<OffsetDateTime> {

	private static final long serialVersionUID = 1L;

	protected DateTimeDeserializer() {
		super(OffsetDateTime.class);
	}

	@Override
	public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		//Deserialize the input date-time passed in json input with local timezone to UTC
		String localTime = p.readValueAs(String.class);
		OffsetDateTime utcTime = OffsetDateTime.parse(localTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME).withOffsetSameInstant(ZoneOffset.UTC);
		log.info("OffsetDateTime: " + localTime + " deserialized as " + utcTime);
		return utcTime;
	}

}
