package vn.trinhtung.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LongToLocalDateConverter());
    }
}

@ReadingConverter
class LongToLocalDateConverter implements Converter<Long, LocalDate> {

    @Override
    public LocalDate convert(Long epochDay) {
        return Instant.ofEpochMilli(epochDay).atZone(ZoneOffset.UTC).toLocalDate();
    }
}