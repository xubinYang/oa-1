package edu.xubin.util;

import java.sql.Date;
import java.sql.Time;

public class AvailableAddress {

    private Integer addressid;

    private String addressname;

    //1可用，0不可用
    private Integer status;

    private String beginTime;

    private String endTime;

    private Date meetingDate;

    public AvailableAddress() {
    }

    public AvailableAddress(Integer addressid) {
        this.addressid = addressid;
    }

    public AvailableAddress(String beginTime, String endTime, Date meetingDate) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.meetingDate = meetingDate;
    }

    public AvailableAddress(Integer addressid, String addressname, Integer status, String beginTime, String endTime) {
        this.addressid = addressid;
        this.addressname = addressname;
        this.status = status;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public AvailableAddress(Integer addressid, String addressname, Integer status) {
        this.addressid = addressid;
        this.addressname = addressname;
        this.status = status;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public String getAddressname() {
        return addressname;
    }

    public void setAddressname(String addressname) {
        this.addressname = addressname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
