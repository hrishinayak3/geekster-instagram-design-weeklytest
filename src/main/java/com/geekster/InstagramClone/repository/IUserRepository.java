package com.geekster.InstagramClone.repository;

import com.geekster.InstagramClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findFirstByEmail(String email);
}
