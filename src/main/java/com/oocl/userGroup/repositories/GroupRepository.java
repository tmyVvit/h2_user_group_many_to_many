package com.oocl.userGroup.repositories;

import com.oocl.userGroup.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
