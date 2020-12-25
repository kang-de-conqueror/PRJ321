/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.models;

import java.io.Serializable;
import java.util.List;
import khangtl.daos.UsersDAO;
import khangtl.dtos.UsersDTO;

/**
 *
 * @author Peter
 */
public class UsersBean implements Serializable {

    private String username, password, fullname;
    private UsersDAO dao;
    private UsersDTO dto;

    public UsersDTO checkLogin() throws Exception {
        return dao.checkLogin(username, password);
    }
    
    public List<UsersDTO> findByLikeName() throws Exception {
        return dao.findByLikeName(fullname);
    }
    
    public int findUserRoom() throws Exception {
        return dao.findUserRoom(username);
    }
    
    public boolean delete() throws Exception {
        return dao.delete(username);
    }
    
    public UsersDTO findByPrimaryKey() throws Exception {
        return dao.findByPrimaryKey(username);
    }
    
    public boolean update() throws Exception {
        return dao.update(dto);
    }
    
    public boolean insert() throws Exception {
        return dao.insert(dto);
    }

    public UsersBean() {
        dao = new UsersDAO();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public UsersDTO getDto() {
        return dto;
    }

    public void setDto(UsersDTO dto) {
        this.dto = dto;
    }
}
