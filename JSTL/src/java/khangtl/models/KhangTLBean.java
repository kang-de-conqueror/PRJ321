/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.models;

import khangtl.daos.RegistrationDAO;
import java.io.Serializable;
import java.util.List;
import khangtl.dtos.RegistrationDTO;

/**
 *
 * @author Peter
 */
public class KhangTLBean implements Serializable {

    private String fullname;
    private String username, password;
    private RegistrationDAO dao;
    private RegistrationDTO dto;

    public boolean insert() throws Exception {
        return dao.insert(dto);
    }

    public boolean update() throws Exception {
        return dao.update(dto);
    }

    public RegistrationDTO findByPrimaryKey() throws Exception {
        return dao.findByPrimaryKey(username);
    }

    public boolean delete() throws Exception {
        return dao.delete(username);
    }

    public List<RegistrationDTO> findByLikeName() throws Exception {
        return dao.findByLikeName(fullname);
    }

    public String checkLogin() throws Exception {
        return dao.checkLogin(username, password);
    }

    public KhangTLBean() {
        dao = new RegistrationDAO();
    }

    public RegistrationDTO getDto() {
        return dto;
    }

    public void setDto(RegistrationDTO dto) {
        this.dto = dto;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
