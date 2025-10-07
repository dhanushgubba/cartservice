package com.project.cartservice.controller;

import com.project.cartservice.model.Cart;
import com.project.cartservice.model.CartItem;
import com.project.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000") // allow React frontend to call
public class CartController {

    @Autowired
    private CartService cartService;

    // Get cart by userId
    @GetMapping("/{userId}")
    public Cart getCartByUserId(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    // Add item to cart
    @PostMapping("/{userId}/add")
    public Cart addItemToCart(
            @PathVariable Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return cartService.addItemToCart(userId, productId, quantity);
    }

    // Remove item from cart
    @DeleteMapping("/{userId}/remove")
    public Cart removeItemFromCart(
            @PathVariable Long userId,
            @RequestParam Long productId) {
        return cartService.removeItemFromCart(userId, productId);
    }

    // Clear all items in cart
    @DeleteMapping("/{userId}/clear")
    public Cart clearCart(@PathVariable Long userId) {
        return cartService.clearCart(userId);
    }
    
    @PutMapping("/{userId}/update")
    public Cart updateCart(@PathVariable Long userId,@RequestBody CartItem cartitem) {
    	return cartService.updateCart(userId,cartitem.getProductId(), cartitem.getQuantity());
    }
}
