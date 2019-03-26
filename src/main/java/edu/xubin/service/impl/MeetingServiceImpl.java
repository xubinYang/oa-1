package edu.xubin.service.impl;

import edu.xubin.bean.MeetingAddressEntity;
import edu.xubin.bean.MeetingEntity;
import edu.xubin.dao.MeetingDao;
import edu.xubin.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService{

    @Autowired
    public MeetingDao meetingDao;

    public void addMeeting(MeetingEntity meetingEntity){
        meetingDao.save(meetingEntity);
    }

//    @Override
//    public MeetingEntity createMeeting(MeetingEntity meetingEntity) {
//        return meetingDao.save(meetingEntity);
//    }

    @Override
    public MeetingEntity findByMeetingid(Integer meetingid) {
        return meetingDao.findByMeetingid(meetingid);
    }

    @Override
    public MeetingEntity addMeeting(String userid, MeetingAddressEntity meetingAddressEntity, String topic, String comment) {
        MeetingEntity meetingEntity = new MeetingEntity(userid, meetingAddressEntity, topic, comment);
        return meetingDao.save(meetingEntity);
    }

    @Override
    public List<MeetingEntity> findMyMeeting(String userid) {
        return meetingDao.findAllByUserid(userid);
    }


}
