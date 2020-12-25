/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.actions;

import com.opensymphony.xwork2.ActionSupport;
import khangtl.daos.RegistrationDAO;
import khangtl.dtos.RegistrationDTO;

/**
 *
 * @author Peter
 */
public class InsertAction extends ActionSupport {

    private String username, password, confirm, emailID, phoneNumber;

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

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public InsertAction() {
    }

    @Override
    public String execute() throws Exception {
        RegistrationDTO dto = new RegistrationDTO(username, emailID, phoneNumber);
        dto.setPassword(password);
        RegistrationDAO dao = new RegistrationDAO();
        if (dao.insert(dto)) {
            return "success";
        }
        return "error";
    }

}
