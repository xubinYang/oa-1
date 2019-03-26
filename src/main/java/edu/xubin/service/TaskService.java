package edu.xubin.service;

import edu.xubin.bean.TaskEntity;

public interface TaskService {

    TaskEntity saveTask(TaskEntity taskEntity, int status);

    TaskEntity findTask(Integer taskid);
}
