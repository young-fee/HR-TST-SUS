package hr.service;

import hr.model.Sys_role;

import java.util.List;

public interface RoleServiceI {
	
	public List<Sys_role> showAllRole(String keyword);
	
	public int delRole(String roleId);
	
	public int updateRole(Sys_role role);
	
	public int addRole(Sys_role role);

	public int delRolePermission(String roleId,String permissionId);
	
	public int addPermissionToRole(String roleId,String permissionId);
	
	public List<Sys_role> findUserRole(String userLoginId);
	
	public List<Sys_role> getAllRoleWithoutUser(String userLoginId);
	
	public int addRoleToUser(String userLoginId,String roleId);
}
