/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.dtos;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Peter
 */
public class CartDTO implements Serializable {
    
    private String customerName;
    private HashMap<String, BookDTO> cart;
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public HashMap<String, BookDTO> getCart() {
        return cart;
    }
    
    public CartDTO() {
        this.customerName = "Guest";
    }
    
    public CartDTO(String customerName) {
        this.customerName = customerName;
        this.cart = new HashMap<>();
    }
    
    public void addToCart(BookDTO dto) throws Exception {
        if (this.cart.containsKey(dto.getId())) {
            int quantity = this.cart.get(dto.getId()).getQuantity() + dto.getQuantity();
            dto.setQuantity(quantity);
        }
        this.cart.put(dto.getId(), dto);
    }
    
    public boolean delete(String id) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
            return true;
        }
        return false;
    }
    
    public int getTotal() {
        int result = 0;
        for(BookDTO dto : this.cart.values()) {
            result += dto.getPrice() * dto.getQuantity();
        }
        return result;
    }
    
    public boolean update(String id, int quantity) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.get(id).setQuantity(quantity);
            return true;
        }
        return false;
    }
}
