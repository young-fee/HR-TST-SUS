package hr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hr.model.Cm_department;

public interface Cm_departmentMapper {
    int deleteByPrimaryKey(String departmentId);

    int insert(Cm_department department);

    int insertSelective(Cm_department record);

    Cm_department selectByPrimaryKey(String departmentId);

    int updateByPrimaryKeySelective(Cm_department record);

    int updateByPrimaryKey(Cm_department record);
    
    List<Cm_department> showAllDepartment(@Param(value = "keyword")String keyword);
}