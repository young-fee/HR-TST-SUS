package hr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hr.model.Sys_user;

public interface Sys_userMapper {
    int deleteByPrimaryKey(String userLoginId);

    int insert(Sys_user record);

    int insertSelective(Sys_user record);

    Sys_user selectByPrimaryKey(String userLoginId);

    int updateByPrimaryKeySelective(Sys_user record);

    int updateByPrimaryKey(Sys_user record);
    
    List<Sys_user> findAllUsers(@Param("keyword")String keyword);
    
    Sys_user getUserByName(@Param("userName")String userName);
    
    List<Sys_user>  getUserByNameList(@Param("userName")String userName);
}