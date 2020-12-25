/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.models;

import java.io.Serializable;
import java.util.List;
import khangtl.daos.DevicesDAO;
import khangtl.dtos.DevicesDTO;

/**
 *
 * @author Peter
 */
public class DevicesBean implements Serializable {

    private int deviceID, repairID;
    private String deviceName, deviceDescription, deviceType, deviceState, deviceImage, buyDate, guaranteeDuration;
    private DevicesDAO dao;
    private DevicesDTO dto;

    public List<DevicesDTO> findByLikeName() throws Exception {
        return dao.findByLikeName(deviceName);
    }

    public boolean delete() throws Exception {
        return dao.delete(deviceID);
    }

    public DevicesDTO findByPrimaryKey() throws Exception {
        return dao.findByPrimaryKey(deviceID);
    }

    public boolean update() throws Exception {
        return dao.update(dto);
    }

    public boolean insert() throws Exception {
        return dao.insert(dto);
    }

    public DevicesBean() {
        dao = new DevicesDAO();
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
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

    public DevicesDTO getDto() {
        return dto;
    }

    public void setDto(DevicesDTO dto) {
        this.dto = dto;
    }

}
