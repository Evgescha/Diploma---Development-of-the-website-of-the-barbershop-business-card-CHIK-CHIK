package com.hescha.barbershop.repository;

import java.sql.Date;
import java.util.List;

import com.hescha.barbershop.entity.Order;
import com.hescha.barbershop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUser(User user);
	Order findByUserAndDateFromAndDateTo(User user, Date dateFrom,  Date dateTo);
	
	List<Order> findByUserAndIsPaidAndIsEnded(User user, boolean isPaid, boolean isEnded);
}
