package edu.xubin.bean;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

//会议室使用情况表
@Entity
@Table(name = "meeting_address")
public class MeetingAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_addressid")
    private Integer meetingAddressid;

    @Column(name = "meeting_date", nullable = false)
    private Date meetingDate;

    @Column(name = "begin_time", nullable = false)
    private Time beginTime;

    @Column(name = "end_time", nullable = false)
    private Time endTime;

    @Column(name = "status")
    private Integer status;

    @OneToOne(targetEntity = AddressEntity.class)
    @JoinColumn(name = "addressid", referencedColumnName = "addressid", unique = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private AddressEntity addressEntity;

    public Integer getMeetingAddressid() {
        return meetingAddressid;
    }

    public void setMeetingAddressid(Integer meetingAddressid) {
        this.meetingAddressid = meetingAddressid;
    }


    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public Time getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Time beginTime) {
        this.beginTime = beginTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public MeetingAddressEntity() {
    }

    public MeetingAddressEntity(AddressEntity addressEntity, Date meetingDate, Time beginTime, Time endTime) {
        this.addressEntity = addressEntity;
        this.meetingDate = meetingDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }
}
