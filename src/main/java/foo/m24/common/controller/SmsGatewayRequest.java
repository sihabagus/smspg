package foo.m24.common.controller;


import foo.m24.common.model.MoLogs;

import java.util.UUID;

public class SmsGatewayRequest {
    String moData;
    String moId;
    String mobileNo;
    String sms;

    public String getMoData() {
        return moData;
    }

    public void setMoData(String moData) {
        this.moData = moData;
    }

    public String getMoId() {
        return moId;
    }

    public void setMoId(String moId) {
        this.moId = moId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
}
