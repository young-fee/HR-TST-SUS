package hr.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import hr.model.Cm_department;
import hr.model.Cm_employee;
import hr.service.DepartmentServiceI;
import hr.service.EmployeeServiceI;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import charset.ChangeCharset;
/**
 * 部门管理
 * @author Young
 *
 */
@Controller
public class DepartmentController {
	
	private EmployeeServiceI employeeService;
	private DepartmentServiceI departmentService;

	//	get set方法	
	public DepartmentServiceI getDepartmentService() {
		return departmentService;
	}

	@Autowired
	public void setDepartmentService(DepartmentServiceI departmentService) {
		this.departmentService = departmentService;
	}
	//	get set方法
	public EmployeeServiceI getEmployeeService() {
		return employeeService;
	}
	@Autowired
	public void setEmployeeService(EmployeeServiceI employeeService) {
		this.employeeService = employeeService;
	}
	/**
	 * 用于显示部门列表界面
	 * @return
	 */
	@RequestMapping("showAllDepartmentView")
	public String showAllDepartmentView(){
		
		return "jsp/department/showAllDepartment";
	}
	
	/**
	 * 用于查询所有部门，返回json字符串，为数据表格的数据接口
	 * @param response
	 * @param keyword
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("showAllDepartment")
	@ResponseBody
	public Map<String, Object> showAllDepartment(HttpServletResponse response,String keyword) throws UnsupportedEncodingException{
		Map<String , Object> resultMap = new HashMap<String, Object>();
		//利用ChangeCharset类将iso8859-1字符转为utf-8;不转换则为乱码
		ChangeCharset change = new ChangeCharset();
		String keyword1 = change.changeCharset(keyword, "iso8859-1", "utf-8");
		
		List<Cm_department> list = departmentService.showAllDepartment(keyword1);
		
		response.setCharacterEncoding("utf-8");//response乱码问题解决
		resultMap.put("count", 2);
		resultMap.put("msg", "");
		resultMap.put("code", 0);
		resultMap.put("data", list);
		return resultMap;
	}
	/**
	 * 用于添加部门
	 * @param department
	 * @return
	 */
	@RequestMapping("addDepartment")
	@RequiresRoles("ROLE_ADMIN")
	public String addDepartment(Cm_department department){
		departmentService.addDepartment(department);
		return "jsp/department/showAllDepartment";
	}
	
	/**
	 * 用于删除部门
	 * @param departmentId
	 * @return
	 */
	@RequestMapping("delDepartment")
	@RequiresRoles("ROLE_ADMIN")
	public String delDepartemnt(String departmentId){
		
		departmentService.delDepartemnt(departmentId);
		return "jsp/department/showAllDepartment";
	}
	
	/**
	 * 用于修改部门信息
	 * @param department
	 * @return
	 */
	@RequestMapping("updateDepartmentById")
	@RequiresRoles("ROLE_ADMIN")
	public String updateDepartmentById(Cm_department department){
		departmentService.updateDepartmentById(department);
		return "jsp/department/showAllDepartment";
	}
	
	/**
	 * 用于通过部门号查看部门所属的员工，返回json作为表格的数据接口
	 * @param departmentId
	 * @param response
	 * @return
	 */
	@RequestMapping("findEmployeeByDepartment")
	@ResponseBody
	public Map<String, Object> findEmployeeByDepartment(String departmentId , HttpServletResponse response){
		Map<String , Object> resultMap = new HashMap<String, Object>();
		List<Cm_employee> list = employeeService.findEmployeeByDepartment(departmentId);
		response.setCharacterEncoding("utf-8");//response乱码问题解决
		resultMap.put("count", 2);
		resultMap.put("msg", "");
		resultMap.put("code", 0);
		resultMap.put("data", list);
		return resultMap; 
	}
	/**
	 * 用于显示部门员工的列表
	 * @param departmentId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("findEmployeeByDepartmentView")
	public String findEmployeeByDepartmentView(String departmentId,ModelMap modelMap){
		modelMap.put("departmentId", departmentId);//在jsp页面通过${key}获取modelMap中的值　
		List<Cm_employee> employeeList = employeeService.GetAllEmployeeWithoutDepartment();
		modelMap.put("employeeList", employeeList);
		return "jsp/department/department_employeeList";
	}
	
	/**
	 * 用于根据部门id删除所属的员工
	 * @param employeeId
	 * @param departmentId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("delDepartmentEmployee")
	@RequiresRoles("ROLE_ADMIN")
	public String delDepartmentEmployee(String employeeId,String departmentId,ModelMap modelMap){
		System.out.println("----------------------------------->"+departmentId+"---------------->"+employeeId);
		departmentService.delDepartmentEmployee(employeeId, departmentId);//移除
		modelMap.put("departmentId", departmentId);//在jsp页面通过${key}获取modelMap中的值
		List<Cm_employee> employeeList = employeeService.GetAllEmployeeWithoutDepartment();
		modelMap.put("employeeList", employeeList);
		return "jsp/department/department_employeeList";
	}
	
	/**
	 * 用于添加其他员工到该部门
	 * @param employeeId
	 * @param departmentId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("addEmployeeToDepartment")
	@RequiresRoles("ROLE_ADMIN")
	public String addEmployeeToDepartment(String employeeId,String departmentId,ModelMap modelMap){
		
		System.out.println("----------------------------------->"+departmentId+"---------------->"+employeeId);
		departmentService.addEmployeeToDepartment(employeeId, departmentId);//添加
		modelMap.put("departmentId", departmentId);//在jsp页面通过${key}获取modelMap中的值
		List<Cm_employee> employeeList = employeeService.GetAllEmployeeWithoutDepartment();
		modelMap.put("employeeList", employeeList);
		return "jsp/department/department_employeeList";
	}
}
