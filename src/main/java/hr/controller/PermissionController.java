package hr.controller;

import hr.model.Sys_permission;
import hr.service.PermissionServiceI;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import charset.ChangeCharset;
import charset.getStringEncoding;

/**
 * 权限管理
 * 
 * @author Young
 *
 */
@Controller
public class PermissionController {

	private PermissionServiceI permissionService;

	public PermissionServiceI getPermissionService() {
		return permissionService;
	}

	@Autowired
	public void setPermissionService(PermissionServiceI permissionService) {
		this.permissionService = permissionService;
	}

	/**
	 * 显示所有权限的列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("showAllPermission")
	@ResponseBody
	public Map<String, Object> showAllPermission(HttpServletResponse response,
			String keyword) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 利用ChangeCharset类将iso8859-1字符转为utf-8;不转换则为乱码
		ChangeCharset change = new ChangeCharset();
		String keyword1 = change.changeCharset(keyword, "iso8859-1", "utf-8");
		List<Sys_permission> list = permissionService
				.showAllPermission(keyword1);
		response.setCharacterEncoding("utf-8");// response乱码问题解决
		resultMap.put("count", 2);
		resultMap.put("msg", "");
		resultMap.put("code", 0);
		resultMap.put("data", list);
		return resultMap;
	}

	@RequestMapping("showAllPermissionView")
	public String showAllPermissionView() {
		return "jsp/permission/showAllPermission";
	}

	/**
	 * 添加权限
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("addPermission")
	@RequiresRoles("ROLE_ADMIN")
	public String addPermission(Sys_permission permission)
			throws UnsupportedEncodingException {
		if (new getStringEncoding().getEncoding(permission.getPermissionId()).equals("ISO-8859-1")) {
		ChangeCharset change = new ChangeCharset();
		permission.setDescription(change.changeCharset(permission.getDescription(),"iso8859-1", "utf-8"));
		permission.setPermissionId(change.changeCharset(permission.getPermissionId(), "iso8859-1", "utf-8"));
		}
		permissionService.addPermission(permission);
		return "jsp/permission/showAllPermission";
	}

	/**
	 * 删除权限
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("delPermission")
	@RequiresRoles("ROLE_ADMIN")
	public String delPermission(String permissionId) throws UnsupportedEncodingException {
		// 获取编码
		if (new getStringEncoding().getEncoding(permissionId).equals("ISO-8859-1")) {
			// 字符转码
			ChangeCharset change = new ChangeCharset();
			permissionId = change.changeCharset(permissionId, "iso8859-1", "utf-8");
		}
		permissionService.delPermission(permissionId);
		return "jsp/permission/showAllPermission";
	}

	/**
	 * 修改权限
	 */
	@RequestMapping("updatePermissionById")
	@RequiresRoles("ROLE_ADMIN")
	public String updatePermissionById(Sys_permission permission) {
		permissionService.updatePermissionById(permission);
		return "jsp/permission/showAllPermission";
	}
}