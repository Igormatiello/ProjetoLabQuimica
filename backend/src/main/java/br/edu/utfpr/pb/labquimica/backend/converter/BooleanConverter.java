package br.edu.utfpr.pb.labquimica.backend.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class BooleanConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean value) {
		return (Boolean.TRUE.equals(value) ? "S" : "N");
	}

	@Override
	public Boolean convertToEntityAttribute(String value) {
		return ( "S".equals(value) );
	}

}
