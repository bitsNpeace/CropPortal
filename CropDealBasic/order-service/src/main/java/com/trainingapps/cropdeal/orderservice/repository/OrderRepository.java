package com.trainingapps.cropdeal.orderservice.repository;

import com.trainingapps.cropdeal.orderservice.model.Order;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
	
	//Changed Today
	Optional<Order> findByCropId(int cropId);
}
