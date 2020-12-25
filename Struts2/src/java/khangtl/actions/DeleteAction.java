/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.actions;

import javax.servlet.http.HttpServletRequest;
import khangtl.daos.RegistrationDAO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Peter
 */
public class DeleteAction {

    private static final String ERROR = "error";
    private static final String SUCCESS = "success";

    private String id, lastSearchValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

    public DeleteAction() {
    }

    public String execute() throws Exception {
        String label = ERROR;
        HttpServletRequest request = ServletActionContext.getRequest();
        RegistrationDAO dao = new RegistrationDAO();
        if (dao.delete(id)) {
            label = SUCCESS;
        } else {
            request.setAttribute("ERROR", "Delete failed");
        }
        return label;
    }

}
