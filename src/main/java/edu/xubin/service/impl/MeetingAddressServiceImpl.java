package edu.xubin.service.impl;

import edu.xubin.bean.AddressEntity;
import edu.xubin.bean.MeetingAddressEntity;
import edu.xubin.dao.MeetingAddressDao;
import edu.xubin.service.MeetingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class MeetingAddressServiceImpl implements MeetingAddressService {

    @Autowired
    private MeetingAddressDao meetingAddressDao;

    @Override
    public List<MeetingAddressEntity> findAllByDate(Date meetingDate) {
        return meetingAddressDao.findAllByMeetingDate(meetingDate);
    }

    public MeetingAddressEntity addMeetingAddress(AddressEntity addressEntity, Date meetingDate, Time beginTime, Time endTime){
        MeetingAddressEntity meetingAddressEntity = new MeetingAddressEntity(addressEntity, meetingDate, beginTime, endTime);
        return meetingAddressDao.save(meetingAddressEntity);
    }

}
