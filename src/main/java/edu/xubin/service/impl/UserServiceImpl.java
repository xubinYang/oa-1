package edu.xubin.service.impl;

import edu.xubin.bean.User;
import edu.xubin.service.UserService;
import edu.xubin.util.WechatService.WechatUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public List<User> findAllUser() {
        return WechatUserService.getAllUser();
    }

    public User findUserByUserid(String userid){
        return WechatUserService.getUserByUserid(userid);
    }

//    public void addUser(User userEntity){
//        this.userDao.save(userEntity);
//    }

//    @Override
//    public User validateUser(String usercode, String password) {
//        User user = userDao.findByUsercode(usercode);
//        if(!user.getPassword().equalsIgnoreCase(password)){
//            return null;
//        }
//        else {
//            return user;
//        }
//    }

//    public User findByUsercode(String usercode){
//        return userDao.findByUsercode(usercode);
//    }

//    public User findByUsername(String username){
//        User user = userDao.findByUsername(username);
//        return user;
//
//    }




}
