package com.proctor.service.user;


import com.proctor.service.user.entity.User;
import com.proctor.service.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> saveAndReturnUser(User user){
        return userRepository.save(user);
    }
}