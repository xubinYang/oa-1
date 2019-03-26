package edu.xubin.service;

import edu.xubin.bean.AddressEntity;
import edu.xubin.bean.MeetingAddressEntity;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface MeetingAddressService {
    List<MeetingAddressEntity> findAllByDate(Date meetingDate);

    MeetingAddressEntity addMeetingAddress(AddressEntity addressEntity, Date meetingDate, Time beginTime, Time endTime);
}
