package edu.xubin.bean;

import org.apache.catalina.User;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "invitation")
public class InvitationEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //0=未确认
    //1=接受会议
    //2=拒绝会议
    @Column(name = "accepting", nullable = false)
    private Integer accepting;

    @Column(name = "reason")
    private String reason;

    @Column(name = "userid")
    private String userid;

    @ManyToOne(targetEntity = MeetingEntity.class)
    @JoinColumn(name = "meetingid", referencedColumnName = "meetingid", unique = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private MeetingEntity meeting;

    public InvitationEntity(String userid, MeetingEntity meeting, int accepting) {
        this.userid = userid;
        this.meeting = meeting;
        this.accepting = accepting;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccepting() {
        return accepting;
    }

    public void setAccepting(Integer accepting) {
        this.accepting = accepting;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public MeetingEntity getMeeting() {
        return meeting;
    }

    public void setMeeting(MeetingEntity meeting) {
        this.meeting = meeting;
    }

    public InvitationEntity() {
    }
}
