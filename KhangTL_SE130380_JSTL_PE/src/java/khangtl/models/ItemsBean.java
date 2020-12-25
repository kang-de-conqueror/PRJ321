/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.models;

import java.util.List;
import khangtl.daos.ItemsDAO;
import khangtl.dtos.ItemsDTO;

/**
 *
 * @author Peter
 */
public class ItemsBean {

    private int itemId, quantity;
    private String itemName, description;
    private boolean status;
    private ItemsDAO dao;
    private ItemsDTO dto;
    
    public List<ItemsDTO> getAllItemDisable() throws Exception {
        return dao.getAllItemDisable();
    }
    
    public ItemsDTO getItemById() throws Exception {
        return dao.getItemById(itemId);
    }

    public ItemsBean() {
        dao = new ItemsDAO();
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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

    public ItemsDTO getDto() {
        return dto;
    }

    public void setDto(ItemsDTO dto) {
        this.dto = dto;
    }

}
