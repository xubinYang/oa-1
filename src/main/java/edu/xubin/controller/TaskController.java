package edu.xubin.controller;

import edu.xubin.bean.TaskEntity;
import edu.xubin.bean.TaskItemEntity;
import edu.xubin.bean.User;
import edu.xubin.service.TaskItemService;
import edu.xubin.service.TaskService;
import edu.xubin.service.UserService;
import edu.xubin.util.Question;
import edu.xubin.util.WechatTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes(value={"userlogin", "userString", "saveTask"})
@RequestMapping("/")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskItemService taskItemService;
    @Autowired
    private UserService userService;

    //saveOrSub=0是保存
    //saveOrSub=1是提交
    @RequestMapping("/saveTask")
    public ModelAndView saveTask(TaskEntity taskEntity, String deadlineString, String userString, int saveOrSub, Model model, HttpSession session){
        deadlineString = deadlineString.replace("T", " ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date deadline = new Date();
        try {
            deadline= sdf.parse(deadlineString);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //草稿状态
        User user = (User)session.getAttribute("userlogin");
        taskEntity.setUserid(user.getUserid());
        taskEntity.setDeadline(deadline);
        taskService.saveTask(taskEntity, 1);
        ModelAndView mav = new ModelAndView();
        mav.addObject("task", taskEntity);
        model.addAttribute("userString", userString);
        model.addAttribute("saveTask", taskEntity);
        if(saveOrSub == 0){
            mav.setViewName("index");
        }
        else {
            mav.setViewName("taskForm");
        }
        return mav;
    }

    @RequestMapping("/taskform")
    public ModelAndView taskForm(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("taskForm");
        return mav;
    }

//    @RequestMapping("/addTask")
//    public TaskEntity addTask(TaskEntity taskEntity, HttpSession session){
//        //更新task问题和问题描述
//        TaskEntity saveTask = (TaskEntity) session.getAttribute("saveTask");
//        saveTask.setQuestion(taskEntity.getQuestion());
//        saveTask.setQuestionDescribe(taskEntity.getQuestionDescribe());
//        TaskEntity task = taskService.saveTask(saveTask, 2);
//        //发送通知给需要完成任务的人
//        String userString = (String)session.getAttribute("userString");
//        String[] users = userString.split(",");
//        List<User> userList = new ArrayList<>();
//        for(String user : users){
//            User userEntity = userService.findByName(user);
//            userList.add(userEntity);
//            TaskItemEntity taskItemEntity = new TaskItemEntity(user, task);
//            taskItemService.saveTaskItem(taskItemEntity, 0);
//        }
//        wechatTaskSendCard(userList, task);
//        return task;
//    }

    @RequestMapping("/showItem")
    public ModelAndView success(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("success");
        return mav;
    }

    @RequestMapping("/showTaskItem")
    public ModelAndView showTaskItem(@RequestParam("code") String code, Integer taskid, Model model){
        User user = userService.findUserByUserid(WechatTool.getWechatUseridByCode(code));
        model.addAttribute("userlogin", user);
        TaskEntity taskEntity =taskService.findTask(taskid);
        String[] questions = taskEntity.getQuestion().split("&");
        String[] questionDescribes = taskEntity.getQuestionDescribe().split("&");
        List<Question> questionList = new ArrayList<>();
        for (int i=0; i<questions.length; i++){
            questionList.add(new Question(questions[i], questionDescribes[i]));
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("showTaskItem");
        mav.addObject("task",taskEntity);
        mav.addObject("questions", questionList);
        return mav;
    }

    @RequestMapping("/addTaskItem")
    public ModelAndView addTaskItem(TaskItemEntity taskItemResult, HttpSession session){
        User user = (User)session.getAttribute("userlogin");
        taskItemResult.setUserid(user.getUserid());
        TaskEntity taskEntity = taskService.findTask(taskItemResult.getTaskEntity().getTaskid());
        TaskItemEntity taskItem = taskItemService.findTaskItem(taskEntity, user.getUserid());
        taskItem.setValue(taskItemResult.getValue());
        taskItemService.saveTaskItem(taskItemResult, 2);
        //发送任务完成文本
        String touser = taskItem.getTaskEntity().getUserid();
        String content = userService.findUserByUserid(user.getUserid()) + "已完成" + taskItem.getTaskEntity().getTaskname()
                + ",需完成人数：" + taskItemService.countTaskItemByTask(taskItem.getTaskEntity(), true)
                + "，已完成人数：" + taskItemService.countTaskItemByTask(taskItem.getTaskEntity(), false)
                + "，未完成人数：" + (taskItemService.countTaskItemByTask(taskItem.getTaskEntity(), true)-taskItemService.countTaskItemByTask(taskItem.getTaskEntity(), false));
        Map<String, Object> map = WechatTool.wechatContentText(0, content);
        WechatTool.wechatMsg(touser, null, null, "text", map, null);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("");
        mav.addObject("taskitem", taskItem);
        return mav;
    }

    private void wechatTaskSendCard(List<User> userEntities, TaskEntity taskEntity){
        StringBuffer touser = new StringBuffer();
        for(int i=0; i<userEntities.size(); i++){
            if (i!=0){
                touser.append("|");
            }
            touser.append(userEntities.get(i).getUserid());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date time = null;
        try {
            time = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String title = "您有一个新任务";
        String description = WechatTool.getWechatDescriptionFormat(time.toString(),"任务创建人：" + userService.findUserByUserid(taskEntity.getUserid()).getName()
                ,"完成截止时间："+taskEntity.getDeadline().toString(),taskEntity.getTaskname());
        String url = WechatTool.getWechatCodeFormat("%2fshowTaskItem%3ftaskid%3d" + taskEntity.getTaskid());
        String btntxt = "点击查看";
        Map<String, Object> map = WechatTool.wechatContentText(1, title, description, url, btntxt);
        WechatTool.wechatMsg(touser.toString(), null, null, "textcard", map, null);
    }
}
