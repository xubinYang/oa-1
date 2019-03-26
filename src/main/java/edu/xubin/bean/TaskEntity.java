package edu.xubin.bean;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskid")
    private Integer taskid;

    @Column(name = "taskname", nullable = false)
    private String taskname;

    @Column(name = "task_describe", nullable = false)
    private String taskDescribe;

    @Column(name = "userid")
    private String userid;

    @Column(name = "deadline", nullable = false)
    private Date deadline;

    @Column(name = "question")
    private String question;

    @Column(name = "question_describe")
    private String questionDescribe;

    @Column(name = "type", nullable = false)
    private Integer type;

    //0是删除
    //1是草稿
    //2是已创建，未全部执行完毕
    //3是已全部执行完毕
    @Column(name = "status", nullable = false)
    private Integer status;

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTaskDescribe() {
        return taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionDescribe() {
        return questionDescribe;
    }

    public void setQuestionDescribe(String questionDescribe) {
        this.questionDescribe = questionDescribe;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public TaskEntity() {
    }
}
