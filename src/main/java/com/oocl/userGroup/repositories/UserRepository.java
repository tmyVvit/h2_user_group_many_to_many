package com.oocl.userGroup.repositories;

import com.oocl.userGroup.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
