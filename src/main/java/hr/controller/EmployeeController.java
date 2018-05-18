package hr.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.model.Cm_employee;
import hr.service.EmployeeServiceI;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import charset.ChangeCharset;


/**
 * 员工管理
 * @author Young
 *
 */
@Controller
public class EmployeeController {

	private EmployeeServiceI employeeService;

	public EmployeeServiceI getEmployeeService() {
		return employeeService;
	}

	@Autowired
	public void setEmployeeService(EmployeeServiceI employeeService) {
		this.employeeService = employeeService;
	}
	
	/**
	 * 显示所有员工信息，返回json
	 * @param response
	 * @param request
	 * @param keyword
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("showAllEmployee")
	@ResponseBody
	public Map<String, Object> showAllEmployee(HttpServletResponse response,HttpServletRequest request ,String keyword) throws UnsupportedEncodingException{		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int start = (Integer.parseInt(request.getParameter("page"))-1)*Integer.parseInt(request.getParameter("limit"));
		int end = Integer.parseInt(request.getParameter("page"))*Integer.parseInt(request.getParameter("limit"));
		//利用ChangeCharset类将iso8859-1字符转为utf-8;不转换则为乱码
		ChangeCharset change = new ChangeCharset();
		String keyword1 = change.changeCharset(keyword, "iso8859-1", "utf-8");
		
		List<Cm_employee> list = employeeService.GetAllEmployee(keyword1,start,end);
		
		response.setCharacterEncoding("utf-8");//response乱码问题解决
		resultMap.put("count", 2);
		resultMap.put("msg", "");
		resultMap.put("code", 0);
		resultMap.put("data", list);
		return resultMap;
	}
	/**
	 * 用于显示所有员工的列表
	 * @return
	 */
	@RequestMapping("/showAllEmployeeView")
	public String ShowAllEmployeeView(){
		return "jsp/employee/showAllEmployee";
	}
	/**
	 * 用于删除员工
	 * @param employeeId
	 * @return
	 */
	@RequestMapping("delEmployee")
	@RequiresRoles("ROLE_ADMIN")
	public String delEmployee(String employeeId){
		employeeService.delEmployee(employeeId);
		return "jsp/employee/showAllEmployee";
	}
	/**
	 * 用于添加员工
	 * @param employee
	 * @return
	 */
	@RequestMapping("addEmployee")
	@RequiresRoles("ROLE_ADMIN")
	public String addEmployee(Cm_employee employee){
		employeeService.addEmployee(employee);
		return "jsp/employee/showAllEmployee";
	}
	/**
	 * 用于修改员工信息
	 * @param employee
	 * @return
	 */
	@RequestMapping("updateEmployeeById")
	@RequiresRoles("ROLE_ADMIN")
	public String updateEmployeeById(Cm_employee employee){
		employeeService.updateEmployeeById(employee);
		return "jsp/employee/showAllEmployee";
	}
}
