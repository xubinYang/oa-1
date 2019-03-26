package edu.xubin.util;

import edu.xubin.bean.MeetingEntity;

public class MeetingStatus {

    private Integer meetingid;
    private String topic;
    private Integer acceptingCount;
    private Integer refuseCount;
    private Integer uncertainCount;

    public Integer getMeetingid() {
        return meetingid;
    }

    public void setMeetingid(Integer meetingid) {
        this.meetingid = meetingid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getAcceptingCount() {
        return acceptingCount;
    }

    public void setAcceptingCount(Integer acceptingCount) {
        this.acceptingCount = acceptingCount;
    }

    public Integer getRefuseCount() {
        return refuseCount;
    }

    public void setRefuseCount(Integer refuseCount) {
        this.refuseCount = refuseCount;
    }

    public Integer getUncertainCount() {
        return uncertainCount;
    }

    public void setUncertainCount(Integer uncertainCount) {
        this.uncertainCount = uncertainCount;
    }
}
