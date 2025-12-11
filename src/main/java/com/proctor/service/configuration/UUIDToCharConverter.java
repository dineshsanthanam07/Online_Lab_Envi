package com.proctor.service.configuration;

import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.util.UUID;

@WritingConverter
class UUIDToCharConverter implements Converter<UUID, String> {

    @Override
    public @Nullable String convert(UUID source) {
        return source.toString();
    }
}