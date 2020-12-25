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
public class RoomsErrorObj implements Serializable {

    private String roomIDError, roomNameError;

    public RoomsErrorObj() {
    }

    public String getRoomIDError() {
        return roomIDError;
    }

    public void setRoomIDError(String roomIDError) {
        this.roomIDError = roomIDError;
    }

    public String getRoomNameError() {
        return roomNameError;
    }

    public void setRoomNameError(String roomNameError) {
        this.roomNameError = roomNameError;
    }
}
