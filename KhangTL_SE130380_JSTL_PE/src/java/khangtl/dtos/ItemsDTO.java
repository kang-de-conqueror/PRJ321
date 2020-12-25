/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.dtos;

import java.io.Serializable;

/**
 *
 * @author Peter
 */
public class ItemsDTO implements Serializable {

    private int itemID, quantity;
    private String itemName, description;
    private boolean status;

    public ItemsDTO() {
    }

    public ItemsDTO(int itemID, int quantity, String itemName, String description) {
        this.itemID = itemID;
        this.quantity = quantity;
        this.itemName = itemName;
        this.description = description;
    }

    public ItemsDTO(int itemID, String itemName, String description) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.description = description;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
