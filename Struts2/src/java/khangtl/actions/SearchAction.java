/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.actions;

import java.util.List;
import khangtl.daos.RegistrationDAO;
import khangtl.dtos.RegistrationDTO;

/**
 *
 * @author Peter
 */
public class SearchAction {

    private String searchValue;
    private List<RegistrationDTO> listAccount;

    public List<RegistrationDTO> getListAccount() {
        return listAccount;
    }

    public void setListAccount(List<RegistrationDTO> listAccount) {
        this.listAccount = listAccount;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public SearchAction() {
    }

    public String execute() throws Exception {
        RegistrationDAO dao = new RegistrationDAO();
        listAccount = dao.findByLikeFullname(searchValue);

        return "success";
    }

}
