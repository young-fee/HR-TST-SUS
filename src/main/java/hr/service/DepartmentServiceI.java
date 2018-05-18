package hr.service;

import hr.model.Cm_department;

import java.util.List;

public interface DepartmentServiceI {
	
	public List<Cm_department>  showAllDepartment(String keyword);
	
	public Cm_department addDepartment(Cm_department department );
	
	public int delDepartemnt(String departmentId);
	
	public int updateDepartmentById(Cm_department department);
	
	public int delDepartmentEmployee(String employeeId,String departmentId);
	
	public int addEmployeeToDepartment(String employeeId,String departmentId);
}
