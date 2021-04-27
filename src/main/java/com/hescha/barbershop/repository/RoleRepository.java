package com.hescha.barbershop.repository;

import com.hescha.barbershop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String login);
	Role findByNameIgnoreCase(String login);
}
