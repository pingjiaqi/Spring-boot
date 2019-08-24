package com.pjq.controller;

import com.pjq.pojo.Result;
import com.pjq.service.ProgressService;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@Scope("prototype")
@RequestMapping("/api/progress")
public class ProgressController {
    @Resource
    private ProgressService progressService;


    @RequestMapping(path = "/updatebookprogress")
    public Result updateBookProgress(HttpSession session, @Param("title")String title,@Param("progress")double progress){
        String username=(String)session.getAttribute("username");
        progressService.updateBookProgress(username,title,progress);
        Result result=new Result();
        result.setMessage("success");
        result.setCode("200");
        return result;
    }


    @RequestMapping(path = "/updatecourseporgress")
    public  Result updateCourseProgress(HttpSession session,@Param("course_name")String course_name,@Param("progress")double progress){
        String username=(String)session.getAttribute("username");
        progressService.updateCourseProgress(username,course_name,progress);
        Result result=new Result();
        result.setMessage("success");
        result.setCode("200");
        return result;
    }



}
