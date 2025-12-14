package com.proctor.service.user.entity;

import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Configuration
public class UserIdInitializerCallback implements BeforeConvertCallback<User> {
    @Override
    public Publisher<User> onBeforeConvert(User entity, SqlIdentifier table) {
        if (entity.getUserId() == null)
            entity.setUserId(UUID.randomUUID());
        return Mono.just(entity);
    }
}