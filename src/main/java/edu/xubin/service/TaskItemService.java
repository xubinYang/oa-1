package edu.xubin.service;

import edu.xubin.bean.TaskEntity;
import edu.xubin.bean.TaskItemEntity;

public interface TaskItemService {

    TaskItemEntity saveTaskItem(TaskItemEntity taskItemEntity, Integer done);

    TaskItemEntity findTaskItem(TaskEntity taskEntity, String userid);

    int countTaskItemByTask(TaskEntity taskEntity, boolean all);
}
