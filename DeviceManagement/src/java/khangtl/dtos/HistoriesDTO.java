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
public class HistoriesDTO implements Serializable {

    private int currentRoomID, pastRoomID;
    private String startTime, endTime, fullname, moveTime, moveReason;

    public HistoriesDTO() {
    }

    public HistoriesDTO(int currentRoomID, int pastRoomID, String startTime, String endTime, String fullname, String moveTime, String moveReason) {
        this.currentRoomID = currentRoomID;
        this.pastRoomID = pastRoomID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fullname = fullname;
        this.moveTime = moveTime;
        this.moveReason = moveReason;
    }

    public int getCurrentRoomID() {
        return currentRoomID;
    }

    public void setCurrentRoomID(int currentRoomID) {
        this.currentRoomID = currentRoomID;
    }

    public int getPastRoomID() {
        return pastRoomID;
    }

    public void setPastRoomID(int pastRoomID) {
        this.pastRoomID = pastRoomID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMoveTime() {
        return moveTime;
    }

    public void setMoveTime(String moveTime) {
        this.moveTime = moveTime;
    }

    public String getMoveReason() {
        return moveReason;
    }

    public void setMoveReason(String moveReason) {
        this.moveReason = moveReason;
    }

}
