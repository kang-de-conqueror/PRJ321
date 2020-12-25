/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.dtos;

import java.io.Serializable;

/**
 *
 * @author Peter
 */
public class UsedErrorObj implements Serializable {

    private String reasonError;

    public UsedErrorObj() {
    }

    public String getReasonError() {
        return reasonError;
    }

    public void setReasonError(String reasonError) {
        this.reasonError = reasonError;
    }
}
