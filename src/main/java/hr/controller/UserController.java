package hr.controller;

import hr.model.Sys_role;
import hr.model.Sys_user;
import hr.service.RoleServiceI;
import hr.service.UserServiceI;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import charset.ChangeCharset;
import charset.getStringEncoding;

/**
 * 用户管理
 * 
 * @author Young
 *
 */
@Controller
public class UserController {
	private UserServiceI UserService;
	private RoleServiceI roleService;

	public RoleServiceI getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(RoleServiceI roleService) {
		this.roleService = roleService;
	}

	public UserServiceI getUserService() {
		return UserService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		UserService = userService;
	}

	/**
	 * 数据接口，返回用户json字符串
	 * 
	 * @param keyword
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("showAllUser")
	@ResponseBody
	public Map<String, Object> showAllUser(String keyword,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 利用ChangeCharset类将iso8859-1字符转为utf-8;不转换则为乱码
		ChangeCharset change = new ChangeCharset();
		String keyword1 = change.changeCharset(keyword, "iso8859-1", "utf-8");

		List<Sys_user> list = UserService.findAllUsers(keyword1);
		response.setCharacterEncoding("utf-8");// response乱码问题解决
		resultMap.put("count", 2);
		resultMap.put("msg", "");
		resultMap.put("code", 0);
		resultMap.put("data", list);
		return resultMap;
	}

	/**
	 * 显示用户列表
	 * 
	 * @return
	 */
	@RequestMapping("showAllUserView")
	public String showAllUserView() {
		return "jsp/user/showAllUser";
	}

	/**
	 * 添加用户，规定用户id和用户名相同
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("addUser")
	@RequiresRoles("ROLE_ADMIN")
	public String AddUser(Sys_user user, HttpServletRequest request) {
		// 加密密码
		System.out.println("-----------------------------------1--"
				+ user.getPassword());
		String password = new SimpleHash("MD5", user.getPassword(),
				user.getUserName(), 1024).toString();
		user.setPassword(password);

		System.out.println("-----------------------------------2--"
				+ user.getPassword());
		UserService.AddSys_User(user);
		return "jsp/user/showAllUser";
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("deleteUserById")
	@RequiresRoles("ROLE_ADMIN")
	public String deleteUserById(String id, HttpServletRequest request)
			throws UnsupportedEncodingException {
		// 获取编码
		if (new getStringEncoding().getEncoding(id).equals("ISO-8859-1")) {
			// 字符转码
			ChangeCharset change = new ChangeCharset();
			id = change.changeCharset(id, "iso8859-1", "utf-8");
		}
		UserService.deleteUserById(id);
		return "jsp/user/showAllUser";
	}

	/**
	 * 查看用户的角色,返回角色json字符串
	 * 
	 * @param response
	 * @param userLoginId
	 * @return
	 */
	@RequestMapping("showUserRole")
	@ResponseBody
	public Map<String, Object> showUserRole(HttpServletResponse response,
			String userLoginId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Sys_role> list = roleService.findUserRole(userLoginId);

		response.setCharacterEncoding("utf-8");// response乱码问题解决
		resultMap.put("count", 2);
		resultMap.put("msg", "");
		resultMap.put("code", 0);
		resultMap.put("data", list);
		return resultMap;
	}

	/**
	 * 显示角色列表
	 * 
	 * @param userLoginId
	 * @param modelMap
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("showUserRoleView")
	public String showUserRoleView(String userLoginId, ModelMap modelMap)
			throws UnsupportedEncodingException {
		ChangeCharset change = new ChangeCharset();
		userLoginId = change.changeCharset(userLoginId, "iso8859-1", "utf-8");
		modelMap.put("userLoginId", userLoginId);// 在jsp页面通过${key}获取modelMap中的值　
		List<Sys_role> roleList = roleService
				.getAllRoleWithoutUser(userLoginId);
		modelMap.put("roleList", roleList);
		return "jsp/user/user_roleList";
	}

	/**
	 * 用于添加角色到用户
	 * 
	 * @param userLoginId
	 * @param roleId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("addRoleToUser")
	@RequiresRoles("ROLE_ADMIN")
	public String addRoleToUser(String userLoginId, String roleId,
			ModelMap modelMap) {
		modelMap.put("userLoginId", userLoginId);// 在jsp页面通过${key}获取modelMap中的值　
		System.out.println("----------------------->" + userLoginId
				+ "------------------>" + roleId);
		roleService.addRoleToUser(userLoginId, roleId);
		List<Sys_role> roleList = roleService
				.getAllRoleWithoutUser(userLoginId);
		modelMap.put("roleList", roleList);
		return "jsp/user/user_roleList";
	}

	/**
	 * 用于将角色从用户移除
	 * 
	 * @param userLoginId
	 * @param roleId
	 * @return
	 */
	@RequestMapping("delUserRole")
	@RequiresRoles("ROLE_ADMIN")
	public String delUserRole(String userLoginId, String roleId,
			ModelMap modelMap) {
		UserService.delUserRole(userLoginId, roleId);
		modelMap.put("userLoginId", userLoginId);// 在jsp页面通过${key}获取modelMap中的值
		List<Sys_role> roleList = roleService
				.getAllRoleWithoutUser(userLoginId);
		modelMap.put("roleList", roleList);
		return "jsp/user/user_roleList";
	}

}
