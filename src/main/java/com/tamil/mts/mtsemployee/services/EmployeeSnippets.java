/*
 * Created on 20-Oct-2020
 * Created by murugan
 * Copyright � 2020 MTS [murugan425]. All Rights Reserved.
 */
package com.tamil.mts.mtsemployee.services;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author murugan
 *
 */
public class EmployeeSnippets {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Locale[] locales = Locale.getAvailableLocales();
		System.out.println("Double: " + String.valueOf(Math.pow(10, 7)));
		
		System.out.println(String.valueOf((int)RandomUtils.nextDouble(Math.pow(10, 9), Math.pow(10, 10))));
		
		//IntStream.range(10, 10).forEach(i ->
		//	System.out.println(RandomUtils.nextInt((int)Math.pow(10, 9), (int)Math.pow(10, 10))));
		/*
		List<Locale> localeList = new ArrayList<Locale>();
		for (Locale locale : locales) {
			System.out.println(locale.getCountry() + "," + locale.getDisplayCountry() + ","
					+ locale.getDisplayLanguage() + "," + locale.getDisplayName() + "," + locale.getLanguage());
		}
		*/
		/*
		 * System.out.println(DateTimeFormatter.ISO_INSTANT.parse("2012-08-15T07:29:31Z"
		 * )); System.out.println(LocalDateTime.parse("2012-08-15T07:29:31",
		 * DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		 * System.out.println(ZonedDateTime.parse("2012-08-15T07:29:31Z",
		 * DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.of("+05:30")))); ZonedDateTime
		 * zdt = ZonedDateTime.parse("2012-08-15T07:29:31Z",
		 * DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.of("+05:30")));
		 * System.out.println(OffsetDateTime.of(LocalDateTime.parse(
		 * "2012-08-15T07:29:31", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
		 * ZoneOffset.of("+05:30")));
		 * System.out.println(zdt.withZoneSameLocal(ZoneId.of("+05:30")).toString());
		 */
	}

}
