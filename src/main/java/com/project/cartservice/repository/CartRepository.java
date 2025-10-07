package com.project.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cartservice.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	Cart findByUserId(Long userId);
}
