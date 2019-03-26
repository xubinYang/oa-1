package edu.xubin.service.impl;

import edu.xubin.bean.TaskEntity;
import edu.xubin.bean.TaskItemEntity;
import edu.xubin.dao.TaskItemDao;
import edu.xubin.service.TaskItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskItemServiceImpl implements TaskItemService {

    @Autowired
    private TaskItemDao taskItemDao;

    @Override
    public TaskItemEntity saveTaskItem(TaskItemEntity taskItemEntity, Integer done) {
        taskItemEntity.setDone(done);
        return taskItemDao.save(taskItemEntity);
    }

    @Override
    public TaskItemEntity findTaskItem(TaskEntity taskEntity, String userid) {
        return taskItemDao.findByTaskEntityAndUserid(taskEntity, userid);
    }

    @Override
    public int countTaskItemByTask(TaskEntity taskEntity, boolean all) {
        if (all){
            return taskItemDao.countAllByTaskEntity(taskEntity);
        }
        else {
            return taskItemDao.countByTaskEntityAndDone(taskEntity, 2);
        }
    }

}
