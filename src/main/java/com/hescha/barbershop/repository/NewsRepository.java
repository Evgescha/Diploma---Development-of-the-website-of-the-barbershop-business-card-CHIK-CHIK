package com.hescha.barbershop.repository;

import com.hescha.barbershop.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News findByName(String name);
}
