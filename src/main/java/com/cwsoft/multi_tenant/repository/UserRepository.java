package com.cwsoft.multi_tenant.repository;

import com.cwsoft.multi_tenant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
