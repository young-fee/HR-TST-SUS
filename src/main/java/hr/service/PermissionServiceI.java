package hr.service;

import hr.model.Sys_permission;

import java.util.List;


public interface PermissionServiceI {

	public List<Sys_permission> showAllPermission(String keyword);
	
	public int addPermission(Sys_permission permission);
	
	public int delPermission(String permissionId);
	
	public int updatePermissionById(Sys_permission permission);
	
	public List<Sys_permission> findRolePermission(String roleId);
	
	public List<Sys_permission> getAllPermissionWithoutRole(String roleId);
}
