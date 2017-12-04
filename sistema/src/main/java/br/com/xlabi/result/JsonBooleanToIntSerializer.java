package br.com.xlabi.result;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonBooleanToIntSerializer extends JsonSerializer<Integer> {

	@Override
	public void serialize(Integer i, JsonGenerator aJsonGenerator, SerializerProvider aSerializerProvider)
			throws IOException, JsonProcessingException {
		Boolean b = false;
		if(i==1){
			b = true;
		}
		aJsonGenerator.writeBoolean(b);
	}
}
