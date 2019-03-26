package edu.xubin.bean;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task_item")
public class TaskItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_itemid")
    private int task_itemid;

    @Column(name = "userid")
    private String userid;

    @ManyToOne
    @JoinColumn(name = "taskid", referencedColumnName = "taskid", unique = true)
    @Cascade(org.hibernate.annotations.CascadeType.REFRESH)
    private TaskEntity taskEntity;

    @Column(name = "value")
    private String value;

    @Column(name = "updatetime")
    private Date updatetime;

    //0是未完成
    //1是草稿
    //2是已完成
    @Column(name = "done", nullable = false)
    private Integer done;

    public TaskItemEntity(String userid, TaskEntity taskEntity) {
        this.userid = userid;
        this.taskEntity = taskEntity;
    }

    public int getTask_itemid() {
        return task_itemid;
    }

    public void setTask_itemid(int task_itemid) {
        this.task_itemid = task_itemid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public TaskItemEntity() {
    }
}
