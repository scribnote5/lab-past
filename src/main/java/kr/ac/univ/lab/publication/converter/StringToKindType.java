package kr.ac.univ.lab.publication.converter;

import org.springframework.core.convert.converter.Converter;

import kr.ac.univ.lab.publication.domain.enums.KindType;

public class StringToKindType implements Converter<String, KindType> {

	@Override
	public KindType convert(String source) {
		return KindType.valueOf(source.toUpperCase());
	}
}