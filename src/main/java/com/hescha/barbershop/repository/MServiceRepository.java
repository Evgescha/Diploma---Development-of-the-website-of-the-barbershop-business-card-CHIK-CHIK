package com.hescha.barbershop.repository;

import com.hescha.barbershop.entity.MService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MServiceRepository extends JpaRepository<MService, Long> {
	MService findByName(String name);
}
