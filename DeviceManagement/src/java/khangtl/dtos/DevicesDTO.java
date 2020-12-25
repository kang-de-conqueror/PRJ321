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
public class DevicesDTO implements Serializable {

    private int deviceID, repairID;
    private String deviceName, deviceDescription, deviceType, deviceState, deviceImage, buyDate, guaranteeDuration, roomName;

    public DevicesDTO() {
    }

    public DevicesDTO(int deviceID, String deviceName, String deviceDescription, String deviceType, String deviceState, String deviceImage, String buyDate, String guaranteeDuration) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.deviceDescription = deviceDescription;
        this.deviceType = deviceType;
        this.deviceState = deviceState;
        this.deviceImage = deviceImage;
        this.guaranteeDuration = guaranteeDuration;
        this.buyDate = buyDate;
    }

    public DevicesDTO(String deviceName, String deviceDescription, String deviceType, String deviceState, String deviceImage, String buyDate, String guaranteeDuration) {
        this.deviceName = deviceName;
        this.deviceDescription = deviceDescription;
        this.deviceType = deviceType;
        this.deviceState = deviceState;
        this.deviceImage = deviceImage;
        this.guaranteeDuration = guaranteeDuration;
        this.buyDate = buyDate;
    }

    public DevicesDTO(int deviceID, String deviceName, String deviceDescription, String deviceType, String deviceState, String deviceImage, String buyDate, String guaranteeDuration, String roomName) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.deviceDescription = deviceDescription;
        this.deviceType = deviceType;
        this.deviceState = deviceState;
        this.deviceImage = deviceImage;
        this.buyDate = buyDate;
        this.guaranteeDuration = guaranteeDuration;
        this.roomName = roomName;
    }

    public DevicesDTO(int deviceID, String deviceName) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
    }

    public DevicesDTO(int deviceID, int repairID, String deviceName, String deviceDescription, String deviceType, String deviceState, String deviceImage, String buyDate, String guaranteeDuration) {
        this.deviceID = deviceID;
        this.repairID = repairID;
        this.deviceName = deviceName;
        this.deviceDescription = deviceDescription;
        this.deviceType = deviceType;
        this.deviceState = deviceState;
        this.deviceImage = deviceImage;
        this.buyDate = buyDate;
        this.guaranteeDuration = guaranteeDuration;
        this.roomName = roomName;
    }

    public int getRepairID() {
        return repairID;
    }

    public void setRepairID(int repairID) {
        this.repairID = repairID;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(String deviceState) {
        this.deviceState = deviceState;
    }

    public String getDeviceImage() {
        return deviceImage;
    }

    public void setDeviceImage(String deviceImage) {
        this.deviceImage = deviceImage;
    }

    public String getGuaranteeDuration() {
        return guaranteeDuration;
    }

    public void setGuaranteeDuration(String guaranteeDuration) {
        this.guaranteeDuration = guaranteeDuration;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
