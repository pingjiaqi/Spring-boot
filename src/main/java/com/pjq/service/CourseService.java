package com.pjq.service;

import com.pjq.dao.CourseDao;
import com.pjq.dao.UserDao;
import com.pjq.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import sun.plugin.javascript.navig.LinkArray;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("courseService")
@Scope("prototype")
public class CourseService {
    @Resource
    CourseDao courseMapper;


    public List<Course> getAllCourse(){
        return courseMapper.getAllCourse();
    }

    public List<Course> findCourse(String course_name,String content,String subject){
        List<Course> courseName=courseMapper.selectByCourse(course_name,content,subject);
        return  courseName;
    }

    public List<Course> findMyCourse(String username){
        List<String> courseName=courseMapper.selectByUserCourse(username);
        List<Course> courseList=new ArrayList<Course>();
        for(int i=0;i<courseName.size();i++)
        {
            String c=courseName.get(i);
            courseList.add(courseMapper.selectDetailCourse(c));
        }
        return courseList;
    }

    public Boolean deleteMyCourse(String username,String course_name){
        Boolean type= courseMapper.deleteByUserCourse(username, course_name);
        return type;
    }

    public Boolean insetIntoUserCourse(String username,String course_name){
        Boolean type=false;
        List<String> courseName=courseMapper.selectByUserCourse(username);
        if(courseName==null)
        {
            courseMapper.insetIntoUserCourse(username,course_name);
            type=true;

        }else {
            type=false;
        }
        return type;

    }

    public Boolean updateCourseProgress(String username,String course_name,double progress){
        Boolean isUpadate=courseMapper.updateCourseProgress(username,course_name,progress);
        return isUpadate;
    }

    public Course findDetailCourse(String course_name){
        Course course=courseMapper.selectDetailCourse(course_name);
        return course;
    }

}
