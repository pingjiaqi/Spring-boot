package com.pjq.controller;


import com.pjq.pojo.Result;
import com.pjq.pojo.User;
import com.pjq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping(value = "/user")
@Scope("prototype")
public class UserController {

    @Autowired
     UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session)  {
        Result result=new Result();
        Boolean t=userService.login(username,password);
        User user=userService.userMessage(username);
        if(t==true)
        {
            session.setAttribute("username",username);
            session.setAttribute("type",user.getType());
            result.setMessage("success");
            result.setCode("200");
        }
        else
        {
            result.setMessage("fail");
        }
        return result;
    }


    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public Result registerIsWork(HttpServletRequest request){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String name=request.getParameter("name");
        Result result=new Result();
        if(userService.register(username,password,name)==true)
        {
            result.setMessage("success");
        }
        else
        {
            result.setMessage("fail");
        }
        return result;
    }

    @RequestMapping(path = "/showinformation",method = RequestMethod.GET)
    public Result showInformation(HttpSession httpSession){
        String username=(String)httpSession.getAttribute("username");
        User user=userService.userMessage(username);
        Result result=new Result();
        result.setCode("200");
        result.setMessage("success");
        result.setResult(user);
        return result;
    }

    @RequestMapping(path = "/changeinformation",method = RequestMethod.POST)
    public Result changeInformation(HttpSession httpSession,String student_id,String name,String sex){
        String username=(String)httpSession.getAttribute("username");
        userService.changeInformation(username,student_id,name,sex);
        Result result=new Result();
        result.setCode("200");
        result.setMessage("success");
        return result;
    }


}
