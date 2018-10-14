package com.rnaomix.itemmanagement.repository;

import com.rnaomix.itemmanagement.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository {

    Role findByRole(Role.RoleName role);
}
