package hr.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import hr.model.Sys_role;

public interface Sys_roleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Sys_role record);

    int insertSelective(Sys_role record);

    Sys_role selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(Sys_role record);

    int updateByPrimaryKey(Sys_role record);
    
    List<Sys_role> showAllRole(@Param(value = "keyword")String keyword);
    
    List<Sys_role> findUserRole(String userLoginId);
    
    List<Sys_role> getAllRoleWithoutUser(String userLoginId);
    
    Set<Sys_role> getRoles(String userName);
    
}