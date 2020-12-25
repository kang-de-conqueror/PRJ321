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
public class DevicesErrorObj implements Serializable {

    private int deviceIDError;
    private String deviceNameError, deviceDescriptionError, deviceTypeError, deviceStateError, deviceImageError, buyDateError, guaranteeDurationError;

    public DevicesErrorObj() {
    }

    public int getDeviceIDError() {
        return deviceIDError;
    }

    public void setDeviceIDError(int deviceIDError) {
        this.deviceIDError = deviceIDError;
    }

    public String getDeviceNameError() {
        return deviceNameError;
    }

    public void setDeviceNameError(String deviceNameError) {
        this.deviceNameError = deviceNameError;
    }

    public String getDeviceDescriptionError() {
        return deviceDescriptionError;
    }

    public void setDeviceDescriptionError(String deviceDescriptionError) {
        this.deviceDescriptionError = deviceDescriptionError;
    }

    public String getDeviceTypeError() {
        return deviceTypeError;
    }

    public void setDeviceTypeError(String deviceTypeError) {
        this.deviceTypeError = deviceTypeError;
    }

    public String getDeviceStateError() {
        return deviceStateError;
    }

    public void setDeviceStateError(String deviceStateError) {
        this.deviceStateError = deviceStateError;
    }

    public String getDeviceImageError() {
        return deviceImageError;
    }

    public void setDeviceImageError(String deviceImageError) {
        this.deviceImageError = deviceImageError;
    }

    public String getBuyDateError() {
        return buyDateError;
    }

    public void setBuyDateError(String buyDateError) {
        this.buyDateError = buyDateError;
    }

    public String getGuaranteeDurationError() {
        return guaranteeDurationError;
    }

    public void setGuaranteeDurationError(String guaranteeDurationError) {
        this.guaranteeDurationError = guaranteeDurationError;
    }

}
