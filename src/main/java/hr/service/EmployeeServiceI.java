package hr.service;

import java.util.List;
import hr.model.Cm_employee;

public interface EmployeeServiceI {
	//public PageInfo<Cm_employee> GetAllEmployee(int page ,int limit);
	
	public Cm_employee getEmplyeeById(String id);
	
	public List<Cm_employee> GetAllEmployee(String keyword ,int start,int end);
		
	public int delEmployee(String employeeId);
	
	public Cm_employee addEmployee(Cm_employee employee);
	
	public int updateEmployeeById(Cm_employee emplloyee);
	
	public List<Cm_employee> findEmployeeByDepartment(String DepartmentId);
	
	public List<Cm_employee> GetAllEmployeeWithoutDepartment();

}
