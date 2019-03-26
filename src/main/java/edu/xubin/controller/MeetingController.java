package edu.xubin.controller;

import edu.xubin.bean.*;
import edu.xubin.service.*;
import edu.xubin.util.AvailableAddress;
import edu.xubin.util.WechatTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes(value = {"saveMeeting", "userString", "userlogin"})
@RequestMapping("/")
public class MeetingController {

    @Autowired
    private UserService userService;
    @Autowired
    private MeetingService meetingService;
    @Autowired
    private InvitationService invitationService;
    @Autowired
    private MeetingAddressService meetingAddressService;
    @Autowired
    private AddressService addressService;


    @RequestMapping("/meeting")
    public ModelAndView meeting() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("register");
        mav.addObject("users", userService.findAllUser());
        return mav;
    }

    @RequestMapping("/chooseTime")
    public ModelAndView saveMeetingSession(AvailableAddress availableAddress, HttpServletRequest request, Model model) {
        String topic = request.getParameter("topic");
        String comment = request.getParameter("comment");
        String userString = request.getParameter("users");
        MeetingEntity meetingEntity = new MeetingEntity(topic, comment);
        model.addAttribute("saveMeeting", meetingEntity);
        model.addAttribute("userString", userString);
        ModelAndView mav = new ModelAndView();
        mav.addObject("meeting", meetingEntity);
        mav.setViewName("createTime");
        return mav;
    }

    //先占着会议地址生成一个会议使用情况记录
    //再生成会议
    //再发起会议参与人员邀请
    @RequestMapping("/addMeeting")
    public ModelAndView addMeeting(AvailableAddress availableAddress, HttpServletRequest request, HttpSession session) {
        //处理前端传回来的格式问题
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm");
        Date stringToTime = null;
        Time beginTime = null;
        Time endTime = null;
        Date meetingDate = null;
        ModelAndView mav = new ModelAndView();
        meetingDate = availableAddress.getMeetingDate();
        meetingDate = new Date();
        try {
            //转换时间格式
            stringToTime = sdftime.parse(availableAddress.getBeginTime());
            beginTime = new Time(stringToTime.getTime());
            stringToTime = sdftime.parse(availableAddress.getEndTime());
            endTime = new Time(stringToTime.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        AddressEntity addressEntity = addressService.findByAddressid(availableAddress.getAddressid());
        //通过前端传值生成会议地址使用，先占着会议地址
        MeetingAddressEntity meetingAddressEntity = meetingAddressService.addMeetingAddress(addressEntity, meetingDate, beginTime, endTime);
        //获取前一个页面保存的会议参数
        MeetingEntity saveMeeting = (MeetingEntity) session.getAttribute("saveMeeting");
        User user = (User) session.getAttribute("userlogin");
        String userString = (String) session.getAttribute("userString");
        String users[] = userString.split(",");
        //创建会议
        MeetingEntity meetingEntity = meetingService.addMeeting(user.getUserid(), meetingAddressEntity, saveMeeting.getTopic(), saveMeeting.getComment());
        //发起会议邀请
        for (int i = 0; i < users.length; i++) {
//            invitationService.addInvitation(userService.findByUsername(users[i]), meetingEntity, 0);
        }
        //企业微信发出邀请卡片消息
        wechatMeetingSendCard(users, meetingEntity);
        mav.setViewName("successMeeting");
        mav.addObject("meeting", meetingEntity);
        mav.addObject("userString", userString);
        return mav;
    }

    @RequestMapping("/forward")
    public ModelAndView forwardMeeting(Integer meetingid) {
        List<InvitationEntity> invitationEntityList = invitationService.findAllByMeeting(meetingService.findByMeetingid(meetingid));
        List<User> selectedUser = null;
        for (InvitationEntity invitationEntity : invitationEntityList) {
            selectedUser.add(userService.findUserByUserid(invitationEntity.getUserid()));
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("selectedUser", selectedUser);
        mav.addObject("users", userService.findAllUser());
        mav.setViewName("");
        return null;
    }

    //接受或拒绝邀请
    @RequestMapping("/accepting")
    public ModelAndView accepting(InvitationEntity invitationRequest, HttpSession session) {
        User user = (User) session.getAttribute("userlogin");
        InvitationEntity invitationEntity = invitationService.findByUserAndMeeting(user.getUserid(), invitationRequest.getMeeting());
        invitationEntity.setAccepting(invitationRequest.getAccepting());
        if (invitationEntity.getAccepting() == 2) {
            invitationEntity.setReason(invitationRequest.getReason());
        }
        invitationEntity = invitationService.updateInvitation(invitationEntity);
        //企业微信文本格式
        String content = null;
        if(invitationEntity.getAccepting() == 1){
            content = userService.findUserByUserid(invitationEntity.getUserid()) + "接受会议邀请"
                    + "\n总共会议人数为："+ invitationService.countAllByMeeting(invitationEntity.getMeeting())
                    +"\n接受人数为"+ invitationService.countAllByMeetingAndAccepting(invitationEntity.getMeeting(), 1)
                    + "，拒绝人数为:" + invitationService.countAllByMeetingAndAccepting(invitationEntity.getMeeting(), 2)
                    +"，未确认人数为：" + invitationService.countAllByMeetingAndAccepting(invitationEntity.getMeeting(), 0);;
        }
        else if (invitationEntity.getAccepting() == 2){
            content = userService.findUserByUserid(invitationEntity.getUserid()) + "拒绝会议邀请" + "，拒绝原因为：" +invitationEntity.getReason()
                    + "\n总共会议人数为："+ invitationService.countAllByMeeting(invitationEntity.getMeeting())
                    +"\n接受人数为"+ invitationService.countAllByMeetingAndAccepting(invitationEntity.getMeeting(), 1)
                    + "，拒绝人数为:" + invitationService.countAllByMeetingAndAccepting(invitationEntity.getMeeting(), 2)
                    +"，未确认人数为：" + invitationService.countAllByMeetingAndAccepting(invitationEntity.getMeeting(), 0);

        }
        Map<String, Object> map = WechatTool.wechatContentText(0, content);
        WechatTool.wechatMsg(invitationEntity.getUserid(), null, null, "text", map, null);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("success");
        mav.addObject("invitation", invitationEntity);
        return mav;
    }

    //显示邀请详情
    @RequestMapping("/invitationShow")
    public ModelAndView invitationShow(@RequestParam("code") String code, Integer meetingid, Model model) {
        User userEntity = userService.findUserByUserid(WechatTool.getWechatUseridByCode(code));
        model.addAttribute("userlogin", userEntity);
        MeetingEntity meetingEntity = meetingService.findByMeetingid(meetingid);
        ModelAndView mav = new ModelAndView();
        List<User> userEntities = invitationService.findAllInvitationUserByMeeting(meetingEntity);
        String userString = null;
        for (User user : userEntities) {
            userString += user.getName();
        }
        mav.setViewName("invitationItem");
        mav.addObject("meeting", meetingEntity);
        mav.addObject("userString", userString);
        return mav;
    }

    @RequestMapping("/myMeeting")
    public ModelAndView myMeeting(HttpSession session){
        User user = (User) session.getAttribute("userlogin");
        List<MeetingEntity> meetingEntities = meetingService.findMyMeeting(user.getUserid());

        ModelAndView mav = new ModelAndView();
        mav.addObject("meetings",meetingEntities);
        return mav;
    }

    private void wechatMeetingSendCard(String[] users, MeetingEntity meetingEntity) {
        StringBuilder touser = new StringBuilder();
        for (int i = 0; i < users.length; i++) {
            if (i != 0) {
                touser.append("|");
            }
//            touser.append(userService.findByName(users[i]).getUsercode());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date time = null;
        try {
            time = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String title = meetingEntity.getTopic();
        String description = WechatTool.getWechatDescriptionFormat(time.toString(), userService.findUserByUserid(meetingEntity.getUserid()).getName() + " 邀请你参加会议"
                , "会议时间:" + meetingEntity.getMeetingAddressEntity().getMeetingDate() + "从" + meetingEntity.getMeetingAddressEntity().getBeginTime() + "到" + meetingEntity.getMeetingAddressEntity().getEndTime()
                , "会议地点:" + meetingEntity.getMeetingAddressEntity().getAddressEntity().getAddressname());
        String url = WechatTool.getWechatCodeFormat("%2finvitationShow%3fmeetingid%3d" + meetingEntity.getMeetingid());
        String btntxt = "点击查看";
        Map<String, Object> map = WechatTool.wechatContentText(1, title, description, url, btntxt);
        WechatTool.wechatMsg(touser.toString(), null, null, "textcard", map, null);
    }

}
