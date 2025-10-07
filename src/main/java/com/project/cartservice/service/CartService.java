package com.project.cartservice.service;

import com.project.cartservice.model.Cart;

public interface CartService {
	Cart getCartByUserId(Long userId);
    Cart addItemToCart(Long userId, Long productId, int quantity);
    Cart removeItemFromCart(Long userId, Long productId);
    Cart clearCart(Long userId);
    Cart updateCart(Long userId, Long productId,int quantity);
}
