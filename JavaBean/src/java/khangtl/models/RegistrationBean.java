/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.models;

import java.io.Serializable;
import khangtl.daos.RegistrationDAO;

/**
 *
 * @author Peter
 */
public class RegistrationBean implements Serializable {

    private String username, password, fullname, role;

    public RegistrationBean() {
    }

    public RegistrationBean(String username, String fullname, String role) {
        this.username = username;
        this.fullname = fullname;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String checkLogin() throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        return dao.checkLogin(username, password);
    }
}
