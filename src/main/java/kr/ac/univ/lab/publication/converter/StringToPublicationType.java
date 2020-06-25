package kr.ac.univ.lab.publication.converter;

import org.springframework.core.convert.converter.Converter;

import kr.ac.univ.lab.publication.domain.enums.PublicationType;

public class StringToPublicationType implements Converter<String, PublicationType> {

	@Override
	public PublicationType convert(String source) {
		return PublicationType.valueOf(source.toUpperCase());
	}

}