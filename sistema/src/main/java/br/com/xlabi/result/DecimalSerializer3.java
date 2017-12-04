package br.com.xlabi.result;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DecimalSerializer3 extends JsonSerializer<Double> {
	@Override
	public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		// put your desired money style here
		
		Locale locale  = new Locale("en", "UK");
		String pattern = "####0.000";
		
		DecimalFormat decimalFormat = (DecimalFormat)
		NumberFormat.getNumberInstance(locale);
		decimalFormat.applyPattern(pattern);

		
		jgen.writeString(decimalFormat.format(value));
	}
}
