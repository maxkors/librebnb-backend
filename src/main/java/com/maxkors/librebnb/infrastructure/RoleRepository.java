package com.maxkors.librebnb.infrastructure;

import com.maxkors.librebnb.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
