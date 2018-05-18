package hr.dao;

import hr.model.Sys_role_permissionKey;

import org.apache.ibatis.annotations.Param;

public interface Sys_role_permissionMapper {
    int deleteByPrimaryKey(@Param("roleId")String roleId, @Param("permissionId")String permissionId);

    int insert(@Param("roleId")String roleId, @Param("permissionId")String permissionId);

    int insertSelective(Sys_role_permissionKey record);
    

}