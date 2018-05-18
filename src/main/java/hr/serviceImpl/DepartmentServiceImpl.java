package hr.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.dao.Cm_departmentMapper;
import hr.dao.Cm_department_employeeMapper;
import hr.model.Cm_department;
import hr.service.DepartmentServiceI;
/**
 * 
 * @author Young
 *
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentServiceI {
	
	
	private Cm_departmentMapper departmentMapper;
	private Cm_department_employeeMapper departmentEmpployeeMapper;
	
	//get set 方法
	public Cm_department_employeeMapper getDepartmentEmpployeeMapper() {
		return departmentEmpployeeMapper;
	}
	@Autowired
	public void setDepartmentEmpployeeMapper(
			Cm_department_employeeMapper departmentEmpployeeMapper) {
		this.departmentEmpployeeMapper = departmentEmpployeeMapper;
	}

	//get set 方法
	public Cm_departmentMapper getDepartmentMapper() {
		return departmentMapper;
	}

	@Autowired
	public void setDepartmentMapper(Cm_departmentMapper departmentMapper) {
		this.departmentMapper = departmentMapper;
	}

	/**
	 * 用于查询部门信息
	 */
	@Override
	public List<Cm_department> showAllDepartment(String keyword) {
		return departmentMapper.showAllDepartment(keyword);
	}
	/**
	 * 用于添加部门
	 */
	@Override
	public Cm_department addDepartment(Cm_department department ){
		departmentMapper.insertSelective(department);
		return department;
	}
	/**
	 * 用于删除部门
	 */
	@Override
	public int delDepartemnt(String departmentId) {
		return departmentMapper.deleteByPrimaryKey(departmentId);
	}
	/**
	 * 用于修改部门
	 */
	@Override
	public int updateDepartmentById(Cm_department department) {
		return departmentMapper.updateByPrimaryKeySelective(department);
		
	}
	/**
	 * 用于删除部门员工
	 */
	@Override
	public int delDepartmentEmployee(String employeeId, String departmentId) {
		System.out.println("------------------------->>>>>"+departmentId);
		return departmentEmpployeeMapper.deleteByPrimaryKey(employeeId, departmentId);
	}
	/**
	 * 用于添加部门员工
	 */
	@Override
	public int addEmployeeToDepartment(String employeeId, String departmentId) {
		System.out.println("------------------------->>>>>"+departmentId+"------------------------->>>>>"+employeeId);
		return departmentEmpployeeMapper.insert(employeeId, departmentId);
	}

}
