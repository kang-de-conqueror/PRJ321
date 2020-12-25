/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import khangtl.daos.EmployeesDAO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Peter
 */
public class LoginAction extends ActionSupport {

    private static final String ERROR = "error";
    private static final String SUCCESS = "success";

    private String username, password;

    public LoginAction() {
    }

    @Override
    public void validate() {
        if (username.isEmpty()) {
            addFieldError("username", "Username cannot be blank");
        }
        if (password.isEmpty()) {
            addFieldError("password", "Password cannnot be blank");
        }
    }

    public String execute() throws Exception {
        String label = ERROR;
        EmployeesDAO dao = new EmployeesDAO();
        boolean check = dao.checkLogin(username, password);
        HttpServletRequest request = ServletActionContext.getRequest();
        if (!check) {
            request.setAttribute("ERROR", "Invalid username or password");
        } else {
            Map session = ActionContext.getContext().getSession();
            session.put("USER", username);
            label = SUCCESS;
        }

        return label;
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

}
