package hr.dao;

import org.apache.ibatis.annotations.Param;

import hr.model.Cm_department_employeeKey;

public interface Cm_department_employeeMapper {
    int deleteByPrimaryKey(@Param(value = "employeeId")String employeeId,@Param(value = "departmentId")String departmentId);

    int insert(@Param(value = "employeeId")String employeeId,@Param(value = "departmentId")String departmentId);

    int insertSelective(Cm_department_employeeKey record);
    
}