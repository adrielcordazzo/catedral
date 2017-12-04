package br.com.xlabi.result;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonStringRemoveNullSerializer extends JsonSerializer<String> {

	@Override
	public void serialize(String i, JsonGenerator aJsonGenerator, SerializerProvider aSerializerProvider)
			throws IOException, JsonProcessingException {

		String b = i;
		System.out.println(b + "xxxxxxxxx-");
		if (i == null) {
			b = "x";
		}
		aJsonGenerator.writeString(b);
	}
}
