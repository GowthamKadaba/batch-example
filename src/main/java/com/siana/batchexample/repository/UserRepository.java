package com.siana.batchexample.repository;

import com.siana.batchexample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
}
