/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.actions;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import khangtl.daos.BlogsDAO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Peter
 */
public class DeleteAction extends ActionSupport {
    
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";
    
    private String id;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void validate() {
        if (!id.matches("\\d+")) {
            addFieldError("id", "Please input a digit");
        }
    }
    
    
    
    public String execute() throws Exception {
        String label = ERROR;
        HttpServletRequest request = ServletActionContext.getRequest();
        BlogsDAO dao = new BlogsDAO();
        if (dao.delete(Integer.parseInt(id))) {
            label = SUCCESS;
            request.setAttribute("SUCCESS", "Delete successfully");
        } else {
            request.setAttribute("ERROR", "Delete failed");
        }
        return label;
    }
    
}
