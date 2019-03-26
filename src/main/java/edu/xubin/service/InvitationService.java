package edu.xubin.service;

import edu.xubin.bean.InvitationEntity;
import edu.xubin.bean.MeetingEntity;
import edu.xubin.bean.User;

import java.util.List;

public interface InvitationService {
    Integer addInvitation(InvitationEntity invitationEntity);

    void addInvitation(String userid, MeetingEntity meetingEntity, Integer accepting);

    List<InvitationEntity> findAllByMeeting(MeetingEntity meetingEntity);

    InvitationEntity findByUserAndMeeting(String userid, MeetingEntity meetingEntity);

    InvitationEntity updateInvitation(InvitationEntity invitationEntity);

    List<User> findAllInvitationUserByMeeting(MeetingEntity meetingEntity);

    Integer countAllByMeetingAndAccepting(MeetingEntity meetingEntity, Integer accepting);

    Integer countAllByMeeting(MeetingEntity meetingEntity);
}
