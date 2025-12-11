package com.proctor.service.configuration;

import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.UUID;

@ReadingConverter
class CharToUUIDConverter implements Converter<String, UUID> {

    @Override
    public @Nullable UUID convert(String source) {
        return UUID.fromString(source);
    }
}