package edu.xubin.dao;

import edu.xubin.bean.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingDao extends JpaRepository<MeetingEntity, Integer>{
    MeetingEntity findByMeetingid(Integer meetingid);

    List<MeetingEntity> findAllByUserid(String userid);
}
