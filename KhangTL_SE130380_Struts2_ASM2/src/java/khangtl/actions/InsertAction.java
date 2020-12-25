/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.actions;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import khangtl.daos.BlogsDAO;
import khangtl.dtos.BlogsDTO;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Peter
 */
public class InsertAction extends ActionSupport {

    private static final String ERROR = "error";
    private static final String SUCCESS = "success";
    private static final String RESET = "reset";

    private String id, title, body, datePublish;
    private String buttonName;

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDatePublish() {
        return datePublish;
    }

    public void setDatePublish(String datePublish) {
        this.datePublish = datePublish;
    }

    public InsertAction() {
    }

    @Override
    public void validate() {
        if (buttonName.equals("Insert")) {
            if (!id.matches("\\d+")) {
                addFieldError("id", "Please input a digit");
            }
            if (title.isEmpty()) {
                addFieldError("title", "Title cannot be blank");
            }
            if (body.isEmpty()) {
                addFieldError("body", "Body cannot be blank");
            }
            if (!datePublish.matches("^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$")) {
                addFieldError("datePublish", "Please follow this format: YYYY-MM-DD");
            }
        }
    }

    public String execute() throws Exception {
        if (buttonName.equals("Insert")) {
            String label = ERROR;
            HttpServletRequest request = ServletActionContext.getRequest();
            BlogsDTO dto = new BlogsDTO(Integer.parseInt(id), title, body, datePublish);
            BlogsDAO dao = new BlogsDAO();
            if (dao.insert(dto)) {
                label = SUCCESS;
                request.setAttribute("SUCCESS", "Insert successfully");
            } else {
                label = ERROR;
                request.setAttribute("ERROR", "Insert failed");
            }
            return label;
        }
        if (buttonName.equals("Reset")) {
            this.setId("");
            this.setTitle("");
            this.setBody("");
            this.setDatePublish("");
            return RESET;
        }
        return super.execute();
    }

}
