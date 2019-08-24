package com.pjq.dao;

import com.pjq.pojo.Course;
import com.pjq.pojo.MyCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Property;

import java.util.List;
@Mapper
public interface CourseDao {
    //查找所以课程
    List<Course> getAllCourse();
    //查找一个课程(可以模糊查找、条件查找）
    public List<Course> selectByCourse(@Param("course_name") String course_name,@Param("content") String content,
                                 @Param("subject") String subject);
    //查找我的课程(全部）
    public List<String> selectByUserCourse(String username);
    //查找一个课程
    public Course selectDetailCourse(@Param("course_name") String course_name);
    //删除我的课程
    public boolean deleteByUserCourse(@Param("username") String username, @Param("course_name") String course_name);
    //购买完成我的课程（添加课程)
    public boolean insetIntoUserCourse(@Param("username") String username, @Param("course_name") String course_name);
    //课程进度调整
    public boolean updateCourseProgress(@Param("username")String username,@Param("course_name")String course_name,
                                        @Param("progress")double progress);

    //查看进度
    public double selectProgress(@Param("username")String username,@Param("course_name")String course_name);

}
