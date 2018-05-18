package hr.dao;

import hr.model.Sys_permission;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface Sys_permissionMapper {
    int deleteByPrimaryKey(String permissionId);

    int insert(Sys_permission record);

    int insertSelective(Sys_permission record);

    Sys_permission selectByPrimaryKey(String permissionId);

    int updateByPrimaryKeySelective(Sys_permission record);

    int updateByPrimaryKey(Sys_permission record);
    
    List<Sys_permission> showAllPermission(@Param(value = "keyword")String keyword);
    
    List<Sys_permission> findRolePermission(String roleId);
    
    List<Sys_permission> getAllPermissionWithoutRole(@Param("roleId")String roleId);
    
    Set<Sys_permission> getPermissions(String userName);
}