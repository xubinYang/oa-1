package edu.xubin.service.impl;

import edu.xubin.bean.TaskEntity;
import edu.xubin.dao.TaskDao;
import edu.xubin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;

    @Override
    public TaskEntity saveTask(TaskEntity taskEntity, int status) {
        taskEntity.setStatus(status);
        return taskDao.save(taskEntity);
    }

    @Override
    public TaskEntity findTask(Integer taskid) {
        return taskDao.findByTaskid(taskid);
    }
}
