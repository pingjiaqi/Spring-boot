package com.pjq.service;

import com.pjq.dao.UserDao;
import com.pjq.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.Resource;


@Service("userService")
@Scope("prototype")

public class UserService {


    @Resource
    UserDao userMapper;


    public boolean login(String username,String password){
        User user=userMapper.selectByName(username);
        if(user!=null)
        {
            if(user.getUsername().equals(username)&&user.getPassword().equals((password)))
            {
                return true;
            }
        }
        return false;
    }

    public User userMessage(String username){
        User user=userMapper.selectByName(username);
        return user;
    }

    public boolean register(String username,String password,String name){
        User user=userMapper.selectByName(username);
        if(user==null)
        {
            userMapper.insertIntoUser(username,password,name);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void changeInformation(String username,String student_id,String name,String sex){
        userMapper.updateUserInformation(username, student_id, name, sex);
    }





}
