/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.models;

import java.util.Date;
import khangtl.daos.ItemsDAO;
import khangtl.dtos.ItemsDTO;

/**
 *
 * @author Peter
 */
public class UsedBeans {

    private int id;
    private String reason;
    private Date time;
    private ItemsDAO dao;
    private ItemsDTO dto;
    
    public boolean repairItem() throws Exception {
        return dao.repairItem(id, reason, time);
    }

    public UsedBeans() {
        dao = new ItemsDAO();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ItemsDTO getDto() {
        return dto;
    }

    public void setDto(ItemsDTO dto) {
        this.dto = dto;
    }

}
