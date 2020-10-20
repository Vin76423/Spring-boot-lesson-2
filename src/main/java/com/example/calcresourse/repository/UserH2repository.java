package com.example.calcresourse.repository;

import com.example.calcresourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserH2repository extends JpaRepository<User, Long> {
    User findByLoginAndPassword(String login, String password);
    boolean existsUserByLoginAndPassword(String login, String password);
    boolean existsUserByToken(long token);
}
