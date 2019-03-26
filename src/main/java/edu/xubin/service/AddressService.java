package edu.xubin.service;

import edu.xubin.bean.AddressEntity;
import edu.xubin.bean.MeetingAddressEntity;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface AddressService {
//    AddressEntity findByMeetingDateAndBeginTimeAndEndTime(Date meetingDate
//            , Time beginTime, Time endTime);

    List<AddressEntity> findAll();

    AddressEntity findByAddressid(Integer addressid);
}
