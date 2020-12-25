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
public class UpdatesDTO implements Serializable {

    private String username, updateContent;

    public UpdatesDTO() {
    }

    public UpdatesDTO(String username, String updateContent) {
        this.username = username;
        this.updateContent = updateContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }
}
