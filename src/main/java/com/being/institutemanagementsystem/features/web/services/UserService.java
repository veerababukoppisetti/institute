package com.being.institutemanagementsystem.features.web.services;

import com.being.institutemanagementsystem.features.data.model.experience.student.CreateUserRequest;
import com.being.institutemanagementsystem.features.data.model.experience.student.User;
import com.being.institutemanagementsystem.features.data.model.persistence.UserEntity;
import com.being.institutemanagementsystem.features.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class UserService {

private  final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public User createUser(CreateUserRequest payload) {
        //1.transform payload to orm entity
        final UserEntity user = UserEntity.builder().username(payload.getUsername())
                .password(passwordEncoder.encode(payload.getPassword())).email(payload.getEmail())
                .build();

        //save the product
        final UserEntity newUser = userRepository.save(user);
        return User.builder().id(newUser.getId()).username(newUser.getUsername())
                .password(newUser.getPassword()).email(newUser.getEmail())
                .build();

    }

}
