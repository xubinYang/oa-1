package edu.xubin.dao;

import edu.xubin.bean.TaskEntity;
import edu.xubin.bean.TaskItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskItemDao extends JpaRepository<TaskItemEntity, Integer> {
    TaskItemEntity findByTaskEntityAndUserid(TaskEntity taskEntity, String userid);

    Integer countAllByTaskEntity(TaskEntity taskEntity);

    Integer countByTaskEntityAndDone(TaskEntity taskEntity, Integer done);
}
