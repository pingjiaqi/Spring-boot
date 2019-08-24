package com.pjq.service;

import com.pjq.dao.BookDao;
import com.pjq.dao.CourseDao;
import com.pjq.pojo.Book;
import com.pjq.pojo.Course;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("progressService")
@Scope("prototype")
public class ProgressService {

    @Resource
    private BookDao bookMapper;
    private CourseDao courseMapper;

    public boolean updateBookProgress(String username,String title,double progress){
        Book book=bookMapper.selectByUserBook(username, title);
        if(book==null)
        {
            return false;
        }
        return bookMapper.updateProgress(username, title, progress);
    }

    public boolean updateCourseProgress(String username,String course_name,double progress){
        return courseMapper.updateCourseProgress(username, course_name, progress);
    }

    public double selectCourseProgress(String username,String course_name){
        return courseMapper.selectProgress(username, course_name);
    }

}
