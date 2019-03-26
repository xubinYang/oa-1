package edu.xubin.service.impl;

import edu.xubin.bean.AddressEntity;
import edu.xubin.bean.MeetingAddressEntity;
import edu.xubin.bean.MeetingEntity;
import edu.xubin.dao.AddressDao;
import edu.xubin.dao.MeetingAddressDao;
import edu.xubin.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{


    @Autowired
    private AddressDao addressDao;



    public List<AddressEntity> findAll(){
        return addressDao.findAll();
    }

    @Override
    public AddressEntity findByAddressid(Integer addressid) {
        return addressDao.findByAddressid(addressid);
    }

//    public AddressEntity findByMeetingDateAndBeginTimeAndEndTime(Date meetingDate
//            , Time beginTime, Time endTime){
//        AddressEntity addressEntity= meetingAddressDao.findByMeetingDateAndBeginTimeAndEndTime(meetingDate
//                , beginTime, endTime).getAddressEntity();
//
//    }



}
