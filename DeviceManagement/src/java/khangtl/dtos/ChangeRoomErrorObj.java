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
public class ChangeRoomErrorObj implements Serializable {

    private String newRoomError, reasonError;

    public ChangeRoomErrorObj() {
    }

    public String getNewRoomError() {
        return newRoomError;
    }

    public void setNewRoomError(String newRoomError) {
        this.newRoomError = newRoomError;
    }

    public String getReasonError() {
        return reasonError;
    }

    public void setReasonError(String reasonError) {
        this.reasonError = reasonError;
    }

}
