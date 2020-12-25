/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.accounts.struts2;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Peter
 */
public class RegisterAction extends ActionSupport {

    private String username, password, confirm, emailID, phoneNumber;

    public String getUsername() {
        return username;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Username can't be blank")
    @StringLengthFieldValidator(type = ValidatorType.FIELD, minLength = "6", maxLength = "20", message = "Username length must be 6 to 20 chars")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @RequiredStringValidator(type = ValidatorType.FIELD, message = "Password can't be blank")
    @StringLengthFieldValidator(type = ValidatorType.FIELD, minLength = "6", maxLength = "20", message = "Password length must be 6 to 20 chars")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    @FieldExpressionValidator(expression = "confirm==password", message = "Confirm must match password")
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getEmailID() {
        return emailID;
    }

    @EmailValidator(type = ValidatorType.FIELD, message = "Your email is invalid")
    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @RegexFieldValidator(type = ValidatorType.FIELD, regex = "\\d{3}-\\d{7}", message = "Your phone number is invalid")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RegisterAction() {
    }

    public String execute() throws Exception {
        return "success";
    }

    @Action(
            value = "/register",
            exceptionMappings = {
                @ExceptionMapping(exception = "java.sql.SQLException", result = "input")
            },
            results = {
                @Result(name = "success", location = "/index.jsp")
                ,
                @Result(name = "input", location = "/insertAnnotation.jsp")
            }
    )
    public String insert() throws Exception {
        return "success";
    }

}
