package com.trainingapps.cropdeal.userMicroService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trainingapps.cropdeal.userMicroService.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	Optional<User> findByEmail(String email);
	User findByFullName(String userName);
}
