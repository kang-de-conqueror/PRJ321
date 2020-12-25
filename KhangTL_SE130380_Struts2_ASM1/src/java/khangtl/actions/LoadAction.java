/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.actions;

import java.util.List;
import khangtl.daos.EmployeesDAO;
import khangtl.dtos.EmployeesDTO;

/**
 *
 * @author Peter
 */
public class LoadAction {

    private List<EmployeesDTO> listEmployee;

    public List<EmployeesDTO> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(List<EmployeesDTO> listEmployee) {
        this.listEmployee = listEmployee;
    }

    public LoadAction() {
    }
    
    public String execute() throws Exception {
        EmployeesDAO dao = new EmployeesDAO();
        listEmployee = dao.getListEmployee();
        return "success";
    }

}
