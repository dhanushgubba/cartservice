package com.project.cartservice.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartItem {

    private Long productId;
    private int quantity;
    
    public CartItem() {
    	//default Constructor
    }
    
    public CartItem(Long productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem [productId=" + productId + ", quantity=" + quantity + "]";
    }
}
