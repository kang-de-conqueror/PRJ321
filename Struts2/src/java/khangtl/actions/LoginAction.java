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
import khangtl.daos.RegistrationDAO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Peter
 */
public class LoginAction extends ActionSupport {

    private static final String ERROR = "error";
    private static final String ADMIN = "admin";
    private static final String USER = "user";

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

    public String execute() throws Exception {
        String label = ERROR;
        RegistrationDAO dao = new RegistrationDAO();
        String role = dao.checkLogin(username, password);
        HttpServletRequest request = ServletActionContext.getRequest();
        if (role.equals("failed")) {
            request.setAttribute("ERROR", "Invalid username or password");
        } else {
            Map session = ActionContext.getContext().getSession();
            session.put("USER", username);
            switch (role) {
                case "admin":
                    label = ADMIN;
                    break;
                case "user":
                    label = USER;
                    break;
                default:
                    request.setAttribute("ERROR", "Your role is invalid");
                    break;
            }
        }

        return label;
    }
}
