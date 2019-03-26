package edu.xubin.controller;

import edu.xubin.bean.User;
import edu.xubin.service.UserService;
import edu.xubin.util.WechatService.WechatJSSDKUtil;
import edu.xubin.util.WechatTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes(value={"userlogin"})
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/test")
    public ModelAndView test(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("test");
        return mav;
    }

    @RequestMapping("/name")
    public Map<String, Object> name(){
        Map<String, Object> map = new HashMap<>();
        String ticket = WechatTool.getJsapi_ticket();
        map = WechatJSSDKUtil.getSignature("http://233x909e44.iok.la/name", ticket);
        return map;
    }

    @RequestMapping("/showFunction")
    public ModelAndView showFunction(int function){
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userService.findAllUser());
        if(function == 1){
            mav.setViewName("meetingbasic");
        }
        else if (function == 2){

            mav.setViewName("taskcreate");
        }
        return mav;
    }


    //企业微信登录
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("code") String code, Model model) {
        ModelAndView mav = new ModelAndView();
        //调用企业微信的工具类获得usercode
        User user = userService.findUserByUserid(WechatTool.getWechatUseridByCode(code));
        mav.setViewName("test");
        mav.addObject("userlogin", user);
        mav.addObject("users", userService.findAllUser());
        model.addAttribute("userlogin", user);
        return mav;
    }
/*
    @RequestMapping("/userlogin")
    public ModelAndView log(@RequestParam("usercode") String usercode, @RequestParam("password") String password, Model model){
        UserEntity user = userService.validateUser(usercode, password);
        ModelAndView mav =new ModelAndView();
        mav.setViewName("index");
        mav.addObject("userlogin",user);
        mav.addObject("users",userService.findAllUser());
        model.addAttribute("userlogin", user);
        return mav;
    }
*/

//        public ModelAndView findUserByUsername(@RequestParam("username") String username){
//        UserEntity userEntity = userService.findByUsername(username);
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("");
//        return mav;
//    }

}
