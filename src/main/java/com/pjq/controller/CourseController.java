package com.pjq.controller;

import com.pjq.pojo.Course;
import com.pjq.pojo.Result;
import com.pjq.service.CourseService;
import com.pjq.service.ProgressService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@Controller
@Scope("prototype")
@RequestMapping(path = "/api/course")
public class CourseController {

    @Autowired
    private CourseService service;
    @Autowired
    CourseService courseService;

    @ResponseBody
    @RequestMapping(path = "/findallcourse", method = RequestMethod.GET)
    public Result findAllCourse(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Credentials","true");
        Result result=new Result();
        List<Course> allCourse=courseService.getAllCourse();
        result.setMessage("success");
        result.setResult(allCourse);
        return result;
    }

    @ResponseBody
    @RequestMapping(path = "/findcourse", method = RequestMethod.POST)
    public Result findCourse(HttpServletResponse response,@Param("course_name") String course_name, @Param("content") String content,@Param("subject") String subject){
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Result result=new Result();
        List<Course> course=courseService.findCourse(course_name, content, subject);
        result.setResult(course);
        if(course==null)
        {
            result.setMessage("fail");
        }
        else {
            result.setMessage("success");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(path = "/my/findmycourse",method = RequestMethod.POST)
    public Result findMyCourse(String course_name, HttpSession session,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Credentials","true");
        String username=(String)session.getAttribute("username");
        System.out.println(username);
        Result result=new Result();
        List<Course> course=courseService.findMyCourse(username);
        if(course==null)
        {
            result.setCode("500");
            result.setMessage("fail");
        }
        else
        {
            result.setCode("200");
            result.setResult(course);
            result.setMessage("success");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(path = "/my/deletemycourse")
    public Result deleteMyCourse(HttpSession session,String course_name){
        Result result=new Result();
        String username=(String)session.getAttribute("username");
        if(courseService.deleteMyCourse(username, course_name)==true)
        {
            result.setMessage("success");
        }
        else
        {
            result.setMessage("fail");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(path = "/my/buycourse")
    public Result buyCourse(HttpSession session,String course_name){
        Result result=new Result();
        String username=(String)session.getAttribute("username");
        Boolean inserIntoType=courseService.insetIntoUserCourse(username, course_name);
        if(inserIntoType==true)
        {
            result.setMessage("success");
        }
        else
        {
            result.setMessage("fail");
        }
        return result;
    }

//    @ResponseBody
//    @RequestMapping(path = "/my/updatecourseprogress")
//    public Result updateCourseProgress(HttpSession session,String course_name,double progress){
//        Result result=new Result();
//        String username=(String)session.getAttribute("username");
//        if(courseService.updateCourseProgress(username, course_name, progress)==true)
//        {
//            result.setMessage("success");
//        }
//        else
//        {
//            result.setMessage("fail");
//        }
//        return result;
//    }
}
