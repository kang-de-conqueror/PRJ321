/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.actions;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import khangtl.daos.EmployeesDAO;
import khangtl.dtos.EmployeesDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Peter
 */
public class ConfirmUpdateAction extends ActionSupport {

    private static final String ERROR = "error";
    private static final String SUCCESS = "success";

    private String id, name, address, password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ConfirmUpdateAction() {
    }

    @Override
    public void validate() {
        if (password.length() == 0 || password.length() > 10) {
            addFieldError("password", "Password has length from 0 to 10");
        }
        if (name.length() == 0 || name.length() > 10) {
            addFieldError("name", "name has length from 0 to 10");
        }
        if (address.length() == 0 || address.length() > 50) {
            addFieldError("address", "Address has length from 0 to 50");
        }
    }

    public String execute() throws Exception {
        String label = ERROR;
        HttpServletRequest request = ServletActionContext.getRequest();
        EmployeesDTO dto = new EmployeesDTO(id, name, address);
        dto.setPassword(password);
        EmployeesDAO dao = new EmployeesDAO();
        if (dao.updateEmployeeByID(dto)) {
            label = SUCCESS;
            request.setAttribute("SUCCESS", "Delete successfully");
        }
        return label;
    }

}
