package com.example.soap.model;

import java.time.LocalDateTime;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

	@Override
	public LocalDateTime unmarshal(String value) {
		return value == null ? null : LocalDateTime.parse(value);
	}

	@Override
	public String marshal(LocalDateTime value) {
		return value == null ? null : value.toString();
	}
}
