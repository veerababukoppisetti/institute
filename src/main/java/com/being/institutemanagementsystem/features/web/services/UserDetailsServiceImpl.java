package com.being.institutemanagementsystem.features.web.services;

import com.being.institutemanagementsystem.features.data.model.experience.student.User;
import com.being.institutemanagementsystem.features.data.model.persistence.UserEntity;
import com.being.institutemanagementsystem.features.data.repository.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<UserEntity> matchingUser = userRepository.findByEmail(username);
        if (!matchingUser.isPresent()) {
            throw new ServiceException("Failed to find user with username: " + username);
        }

        final UserEntity customer = matchingUser.get();
       return new org.springframework.security.core.userdetails.User (customer.getUsername(), customer.getPassword(),new ArrayList<>());

    }




}
