package com.proctor.service.configuration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.boot.r2dbc.autoconfigure.R2dbcProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import java.util.List;

@Configuration
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class R2dbcConfiguration extends AbstractR2dbcConfiguration {

    private final R2dbcProperties r2dbcProperties;

    @Override
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(r2dbcProperties.getUrl());
    }

    @Override
    protected List<Object> getCustomConverters() {
        return List.of(new CharToUUIDConverter(), new UUIDToCharConverter());
    }
}