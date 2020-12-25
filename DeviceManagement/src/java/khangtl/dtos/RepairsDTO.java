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
public class RepairsDTO implements Serializable {

    private int repairID;
    private boolean canceled;
    private String deviceName, requestDescription, userRequest, userRepair, requestTime, startTime, endTime, repairDescription, repairResult;

    public RepairsDTO() {
    }

    public RepairsDTO(int repairID, String deviceName, String requestDescription, String userRequest, String requestTime) {
        this.repairID = repairID;
        this.deviceName = deviceName;
        this.requestDescription = requestDescription;
        this.userRequest = userRequest;
        this.requestTime = requestTime;
    }

    public RepairsDTO(int repairID, String deviceName, String requestDescription, String userRequest, String requestTime, String startTime, String endTime) {
        this.repairID = repairID;
        this.deviceName = deviceName;
        this.requestDescription = requestDescription;
        this.userRequest = userRequest;
        this.requestTime = requestTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public RepairsDTO(int repairID, boolean canceled, String deviceName, String requestDescription, String userRequest, String requestTime, String startTime, String endTime) {
        this.repairID = repairID;
        this.canceled = canceled;
        this.deviceName = deviceName;
        this.requestDescription = requestDescription;
        this.userRequest = userRequest;
        this.requestTime = requestTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public RepairsDTO(int repairID, String requestDescription, String userRequest, String userRepair, String requestTime, String startTime, String endTime, String repairDescription, String repairResult, boolean canceled) {
        this.repairID = repairID;
        this.requestDescription = requestDescription;
        this.userRequest = userRequest;
        this.userRepair = userRepair;
        this.requestTime = requestTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.repairDescription = repairDescription;
        this.repairResult = repairResult;
        this.canceled = canceled;
    }

    public RepairsDTO(int repairID, boolean canceled, String deviceName, String requestDescription, String userRequest, String userRepair, String requestTime, String startTime, String endTime, String repairDescription, String repairResult) {
        this.repairID = repairID;
        this.canceled = canceled;
        this.deviceName = deviceName;
        this.requestDescription = requestDescription;
        this.userRequest = userRequest;
        this.userRepair = userRepair;
        this.requestTime = requestTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.repairDescription = repairDescription;
        this.repairResult = repairResult;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public int getRepairID() {
        return repairID;
    }

    public void setRepairID(int repairID) {
        this.repairID = repairID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(String userRequest) {
        this.userRequest = userRequest;
    }

    public String getUserRepair() {
        return userRepair;
    }

    public void setUserRepair(String userRepair) {
        this.userRepair = userRepair;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
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

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public String getRepairResult() {
        return repairResult;
    }

    public void setRepairResult(String repairResult) {
        this.repairResult = repairResult;
    }
}
