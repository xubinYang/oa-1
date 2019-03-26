package edu.xubin.service.impl;

import edu.xubin.bean.InvitationEntity;
import edu.xubin.bean.MeetingEntity;
import edu.xubin.bean.User;
import edu.xubin.dao.InvitationDao;
import edu.xubin.service.InvitationService;
import edu.xubin.util.WechatService.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class InvitationServiceImpl implements InvitationService{
    @Autowired
    public InvitationDao invitationDao;

    @Override
    public Integer addInvitation(InvitationEntity invitationEntity) {
        return null;
    }

    @Override
    public void addInvitation(String userid, MeetingEntity meetingEntity, Integer accepting) {
        InvitationEntity invitationEntity = new InvitationEntity(userid, meetingEntity, accepting);
        invitationDao.save(invitationEntity);
    }



    public List<InvitationEntity> findAllByMeeting(MeetingEntity meetingEntity){
        return invitationDao.findAllByMeeting(meetingEntity);
    }

    @Override
    public InvitationEntity findByUserAndMeeting(String userid, MeetingEntity meetingEntity) {
        return invitationDao.findByUseridAndMeeting(userid, meetingEntity);
    }

    @Override
    public InvitationEntity updateInvitation(InvitationEntity invitationEntity) {
        return invitationDao.save(invitationEntity);
    }

    @Override
    public List<User> findAllInvitationUserByMeeting(MeetingEntity meetingEntity) {
        List<InvitationEntity> invitationEntityList = invitationDao.findAllByMeeting(meetingEntity);
        List<User> users = new ArrayList<>();
        for (int i=0; i<invitationEntityList.size(); i++){
            users.add(WechatUserService.getUserByUserid(invitationEntityList.get(i).getUserid()));
        }
        return users;
    }

    @Override
    public Integer countAllByMeetingAndAccepting(MeetingEntity meetingEntity, Integer accepting) {
        return invitationDao.countAllByMeetingAndAcceptingEquals(meetingEntity, accepting);
    }

    @Override
    public Integer countAllByMeeting(MeetingEntity meetingEntity) {
        return invitationDao.countAllByMeeting(meetingEntity);
    }


}
