package edu.xubin.service;

import edu.xubin.bean.MeetingAddressEntity;
import edu.xubin.bean.MeetingEntity;

import java.util.List;

public interface MeetingService {
//    MeetingEntity createMeeting(MeetingEntity meetingEntity);

    MeetingEntity findByMeetingid(Integer meetingid);

    MeetingEntity addMeeting(String userid, MeetingAddressEntity meetingAddressEntity, String topic, String comment);

    List<MeetingEntity> findMyMeeting(String userid);
}
