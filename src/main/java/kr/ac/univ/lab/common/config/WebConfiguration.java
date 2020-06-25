package kr.ac.univ.lab.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.ac.univ.lab.publication.converter.StringToKindType;
import kr.ac.univ.lab.publication.converter.StringToPublicationType;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
    	// publication
        registry.addConverter(new StringToKindType());
        registry.addConverter(new StringToPublicationType());
    }
}