package br.com.xlabi.result;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonBooleanToIntDeserializer extends JsonDeserializer<Integer> {

	@Override
	public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {

		try {
			final Boolean jsonValue = jsonParser.getBooleanValue();
			if (jsonValue) {
				return 1;
			}
			return 0;
		} catch (Exception e) {
			try {
				final Integer jsonValue = jsonParser.getIntValue();
				return jsonValue;
			} catch (Exception ee) {
				return 0;
			}

		}
	}
}
