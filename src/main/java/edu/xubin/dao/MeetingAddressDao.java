package edu.xubin.dao;

import edu.xubin.bean.MeetingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface MeetingAddressDao extends JpaRepository<MeetingAddressEntity, Integer>{
//    MeetingAddressEntity findByMeetingDateAndBeginTimeAndEndTime(Date MeetingDate, Time beginTime, Time endTime);

    List<MeetingAddressEntity> findAllByMeetingDate(Date meetingDate);
}
