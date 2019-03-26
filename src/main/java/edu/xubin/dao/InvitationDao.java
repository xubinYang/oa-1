package edu.xubin.dao;

import edu.xubin.bean.InvitationEntity;
import edu.xubin.bean.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitationDao extends JpaRepository<InvitationEntity, Integer> {
    List<InvitationEntity> findAllByMeeting(MeetingEntity meetingEntity);

    InvitationEntity findByUseridAndMeeting(String userid, MeetingEntity meetingEntity);

    Integer countAllByMeeting(MeetingEntity meetingEntity);

    Integer countAllByMeetingAndAcceptingEquals(MeetingEntity meetingEntity, Integer accepting);
}
