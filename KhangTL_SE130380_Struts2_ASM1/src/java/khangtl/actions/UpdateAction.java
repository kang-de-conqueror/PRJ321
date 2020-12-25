/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.actions;

import khangtl.daos.EmployeesDAO;
import khangtl.dtos.EmployeesDTO;

/**
 *
 * @author Peter
 */
public class UpdateAction {

    private String id;
    private EmployeesDTO dto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EmployeesDTO getDto() {
        return dto;
    }

    public void setDto(EmployeesDTO dto) {
        this.dto = dto;
    }

    public UpdateAction() {
    }

    public String execute() throws Exception {
        EmployeesDAO dao = new EmployeesDAO();
        dto = dao.getEmployeeByID(id);
        return "success";
    }

}
