/*
 * Created on 19-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.web.model;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * @author murugan
 *
 */
@Slf4j
@Component
public class DateTimeSerializer extends JsonSerializer<OffsetDateTime> {

	@Value("${user.timezone}")
	private String timeZone;

	@Override
	public void serialize(OffsetDateTime value, JsonGenerator jsonGenerator, SerializerProvider serializers)
			throws IOException {
		//Serialize the time to local time based on the user timezone format
		log.info("OffsetDateTime: " + value + " serialized as " + value.withOffsetSameInstant(ZoneOffset.of(timeZone)));
		jsonGenerator.writeObject(value.withOffsetSameLocal(ZoneOffset.of(timeZone)));
	}

}
