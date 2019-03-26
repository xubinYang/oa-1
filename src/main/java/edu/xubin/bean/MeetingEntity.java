package edu.xubin.bean;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "meeting")
public class MeetingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meetingid")
    private Integer meetingid;


    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "comment", nullable = false)
    private String comment;


    @Column(name = "type")
    private Integer type;

    @Column(name = "userid")
    private String userid;

    @OneToOne(targetEntity = MeetingAddressEntity.class)
    @JoinColumn(name = "meeting_addressid", referencedColumnName = "meeting_addressid", unique = true)
    @Cascade(org.hibernate.annotations.CascadeType.REFRESH)
    private MeetingAddressEntity meetingAddressEntity;

    public MeetingEntity() {
    }

    public MeetingEntity(String userid, MeetingAddressEntity meetingAddressEntity, String topic, String comment) {
        this.userid = userid;
        this.meetingAddressEntity = meetingAddressEntity;
        this.topic = topic;
        this.comment = comment;
    }

    public MeetingEntity(String userid, String topic, String comment) {
        this.userid = userid;
        this.topic = topic;
        this.comment = comment;

    }

    public MeetingEntity(String topic, String comment) {
        this.topic = topic;
        this.comment = comment;
    }

    public Integer getMeetingid() {
        return meetingid;
    }

    public void setMeetingid(Integer meetingid) {
        this.meetingid = meetingid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public MeetingAddressEntity getMeetingAddressEntity() {
        return meetingAddressEntity;
    }

    public void setMeetingAddressEntity(MeetingAddressEntity meetingAddressEntity) {
        this.meetingAddressEntity = meetingAddressEntity;
    }
}

