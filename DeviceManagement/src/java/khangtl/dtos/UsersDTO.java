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
public class UsersDTO implements Serializable {

    private String username, password, fullname, role;
    private int room;

    public UsersDTO() {
    }

    public UsersDTO(String username, String fullname, String role) {
        this.username = username;
        this.fullname = fullname;
        this.role = role;
    }

    public UsersDTO(String username, int room) {
        this.username = username;
        this.room = room;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
