package com.martinsdev.learnhub.api.repositories;

import com.martinsdev.learnhub.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
