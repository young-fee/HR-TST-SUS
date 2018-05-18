package hr.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hr.model.Cm_employee;

public interface Cm_employeeMapper {
    int deleteByPrimaryKey(String employeeId);

    int insert(Cm_employee record);

    int insertSelective(Cm_employee record);

    Cm_employee selectByPrimaryKey(String employeeId);

    int updateByPrimaryKeySelective(Cm_employee record);

    int updateByPrimaryKey(Cm_employee employee);
    
    public List<Cm_employee> GetAllEmployee(@Param(value = "keyword")String keyword,@Param(value = "start")int start,@Param(value = "end")int end);
    
    public List<Cm_employee> findEmployeeByDepartment(String departmentId);
    
    public List<Cm_employee> GetAllEmployeeWithoutDepartment();
}