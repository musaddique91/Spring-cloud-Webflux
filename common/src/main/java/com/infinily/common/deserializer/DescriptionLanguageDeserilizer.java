package com.infinily.common.deserializer;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.MessageSource;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@JsonComponent
public class DescriptionLanguageDeserilizer extends JsonDeserializer<String>{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String text = p.getText();
		return getMessage(text);
	}
	
	private String getMessage(String code) {
		Locale locale =Locale.ENGLISH;
		return messageSource.getMessage(code, null, locale);
	}

}
