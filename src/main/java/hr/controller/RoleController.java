package hr.controller;

import hr.model.Sys_permission;
import hr.model.Sys_role;
import hr.service.PermissionServiceI;
import hr.service.RoleServiceI;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import charset.ChangeCharset;
import charset.getStringEncoding;

/**
 * 角色管理
 * 
 * @author Young
 *
 */
@Controller
public class RoleController {
	private RoleServiceI roleService;
	private PermissionServiceI permissionService;

	public PermissionServiceI getPermissionService() {
		return permissionService;
	}

	@Autowired
	public void setPermissionService(PermissionServiceI permissionService) {
		this.permissionService = permissionService;
	}

	public RoleServiceI getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(RoleServiceI roleService) {
		this.roleService = roleService;
	}

	/**
	 * 显示所有的角色信息，返回json字符串
	 * 
	 * @param response
	 * @param keyword
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("showAllRole")
	@ResponseBody
	public Map<String, Object> showAllRole(HttpServletResponse response,
			String keyword) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 利用ChangeCharset类将iso8859-1字符转为utf-8;不转换则为乱码
		ChangeCharset change = new ChangeCharset();
		String keyword1 = change.changeCharset(keyword, "iso8859-1", "utf-8");

		List<Sys_role> list = roleService.showAllRole(keyword1);// 利用关键字查询

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
	 * @return
	 */
	@RequestMapping("showAllRoleView")
	public String showAllRoleView() {

		return "/jsp/role/showAllRole";
	}

	/**
	 * 删除该角色
	 * 
	 * @param roleID
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("delRole")
	@RequiresRoles("ROLE_ADMIN")
	public String delRole(String roleId) throws UnsupportedEncodingException {
		// 获取编码
		if (new getStringEncoding().getEncoding(roleId).equals("ISO-8859-1")) {
			// 字符转码
			ChangeCharset change = new ChangeCharset();
			roleId = change.changeCharset(roleId, "iso8859-1", "utf-8");
		}
		roleService.delRole(roleId);
		return "/jsp/role/showAllRole";
	}

	/**
	 * 修改角色信息
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("updateRole")
	@RequiresRoles("ROLE_ADMIN")
	public String updateRole(Sys_role role) {
		roleService.updateRole(role);
		return "/jsp/role/showAllRole";
	}

	/**
	 * 添加角色
	 * 
	 * @return
	 */
	@RequestMapping("addRole")
	@RequiresRoles("ROLE_ADMIN")
	public String addRole(Sys_role role) {
		roleService.addRole(role);
		return "/jsp/role/showAllRole";
	}

	/**
	 * 查看角色的权限
	 * 
	 * @return
	 */
	@RequestMapping("findRolePermissionView")
	public String findRolePermissionView() {

		return "jsp/role/role_permissionList";
	}

	/**
	 * 查看角色所拥有的权限的数据接口，返回json字符串
	 * 
	 * @param response
	 * @param roleId
	 * @return
	 */
	@RequestMapping("showRolePermission")
	@ResponseBody
	public Map<String, Object> showRolePermission(HttpServletResponse response,
			String roleId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<Sys_permission> list = permissionService
				.findRolePermission(roleId);

		response.setCharacterEncoding("utf-8");// response乱码问题解决
		resultMap.put("count", 2);
		resultMap.put("msg", "");
		resultMap.put("code", 0);
		resultMap.put("data", list);
		return resultMap;
	}

	/**
	 * 显示权限列表
	 * 
	 * @param roleId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("showRolePermissionView")
	public String showRolePermissionView(String roleId, ModelMap modelMap) {
		modelMap.put("roleId", roleId);// 在jsp页面通过${key}获取modelMap中的值　
		// 得到所有为分配给角色的权限
		List<Sys_permission> permissionList = permissionService
				.getAllPermissionWithoutRole(roleId);

		modelMap.put("permissionList", permissionList);
		return "jsp/role/role_permissionList";
	}

	/**
	 * 删除角色的权限
	 * 
	 * @param roleId
	 * @param permissionId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("delRolePermission")
	@RequiresRoles("ROLE_ADMIN")
	public String delRolePermission(String roleId, String permissionId,
			ModelMap modelMap) {
		roleService.delRolePermission(roleId, permissionId);// 移除
		modelMap.put("roleId", roleId);// 在jsp页面通过${key}获取modelMap中的值

		List<Sys_permission> permissionList = permissionService
				.getAllPermissionWithoutRole(roleId);
		modelMap.put("permissionList", permissionList);
		return "jsp/role/role_permissionList";
	}

	/**
	 * 添加权限到角色
	 * 
	 * @param roleId
	 * @param permissionId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("addPermissionToRole")
	@RequiresRoles("ROLE_ADMIN")
	public String addPermissionToRole(String roleId, String permissionId,
			ModelMap modelMap) {

		System.out.println("----------------------------------->" + roleId
				+ "---------------->" + permissionId);
		roleService.addPermissionToRole(roleId, permissionId);// 添加
		modelMap.put("roleId", roleId);// 在jsp页面通过${key}获取modelMap中的值
		List<Sys_permission> permissionList = permissionService
				.getAllPermissionWithoutRole(roleId);
		modelMap.put("permissionList", permissionList);
		return "jsp/role/role_permissionList";
	}
}
