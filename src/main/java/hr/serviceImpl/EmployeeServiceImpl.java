package hr.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.dao.Cm_employeeMapper;
import hr.model.Cm_employee;
import hr.service.EmployeeServiceI;
/**
 * 
 * @author Young
 *
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeServiceI{



	private Cm_employeeMapper employeeMapper; 
	
	//get set 方法
	public Cm_employeeMapper getEmployeeMapper() {
		return employeeMapper;
	}
	
	@Autowired
	public void setEmployeeMapper(Cm_employeeMapper employeeMapper) {
		this.employeeMapper = employeeMapper;
	}
	
	/**
	 * 用于通过关键字查询员工列表
	 */
	@Override
	public List<Cm_employee> GetAllEmployee(String keyword,int start,int end){
		
		if(keyword == null){
			keyword = "";
		}
		return employeeMapper.GetAllEmployee(keyword,start,end);
		
	}
	/**
	 * 用于查询员工
	 */
	@Override
	public Cm_employee getEmplyeeById(String id) {
		return employeeMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 用于删除员工
	 */
	@Override
	public int delEmployee(String employeeId){
		return employeeMapper.deleteByPrimaryKey(employeeId);
	}
	/**
	 * 用于添加员工
	 */
	@Override
	public Cm_employee addEmployee(Cm_employee employee){
		employeeMapper.insertSelective(employee);
		return employee;
	}
	/**
	 * 用于修改员工
	 */
	@Override
	public int updateEmployeeById(Cm_employee employee){
		return employeeMapper.updateByPrimaryKey(employee);
	}
	/**
	 * 用于查询该部门的员工
	 */
	@Override
	public  List<Cm_employee> findEmployeeByDepartment(String departmentId) {
		List<Cm_employee> list = employeeMapper.findEmployeeByDepartment(departmentId);
		return list;
	}
	/**
	 * 用于查询出所有未分布到部门的员工
	 */
	@Override
	public List<Cm_employee> GetAllEmployeeWithoutDepartment() {
		List<Cm_employee> list = employeeMapper.GetAllEmployeeWithoutDepartment();
		return list;
	}
}
