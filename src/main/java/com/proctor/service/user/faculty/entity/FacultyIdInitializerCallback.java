package com.proctor.service.user.faculty.entity;

import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Configuration
public class FacultyIdInitializerCallback implements BeforeConvertCallback<Faculty> {
    @Override
    public Publisher<Faculty> onBeforeConvert(Faculty entity, SqlIdentifier table) {
        if (entity.getFacultyId() == null)
            entity.setFacultyId(UUID.randomUUID());
        return Mono.just(entity);
    }
}