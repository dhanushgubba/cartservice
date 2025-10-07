package com.project.cartservice.service;

import com.project.cartservice.model.Cart;
import com.project.cartservice.model.CartItem;
import com.project.cartservice.repository.CartRepository;
import com.project.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Cart addItemToCart(Long userId, Long productId, int quantity) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setItems(new ArrayList<>());
        }

        // check if product already exists in cart
        boolean itemExists = false;
        for (CartItem item : cart.getItems()) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity); // increase quantity
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            cart.getItems().add(new CartItem(productId, quantity));
        }

        return cartRepository.save(cart);
    }

    @Override
    public Cart removeItemFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            cart.getItems().removeIf(item -> item.getProductId().equals(productId));
            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public Cart clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            cart.getItems().clear();
            return cartRepository.save(cart);
        }
        return null;
    }

	@Override
	public Cart updateCart(Long userId, Long productId, int quantity) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findByUserId(userId);
		if(cart != null) {
			for(CartItem item : cart.getItems()) {
				if(item.getProductId().equals(productId)) {
					if(quantity<=0) {
						cart.getItems().remove(item);
					}else {
						item.setQuantity(quantity);
					}
					return cartRepository.save(cart);
				}
			}
		}
		return null;
	}
}
