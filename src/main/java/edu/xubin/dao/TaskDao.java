package edu.xubin.dao;

import edu.xubin.bean.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends JpaRepository<TaskEntity, Integer> {
    TaskEntity findByTaskid(Integer taskid);
}
