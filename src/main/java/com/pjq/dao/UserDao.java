package com.pjq.dao;

import com.pjq.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserDao {
    public User selectByName(String username);

    public void insertIntoUser(@Param("username") String username, @Param("password") String password,@Param("name") String name);

    public void updateUserInformation(@Param("username")String username,@Param("student_id")String student_id,@Param("name")String name,@Param("sex")String sex);
}
