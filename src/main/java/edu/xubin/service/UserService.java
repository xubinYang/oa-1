package edu.xubin.service;

import edu.xubin.bean.User;

import java.util.List;

public interface UserService {

//    void addUser(User userEntity);
//
//    public User validateUser(String usercode, String password);
//
//    User findByUsername(String username);

    List<User> findAllUser();

    User findUserByUserid(String userid);
}
