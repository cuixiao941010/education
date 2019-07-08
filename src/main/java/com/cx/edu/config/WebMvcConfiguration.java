package com.cx.edu.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

	private static final String DEFAULT_LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_LOCAL_DATE_TIME_PATTERN);


	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
			.serializationInclusion(JsonInclude.Include.NON_NULL)
			.serializationInclusion(JsonInclude.Include.NON_EMPTY)
			.serializerByType(LocalDate.class, new LocalDateSerializer(DATE_FORMATTER))
			.deserializerByType(LocalDate.class, new LocalDateDeserializer(DATE_FORMATTER));
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
	}


	@Override
	public FormattingConversionService mvcConversionService() {
		FormattingConversionService conversionService = new FormattingConversionService();
		addFormatters(conversionService);
		return conversionService;
	}

	@Override
	protected void addFormatters(FormatterRegistry registry) {
		// 覆盖DefaultConversionService的ConvererFactory顺序, 先注册自定义的
		registry.addConverterFactory(stringToCommonEnumConverterFactory());
		DefaultConversionService.addDefaultConverters(registry);
		DefaultFormattingConversionService.addDefaultFormatters(registry);
	}


	public ConverterFactory stringToCommonEnumConverterFactory() {
		return new StringToCommonEnumConverterFactory();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/resources/")
				.addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}

}
