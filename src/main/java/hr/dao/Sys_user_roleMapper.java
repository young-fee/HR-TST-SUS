package hr.dao;

import hr.model.Sys_user_roleKey;

import org.apache.ibatis.annotations.Param;

public interface Sys_user_roleMapper {
    int deleteByPrimaryKey(@Param("userLoginId")String userLoginId, @Param("roleId")String roleId);

    int insert(@Param("userLoginId")String userLoginId, @Param("roleId")String roleId);

    int insertSelective(Sys_user_roleKey record);
    
}