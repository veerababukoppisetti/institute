package com.being.institutemanagementsystem.features.data.repository;

import com.being.institutemanagementsystem.features.data.model.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByEmail(String username);
}
